package com.example.DeliveryApp.AppPOC.EntityClasses;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraHolderRepository extends JpaRepository<JiraHolder, Long> {

	@Query(value = "select * from JIRA_HOLDER_TABLE where REPORTMYID=:reportId", nativeQuery = true)
	Optional<JiraHolder> findByReportID(String reportId);
}
