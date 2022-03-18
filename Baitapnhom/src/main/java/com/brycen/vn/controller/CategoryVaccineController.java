package com.brycen.vn.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.vn.entity.CategoryVaccine;
import com.brycen.vn.service.iml.CategoryVaccineServiceImpl;

@RestController
@RequestMapping("/congthongtin")
@CrossOrigin("*")
public class CategoryVaccineController {

	@Autowired
	private CategoryVaccineServiceImpl categoryVaccineServiceImpl;
	
	// admin quan li loai vaccine
	@GetMapping("/admin/categoryvaccine")
	public ResponseEntity<Map<String, Object>> getAllCategoryVaccineAdmin(
			@RequestParam(value = "page", defaultValue = "1") int page) {
		return categoryVaccineServiceImpl.gellAll(page);
	}
	
	// admin thêm loai vaccine mới
	@PostMapping(value = "/admin/categoryvaccine")
	public ResponseEntity<String> createCategoryVaccine(@RequestBody CategoryVaccine categoryVaccine) {
		if (categoryVaccineServiceImpl.createCategoryVaccine(categoryVaccine)) {
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("ERROR!", HttpStatus.BAD_REQUEST);
		}
	}
	
	// admin sửa loai vaccine 
	@PutMapping(value = "/admin/categoryvaccine/{id}")
	public ResponseEntity<String> updateCategoryVaccine(@RequestBody CategoryVaccine categoryVaccine, @PathVariable("id") long id) {
		if (categoryVaccineServiceImpl.updateCategoryVaccine(categoryVaccine, id)) {
			return new ResponseEntity<String>("Update!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("ERROR!", HttpStatus.BAD_REQUEST);
		}
	}

	// admin xóa loai vacine
	@DeleteMapping("/admin/categoryvaccine/{id}")
	public ResponseEntity<String> deleteCategoryVaccine(@PathVariable Long id) {
		if (categoryVaccineServiceImpl.deleteCategoryVaccine(id)) {
			return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ERROR!", HttpStatus.BAD_REQUEST);
	}
}
