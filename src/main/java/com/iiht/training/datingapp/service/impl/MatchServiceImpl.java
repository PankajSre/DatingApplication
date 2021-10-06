package com.iiht.training.datingapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.datingapp.dto.DislikeDto;
import com.iiht.training.datingapp.dto.LikeDto;
import com.iiht.training.datingapp.dto.MatchDto;
import com.iiht.training.datingapp.dto.UserDto;
import com.iiht.training.datingapp.entity.Dislike;
import com.iiht.training.datingapp.entity.Like;
import com.iiht.training.datingapp.entity.Match;
import com.iiht.training.datingapp.entity.User;
import com.iiht.training.datingapp.filter.Filter;
import com.iiht.training.datingapp.filter.FilterUtils;
import com.iiht.training.datingapp.repository.MatchRepository;
import com.iiht.training.datingapp.service.DislikesService;
import com.iiht.training.datingapp.service.LikesService;
import com.iiht.training.datingapp.service.LocationServiceApi;
import com.iiht.training.datingapp.service.MatchService;

@Service
public class MatchServiceImpl implements MatchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchService.class);

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	LikesService likesService;

	@Autowired
	LocationServiceApi locationServiceApi;

	@Autowired
	DislikesService dislikesService;

	public List<MatchDto> getAllMatches(Long userId) {
		List<Match> matches = matchRepository.findByUserIdOrMatchedUserId(userId, userId);
		List<MatchDto> matchesDto = new ArrayList<>();
		for (Match match : matches) {
			MatchDto matchDto = new MatchDto();
			BeanUtils.copyProperties(match, matchDto);
			matchesDto.add(matchDto);
		}
		return matchesDto;
	}

	public MatchDto saveMatch(MatchDto matchDto) {
		Match match = new Match();
		BeanUtils.copyProperties(matchDto, match);
		matchRepository.save(match);
		return matchDto;

	}

	@Override
	public List<UserDto> getPotentialMatches(Long userId, List<Filter> filters) {
		List<UserDto> filteredUsers = locationServiceApi.getUsersDto(filters);
		List<LikeDto> likes = likesService.getAllLikes(userId);
		List<DislikeDto> dislikes = dislikesService.getAllDislikes(userId);
		// Filter already liked users
		filteredUsers = FilterUtils.filterLikedUsers(filteredUsers, likes);
		// filter already disliked users
		filteredUsers = FilterUtils.filterDislikedUsers(filteredUsers, dislikes);
		return filteredUsers;
	}

}