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

import com.brycen.vn.dto.NewDTO;
import com.brycen.vn.dto.VaccineDTO;
import com.brycen.vn.entity.CategoryVaccine;
import com.brycen.vn.entity.Vaccine;
import com.brycen.vn.service.iml.VaccineServiceImpl;

@RestController
@RequestMapping("/congthongtin")
@CrossOrigin("*")
public class VaccineController {
	@Autowired
	private VaccineServiceImpl vaccineServiceImpl;
	
	// admin quan li loai vaccine
	@GetMapping("/admin/vaccine")
	public ResponseEntity<Map<String, Object>> getAllVaccineAdmin(
			@RequestParam(value = "page", defaultValue = "1") int page) {
		return vaccineServiceImpl.gellAll(page);
	}
	//admin thêm vaccnie
	@PostMapping(value = "/admin/vaccine")
	public ResponseEntity<String> createVaccine(@RequestBody VaccineDTO vaccineDTO) {
		vaccineServiceImpl.createVaccine(vaccineDTO);
		return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
	}
	//admin sửa vaccine
	@PutMapping(value = "/admin/vaccine/{id}")
	public ResponseEntity<String> updateVaccine(@RequestBody VaccineDTO vaccineDTO ,@PathVariable Long id) {	
		vaccineDTO.setId(id);
		if(vaccineServiceImpl.updateVaccine(vaccineDTO) !=null) {
			return new ResponseEntity<String>("Update!",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("ERROR!",HttpStatus.BAD_REQUEST);
	}
	
	// admin xóa  vacine
	@DeleteMapping("/admin/vaccine/{id}")
	public ResponseEntity<String> deleteVaccine(@PathVariable Long id) {
		if (vaccineServiceImpl.deleteVaccine(id)) {
			return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ERROR!", HttpStatus.BAD_REQUEST);
	}

}
