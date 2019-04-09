package com.example.DeliveryApp.AppPOC.Resources;

import java.net.URI;
import java.util.ArrayList;
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
import com.example.DeliveryApp.AppPOC.EntityClasses.ReleaseParameters;
import com.example.DeliveryApp.AppPOC.EntityClasses.ReleaseParametersRepository;
import com.example.DeliveryApp.AppPOC.ExceptionHandlers.ReleaseNotFoundException;
import com.example.DeliveryApp.AppPOC.ExceptionHandlers.ReportAlreadyExistsException;
import com.example.DeliveryApp.AppPOC.Util.UtilityClass;

@RestController
public class ServiceClass {

	@Autowired
	ReleaseParametersRepository releaseRepo;

	@GetMapping("/getAllReports")
	public List<ReleaseParameters> retreiveAllReports() {
		return releaseRepo.findAll();
	}

	@GetMapping("/getOneReport/{id}")
	public ReleaseParameters retrieveOneReport(@PathVariable long id) throws ReleaseNotFoundException {
		Optional<ReleaseParameters> releaseParam = releaseRepo.findById(id);
		if (!releaseParam.isPresent())
			throw new ReleaseNotFoundException();
		return releaseParam.get();
	}

	@GetMapping("/getSpecificReport/{lob}/{year}/{quarter}/{release}")
	public ReleaseParameters retrieveParamReport(@PathVariable String lob, @PathVariable String year,
			@PathVariable String quarter, @PathVariable String release) throws ReleaseNotFoundException {
		Optional<ReleaseParameters> releaseParam = releaseRepo.findByParam(lob, year, quarter, release);
		if (!releaseParam.isPresent())
			throw new ReleaseNotFoundException();
		return releaseParam.get();
	}

	/*
	 * @DeleteMapping("/deleteReport/{id}") public void deleteReportID(@PathVariable
	 * long id) throws ReleaseNotFoundException { releaseRepo.deleteById(id); }
	 */

	// create a new entry in the DB
	@PostMapping("/createReport")
	public ResponseEntity<ReleaseParameters> createReport(@RequestBody ReleaseParameters releaseParam) {
		try {
			ReleaseParameters newParam = releaseParam;
			List<JiraHolder> jiraHolderList = new ArrayList<JiraHolder>(newParam.getJiraholder().size());
			for (int i = 0; i < newParam.getJiraholder().size(); i++) {
				JiraHolder newJiraHolder = newParam.getJiraholder().get(i);
				newJiraHolder.setReleaseParam(newParam);
				jiraHolderList.add(newJiraHolder);
			}
			newParam.setJiraholder(jiraHolderList);
			ReleaseParameters savedReport = releaseRepo.save(newParam);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedReport.getID()).toUri();
			return ResponseEntity.created(location).build();
		} catch (RuntimeException e) {
			System.out.println("A report with same parameters already exists " + e);
			throw new ReportAlreadyExistsException();
		}

	}

	//update an existing entry in the DB
	@PutMapping("/updateReport/{id}")
	public ResponseEntity<ReleaseParameters> updateReport(@PathVariable long id,
			@RequestBody ReleaseParameters releaseParam) {
		ReleaseParameters existing = releaseRepo.findById(id).get();
		UtilityClass.copyNonNullProperties(releaseParam, existing);
		ReleaseParameters savedReport = releaseRepo.save(existing);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedReport.getID()).toUri();
		return ResponseEntity.created(location).build();
	}

	/*
	 * @GetMapping("/uploadCSV") public void updateAllReports() {
	 * List<ReleaseParameters> myList=UtilityClass.readCSV(); for(int
	 * i=0;i<myList.size();i++) { this.createReport(myList.get(i)); }
	 * 
	 * 
	 * 
	 * }
	 */

}
