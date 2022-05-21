package com.yash.pms.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.yash.pms.entity.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min=4,message="name must be min of 4 charracter!!")
	private String name;
	
@Email(message="Email address is not valid!!")
	private String email;
@NotNull
@Size(min=3,max=16 ,message="name must be min of 3 char and max 8 char!!")
	private String password;
private String about;

private Set<RoleDto> roles=new HashSet<>();
}
