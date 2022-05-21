package com.yash.pms;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yash.pms.config.AppConstants;
import com.yash.pms.entity.Role;
import com.yash.pms.repository.RoleRepository;



@SpringBootApplication
public class PmsApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(PmsApplication.class, args);
	}

	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.passwordEncoder.encode("xyz"));
		/*
		 * try { Role role=new Role(); role.setId(AppConstants.ADMIN_USER);
		 * role.setName("ADMIN_USER");
		 * 
		 * Role role1=new Role(); role.setId(AppConstants.NORMAL_USER);
		 * role.setName("NORMAL_USER");
		 * 
		 * List<Role> roles=List.of(role,role1); List<Role>
		 * result=this.roleRepository.saveAll(roles);
		 * result.forEach(r->{System.out.println(r.getName()); });
		 * 
		 * } catch (Exception e) { // TODO: handle exception // e.printStackTrace();
		 * throw new Exception("already exist"); }
		 */
	}
	}
