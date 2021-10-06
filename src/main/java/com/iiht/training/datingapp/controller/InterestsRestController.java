package com.iiht.training.datingapp.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.training.datingapp.dto.InterestsDto;
import com.iiht.training.datingapp.entity.Interests;
import com.iiht.training.datingapp.exceptions.InterestsNotFoundException;
import com.iiht.training.datingapp.exceptions.InvalidDataException;
import com.iiht.training.datingapp.service.InterestsService;

@RestController
@RequestMapping("/interests")
public class InterestsRestController {

	@Autowired
	private InterestsService interestsService;

	@PostMapping
	public ResponseEntity<?> addInterests(@Valid @RequestBody InterestsDto interestsDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Request data is not valid");
		}
		return new ResponseEntity<InterestsDto>(interestsService.createInterest(interestsDto), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> updateInterests(@Valid @RequestBody InterestsDto interestsDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Request data is not valid");
		}
		return new ResponseEntity<InterestsDto>(interestsService.updateInterest(interestsDto), HttpStatus.OK);
	}

	@DeleteMapping("/{interestId}")
	public ResponseEntity<?> deleteInterests(@PathVariable Long interestId) {
		boolean deleteInterest = interestsService.deleteInterest(interestId);
		return new ResponseEntity<>(deleteInterest, HttpStatus.OK);
	}

	@GetMapping("/{interestId}")
	public ResponseEntity<InterestsDto> getInterestsById(@PathVariable Long interestId) {
		InterestsDto interestDto = interestsService.getById(interestId);
		return ResponseEntity.ok(interestDto);
	}

}
