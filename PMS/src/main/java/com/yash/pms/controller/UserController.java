package com.yash.pms.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.pms.payload.ApiResponse;
import com.yash.pms.payload.UserDto;
import com.yash.pms.service.UserService;



@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserdto=this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserdto, HttpStatus.CREATED);
		
	}
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable ("userId") Integer uid)
	{
		UserDto updatedUser=this.userService.updateUser(userDto,uid);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
		
	}
	//admin delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
public ResponseEntity<ApiResponse> deleteUser(@PathVariable ("userId") Integer uid)
{
this.userService.deleteUser(uid);
//without use ApiResponse
//return new ResponseEntity(Map.of("message","user deleted successfully"), HttpStatus.OK);
return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted sucessfully",true),HttpStatus.OK);
}
@GetMapping("/")
	public ResponseEntity<List<UserDto>> getALLUser()
	{
		return ResponseEntity.ok(this.userService.getAllUser());
		
	}
	@GetMapping("/{userId}")
public ResponseEntity<UserDto> getByUserId(@PathVariable ("userId") Integer uid)
{
	return ResponseEntity.ok(this.userService.getuserById(uid));
	
}
	
}
