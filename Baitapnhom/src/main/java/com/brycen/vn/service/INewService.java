package com.brycen.vn.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.brycen.vn.dto.NewDTO;
import com.brycen.vn.entity.New;

public interface INewService {
	
	ResponseEntity<Map<String, Object>> gellAll(int page);
	
	NewDTO getNewsById(Long id);
	
	boolean insertNew(New news);
	
	boolean deleteNew(Long id);

}
