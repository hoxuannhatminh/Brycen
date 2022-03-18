package com.brycen.vn.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.brycen.vn.dto.VaccineDTO;
import com.brycen.vn.entity.CategoryVaccine;
import com.brycen.vn.entity.Vaccine;

public interface IVaccineService {
	ResponseEntity<Map<String, Object>> gellAll(int page);
	
	VaccineDTO getVaccineById(Long id);
	
	VaccineDTO createVaccine(VaccineDTO vaccineDTO);
	
	VaccineDTO updateVaccine(VaccineDTO vaccineDTO);
	
	boolean deleteVaccine(Long id);
	
}
