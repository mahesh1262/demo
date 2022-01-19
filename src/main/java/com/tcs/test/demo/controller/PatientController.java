package com.tcs.test.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.test.demo.dto.PatientDto;
import com.tcs.test.demo.model.Patient;
import com.tcs.test.demo.service.PatientService;

@RestController
@RequestMapping("/api")
public class PatientController {

	@Autowired
	PatientService patientservice;

	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> getAllTutorials() {
		try {
			List<Patient> patients = patientservice.getAllPatient();
			return new ResponseEntity<>(patients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getTutorialById(@PathVariable("id") long id) {
		Optional<Patient> patient = patientservice.findById(id);

		if (patient.isPresent()) {
			return new ResponseEntity<>(patient.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/patients")
	public ResponseEntity<Patient> createPatient(@RequestBody PatientDto patientDto) {
		try {
			Patient patient = patientservice.createPatient(patientDto);
			return new ResponseEntity<>(patient, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updateTutorial(@PathVariable("id") long id, @RequestBody PatientDto patientDto) {
		Optional<Patient> patient = patientservice.findById(id);
		if (patient.isPresent()) {
			return new ResponseEntity<>(patientservice.updatePatient(patientDto, id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/patients/{id}")
	public ResponseEntity<HttpStatus> deletePatient(@PathVariable("id") long id) {
		try {
			patientservice.deleteByid(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/patients")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			patientservice.deleteAllPatients();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
