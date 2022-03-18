package com.brycen.vn.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.vn.service.iml.InjectionRegistrationServiceImpl;

@RestController
@RequestMapping("/congthongtin")
@CrossOrigin("*")
public class InjectionRegistrationController {
	@Autowired
	private InjectionRegistrationServiceImpl injectionRegistrationServiceImpl;
	
	// admin quan li loai vaccine
	@GetMapping("/admin/injectionregistration")
	public ResponseEntity<Map<String, Object>> getAllInjectionRegistration(
			@RequestParam(value = "page", defaultValue = "1") int page) {
		return injectionRegistrationServiceImpl.gellAll(page);
	}
	
	@GetMapping("/admin/injectionregistration/comfirn")
	public ResponseEntity<Map<String, Object>> getAllInjectionRegistrationComfirm(
			@RequestParam(value = "page", defaultValue = "1") int page) {
		return injectionRegistrationServiceImpl.gellComfirm(page);
	}
	
	@GetMapping("/admin/injectionregistration/cancel")
	public ResponseEntity<Map<String, Object>> getAllInjectionRegistrationCancel(
			@RequestParam(value = "page", defaultValue = "1") int page) {
		return injectionRegistrationServiceImpl.gellCancel(page);
	}
	
	@PostMapping("/admin/injectionregistration/{id}")
	public ResponseEntity<String> confirmInjectionRegistration(@PathVariable("id") long id) {
		if (injectionRegistrationServiceImpl.confirm(id)) {
			return new ResponseEntity<String>("Đã tiêm!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PutMapping("/admin/injectionregistration/{id}")
	public ResponseEntity<String> cancelInjectionRegistration(@PathVariable("id") long id) {
		if (injectionRegistrationServiceImpl.cancel(id)) {
			return new ResponseEntity<String>("Huỷ tiêm!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
		}
	}
}
