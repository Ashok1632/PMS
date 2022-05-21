package com.yash.pms.service.Impl;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yash.pms.config.AppConstants;
import com.yash.pms.entity.Role;
import com.yash.pms.entity.User;
import com.yash.pms.exception.ResourceNotFoundException;
import com.yash.pms.payload.UserDto;
import com.yash.pms.repository.RoleRepository;
import com.yash.pms.repository.UserRepository;
import com.yash.pms.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.dtoToUser(userDto);
		User saveuser=this.userRepository.save(user);
		return this.userToDto(saveuser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		user.setName(userDto.getName());
		
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		User updatedUser=this.userRepository.save(user);
		UserDto userDto1=this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getuserById(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		List<User> users=this.userRepository.findAll();
		List<UserDto> userdtos=users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		this.userRepository.delete(user);
		
	}
private User dtoToUser(UserDto userDto)
{ 
	/*
	 * User user=new User(); user.setFirstname(userDto.getFirstname());
	 * user.setLastname(userDto.getLastname());
	 * 
	 * user.setEmail(userDto.getEmail()); user.setPassword(userDto.getPassword()); 
	 * old style
	 */
	//another using modelmapper
	User user=this.modelmapper.map(userDto, User.class);
	return user;
}
public UserDto userToDto(User user)
{
	/*
	 * UserDto userDto=new UserDto(); userDto.setFirstname(user.getFirstname());
	 * userDto.setLastname(user.getLastname());
	 * 
	 * userDto.setEmail(user.getEmail()); userDto.setPassword(user.getPassword());
	 */
	
	UserDto userDto=this.modelmapper.map(user, UserDto.class);
	return userDto;
	
}

@Override
public UserDto registerNewUser(UserDto userDto) {
	// TODO Auto-generated method stub
	User user=this.modelmapper.map(userDto,User.class );
	//encode password
	user.setPassword(this.passwordEncoder.encode(user.getPassword()));
	Role role=this.roleRepository.findById(AppConstants.NORMAL_USER).get();
	user.getRoles().add(role);
	User newUser=this.userRepository.save(user);
	return this.modelmapper.map(newUser,UserDto.class);
}
}
