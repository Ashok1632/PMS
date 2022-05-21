package com.yash.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.pms.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
