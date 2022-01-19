package com.tcs.test.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.test.demo.dto.PatientDto;
import com.tcs.test.demo.model.Patient;
import com.tcs.test.demo.repository.PatientRepository;
import com.tcs.test.demo.service.PatientService;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

@Service
public class PatientserviceImpl implements PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Patient createPatient(PatientDto patientDto) {
		
		Patient patient = modelMapper.map(patientDto,Patient.class);
		
		return patientRepository.save(patient);
		
	}

	@Override
	public List<Patient> getAllPatient() {
		return patientRepository.findAll();		 
	}

	@Override
	public Optional<Patient> findById(Long id) {
		
		return patientRepository.findById(id);
	}

	@Override
	public Patient updatePatient(PatientDto patientDto, Long id) {
		Patient patientupdated = null ;
		Optional<Patient> patient = patientRepository.findById(id);
		if (patient.isPresent()) {
			patientupdated =	patient.get();
			patientupdated.setName(patientDto.getName());
			patientupdated.setGender(patientDto.getGender());
			patientupdated.setMobileNumber(patientDto.getMobileNumber());
			patientupdated.setAge(patientDto.getGender());
		patientRepository.save(patientupdated);
		}
		return patientupdated;
		
	}

	@Override
	public void deleteByid(Long id) {
	   patientRepository.deleteById(id);		
	}

	@Override
	public void deleteAllPatients() {
		patientRepository.deleteAll();		
	}
	
	

}
