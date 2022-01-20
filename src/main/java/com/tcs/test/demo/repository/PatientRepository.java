package com.tcs.test.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.test.demo.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	List<Patient> findByNameContaining(String name);
}
