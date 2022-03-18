package com.brycen.vn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brycen.vn.entity.CategoryVaccine;

@Repository
public interface CategoryVaccineRepository extends JpaRepository<CategoryVaccine, Long>{
	Page<CategoryVaccine> findByDeleted(boolean deleted, Pageable pageable);

}
