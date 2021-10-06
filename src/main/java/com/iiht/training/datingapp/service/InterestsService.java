package com.iiht.training.datingapp.service;

import com.iiht.training.datingapp.dto.InterestsDto;

public interface InterestsService {

	public InterestsDto createInterest(InterestsDto interests);
	public InterestsDto updateInterest(InterestsDto interests);
	public boolean deleteInterest(Long interestId);
	public InterestsDto getById(Long interestId);
	
}
