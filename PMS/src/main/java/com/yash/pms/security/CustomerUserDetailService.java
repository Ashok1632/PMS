package com.yash.pms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yash.pms.entity.User;
import com.yash.pms.exception.ResourceNotFoundException;
import com.yash.pms.repository.UserRepository;


@Service
public class CustomerUserDetailService implements UserDetailsService {
	@Autowired
private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "email:"+username,0));
		return user;
	}

}
