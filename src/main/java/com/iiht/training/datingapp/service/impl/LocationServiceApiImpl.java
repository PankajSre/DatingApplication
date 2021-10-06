package com.iiht.training.datingapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iiht.training.datingapp.dto.UserDto;
import com.iiht.training.datingapp.entity.User;
import com.iiht.training.datingapp.filter.Filter;
import com.iiht.training.datingapp.filter.FilterUtils;
import com.iiht.training.datingapp.service.LocationServiceApi;

@Service
public class LocationServiceApiImpl implements LocationServiceApi {

	private static final List<UserDto> USER_DATA = new ArrayList<UserDto>();

	

	static {
	

		USER_DATA.add(new UserDto(101L, "manas", 21, 8712312345L, "Male", "manas@gmail.com", "New Delhi", "India"));
		USER_DATA.add(new UserDto(102L, "Leena", 22, 9812091290L, "Female", "leena@gmail.com", "Mexico", "Mexico"));
		USER_DATA.add(new UserDto(103L, "Diana", 30, 9999111122L, "Female", "diana@gmail.com", "New Jersy", "US"));
		USER_DATA.add(new UserDto(104L, "Pinky", 35, 8129012341L, "Female", "pinky@gmail.com", "Bangalore", "India"));
		USER_DATA.add(new UserDto(105L, "Pankaj", 36, 9090901212L, "Male", "pankaj@gmail.com", "Mumbai", "India"));
		USER_DATA.add(new UserDto(106L, "Advik", 10, 8712309189L, "Male", "advik@gmail.com", "Washington", "US"));
		USER_DATA.add(new UserDto(107L, "Sucheta", 35, 8888776655L, "Female", "sucheta@gmail.com", "London", "UK"));
		USER_DATA.add(new UserDto(108L, "Hardik", 11, 7812309878L, "Male", "hardik@gmail.com", "Pune", "India"));
		USER_DATA.add(new UserDto(109L, "Krishna", 20, 6677110099L, "Male", "krishna@gmail.com", "Bangalore", "India"));
		USER_DATA.add(new UserDto(110L, "Ramesh", 26, 7788990011L, "Male", "ramesh@gmail.com", "New Delhi", "India"));
		

	}

	@Override
	public List<UserDto> getUsersDto(List<Filter> filters) {
		
		List<UserDto> filteredUsers = USER_DATA;
		if (filters != null && !filters.isEmpty()) {
			for (Filter filter : filters) {
				filteredUsers = applyFilter(filter, filteredUsers);
			}
		}
		return filteredUsers;
	}

	private List<UserDto> applyFilter(Filter filter, List<UserDto> filteredUsers) {
		switch (filter.getType()) {
		case AGE:
			filteredUsers = FilterUtils.applyAgeFilter(filteredUsers, filter.getValues());
			break;
		case CITY:
			filteredUsers = FilterUtils.applyLocationFilter(filteredUsers, filter.getValues(), true);
			break;
		case COUNTRY:
			filteredUsers = FilterUtils.applyLocationFilter(filteredUsers, filter.getValues(), false);
			break;
		case GENDER:
			filteredUsers = FilterUtils.applyGenderFilter(filteredUsers, filter.getValues());
			break;
		}
		return filteredUsers;
	}

}