package com.revature.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entity.Patient;
import com.revature.entity.dto.PatientDto;
import com.revature.service.PatientInfoService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
//@EnableDiscoveryClient
@RequestMapping("/api/v1")
@CrossOrigin(origins="*")
public class PatientInfoController {

	@Autowired
	PatientInfoService patientService;

	@GetMapping("/patient")
	public ResponseEntity<List<PatientDto>> getPatients() {

		List<PatientDto> bpi=null;
		try {
		bpi=patientService.getAllPatients();
		}
		catch (NullPointerException e){
			return new ResponseEntity<>(bpi,HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(NoSuchElementException e){
			return new ResponseEntity<>(bpi,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(bpi, HttpStatus.OK);
	}

	@GetMapping("/patient/{id}")
	public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") int id) {
		PatientDto bpi=null;
		try
		{
			bpi=patientService.getPatientById(id);
		}
		catch(NullPointerException e)
		{
			return new ResponseEntity<>(bpi,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(NoSuchElementException e){
			return new ResponseEntity<PatientDto>(bpi,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(bpi, HttpStatus.OK);
	}

	 
	@PutMapping("/patient/{id}")
	public ResponseEntity<PatientDto> putPatient(@PathVariable("id") int id,
			@RequestBody @Valid PatientDto basic ) {
		return new ResponseEntity<>(patientService.updatePatientById(id, basic), HttpStatus.OK);
	}
	
}
