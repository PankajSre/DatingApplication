package com.iiht.training.datingapp.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.datingapp.dto.InterestsDto;
import com.iiht.training.datingapp.entity.Interests;
import com.iiht.training.datingapp.exceptions.InterestsNotFoundException;
import com.iiht.training.datingapp.repository.InterestsRepository;
import com.iiht.training.datingapp.service.InterestsService;

@Service
public class InterestsServiceImpl implements InterestsService {

	@Autowired
	private InterestsRepository interestsRepository;

	@Override
	public InterestsDto createInterest(InterestsDto interestsDto) {

		Interests interests = new Interests();
		BeanUtils.copyProperties(interestsDto, interests);
		interestsRepository.save(interests);
		return interestsDto;
	}

	@Override
	public InterestsDto updateInterest(InterestsDto interestsDto) {

		Interests interests = new Interests();
		BeanUtils.copyProperties(interestsDto, interests);
		interestsRepository.save(interests);
		return interestsDto;
	}

	@Override
	public boolean deleteInterest(Long interestId) {
		InterestsDto interestsDto = getById(interestId);
		Interests interests = new Interests();
		BeanUtils.copyProperties(interestsDto, interests);
		interestsRepository.delete(interests);
		return true;
	}

	@Override
	public InterestsDto getById(Long interestId) {
		Optional<Interests> interests = interestsRepository.findById(interestId);
		if (interests.isPresent()) {
			InterestsDto interestsDto = new InterestsDto();
			BeanUtils.copyProperties(interests.get(), interestsDto);
			return interestsDto;
		} else {
			throw new InterestsNotFoundException("Interest with id " + interestId + " is not found");
		}

	}

}
