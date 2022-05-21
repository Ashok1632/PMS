package com.yash.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.pms.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
