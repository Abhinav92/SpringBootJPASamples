package com.example.DeliveryApp.AppPOC.EntityClasses;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseParametersRepository extends JpaRepository<ReleaseParameters, Long> {

	@Query(value = "select * from RELEASE_PARAMETERS where REPORTID=:id", nativeQuery = true)
	Optional<ReleaseParameters> findById(long id);

	@Query(value = "SELECT * FROM RELEASE_PARAMETERS where LOB=:lob and Quarter=:quarter and Releases=:release and year=:year", nativeQuery = true)
	Optional<ReleaseParameters> findByParam(String lob, String year, String quarter, String release);

}
