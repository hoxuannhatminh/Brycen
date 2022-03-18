package com.brycen.vn.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.brycen.vn.dto.CategoryVaccineDTO;
import com.brycen.vn.entity.CategoryVaccine;

public interface ICategoryVaccineService {

	
	ResponseEntity<Map<String, Object>> gellAll(int page);
	
	CategoryVaccineDTO getCategoryVaccineById(Long id);
	
	boolean createCategoryVaccine(CategoryVaccine CategoryVaccine);
	
	boolean updateCategoryVaccine(CategoryVaccine categoryVaccine, Long id);
	
	boolean deleteCategoryVaccine(Long id);
	
	
}
