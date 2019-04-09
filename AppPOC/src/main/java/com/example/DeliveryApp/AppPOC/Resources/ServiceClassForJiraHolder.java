package com.example.DeliveryApp.AppPOC.Resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.DeliveryApp.AppPOC.EntityClasses.JiraHolder;
import com.example.DeliveryApp.AppPOC.EntityClasses.JiraHolderRepository;
import com.example.DeliveryApp.AppPOC.EntityClasses.ReleaseParameters;
import com.example.DeliveryApp.AppPOC.ExceptionHandlers.ReleaseNotFoundException;
import com.example.DeliveryApp.AppPOC.ExceptionHandlers.ReportAlreadyExistsException;
import com.example.DeliveryApp.AppPOC.Util.UtilityClass;

@RestController
public class ServiceClassForJiraHolder {

	@Autowired
	JiraHolderRepository jiraRepo;

	@GetMapping("/getAllJiraInfo")
	public List<JiraHolder> retreiveAllJiraInfo() {
		return jiraRepo.findAll();
	}

	@GetMapping("/getSpecificJira/{id}")
	public JiraHolder retrieveSpecificJira(@PathVariable long id) throws ReleaseNotFoundException {
		Optional<JiraHolder> jiraHolderParam = jiraRepo.findById(id);
		if (!jiraHolderParam.isPresent())
			throw new ReleaseNotFoundException();
		return jiraHolderParam.get();
	}

	@GetMapping("/getAllSpecificJira/{reportId}")
	public JiraHolder retrieveAllSpecificJira(@PathVariable String reportId) throws ReleaseNotFoundException {
		Optional<JiraHolder> jiraHolderParam = jiraRepo.findByReportID(reportId);
		if (!jiraHolderParam.isPresent())
			throw new ReleaseNotFoundException();
		return jiraHolderParam.get();
	}
	/*
	 * @DeleteMapping("/deleteReport/{id}") public void deleteReportID(@PathVariable
	 * long id) throws ReleaseNotFoundException { releaseRepo.deleteById(id); }
	 */

	// create report based on the Object
	@PostMapping("/createJiraItems/{reportID}")
	public ResponseEntity<Object> createNewReport(@PathVariable String reportID, @RequestBody JiraHolder jiraParam) {
		try {
			JiraHolder tempHolder = new JiraHolder(jiraParam.getStory(), jiraParam.getUsage(), jiraParam.getBusiness(),
					jiraParam.getEffort());
			// tempHolder.setReleaseParam(reportID);
			JiraHolder savedReport = jiraRepo.save(tempHolder);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedReport.getID()).toUri();
			return ResponseEntity.created(location).build();
		} catch (RuntimeException e) {
			System.out.println("A report with same parameters already exists " + e);
			throw new ReportAlreadyExistsException();
		}
	}

	@PutMapping("/updateJiraItems/{id}")
	public ResponseEntity<ReleaseParameters> updateReport(@PathVariable long id, @RequestBody JiraHolder jiraParam) {
		try {
			JiraHolder existing = jiraRepo.findById(id).get();
			UtilityClass.copyNonNullProperties(jiraParam, existing);
			JiraHolder savedReport = jiraRepo.save(existing);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedReport.getID()).toUri();
			return ResponseEntity.created(location).build();
		} catch (RuntimeException e) {
			System.out.println("A report with same parameters already exists " + e);
			throw new ReportAlreadyExistsException();
		}
	}
}
