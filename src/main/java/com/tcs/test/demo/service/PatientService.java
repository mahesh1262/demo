package com.tcs.test.demo.service;

import java.util.List;
import java.util.Optional;

import com.tcs.test.demo.dto.PatientDto;
import com.tcs.test.demo.model.Patient;

public interface PatientService {

	Patient createPatient(PatientDto patientDto);

	List<Patient> getAllPatient();

	Optional<Patient> findById(Long id);

	Patient updatePatient(PatientDto patientDto, Long id);

	void deleteByid(Long id);

	void deleteAllPatients();
	
	List<Patient> findByName(String name);
	

}
