package com.yash.pms.service;

import java.util.List;

import com.yash.pms.payload.UserDto;

public interface UserService {
	
	UserDto registerNewUser(UserDto user);
UserDto createUser (UserDto user);
UserDto updateUser(UserDto user,Integer userId);
UserDto getuserById(Integer userId);
List<UserDto> getAllUser();
void deleteUser(Integer userId);


}
