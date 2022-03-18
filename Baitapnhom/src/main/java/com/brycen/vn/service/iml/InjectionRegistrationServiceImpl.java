package com.brycen.vn.service.iml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.brycen.vn.dto.InjectionRegistrationDTO;
import com.brycen.vn.entity.InjectionRegistration;
import com.brycen.vn.entity.Vaccine;
import com.brycen.vn.repositories.InjectionRegistrationRepository;
import com.brycen.vn.service.IInjectionRegistrationService;

@Service
public class InjectionRegistrationServiceImpl implements  IInjectionRegistrationService{
	
	@Autowired
	private InjectionRegistrationRepository injectionRegistrationRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	public ResponseEntity<Map<String, Object>> gellAll(int page) {
		try {
			int size = 10;
			List<InjectionRegistration> listinjectionRegistration = new ArrayList<InjectionRegistration>();
			Pageable pageable = PageRequest.of(page - 1, size);
			Page<InjectionRegistration> pageTuts = injectionRegistrationRepository.findByStatus(0L, pageable);
			listinjectionRegistration = pageTuts.getContent();
			List<InjectionRegistrationDTO> listDtos = listinjectionRegistration.stream().map(injectionRegistration -> modelMapper.map(injectionRegistration, InjectionRegistrationDTO.class))
					.collect(Collectors.toList());

			Map<String, Object> response = new HashMap<>();
			response.put("listnews", listDtos);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public ResponseEntity<Map<String, Object>> gellComfirm(int page) {
		try {
			int size = 10;
			List<InjectionRegistration> listinjectionRegistration = new ArrayList<InjectionRegistration>();
			Pageable pageable = PageRequest.of(page - 1, size);
			Page<InjectionRegistration> pageTuts = injectionRegistrationRepository.findByStatus(1L, pageable);
			listinjectionRegistration = pageTuts.getContent();
			List<InjectionRegistrationDTO> listDtos = listinjectionRegistration.stream().map(injectionRegistration -> modelMapper.map(injectionRegistration, InjectionRegistrationDTO.class))
					.collect(Collectors.toList());

			Map<String, Object> response = new HashMap<>();
			response.put("listnews", listDtos);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public ResponseEntity<Map<String, Object>> gellCancel(int page) {
		try {
			int size = 10;
			List<InjectionRegistration> listinjectionRegistration = new ArrayList<InjectionRegistration>();
			Pageable pageable = PageRequest.of(page - 1, size);
			Page<InjectionRegistration> pageTuts = injectionRegistrationRepository.findByStatus(2L, pageable);
			listinjectionRegistration = pageTuts.getContent();
			List<InjectionRegistrationDTO> listDtos = listinjectionRegistration.stream().map(injectionRegistration -> modelMapper.map(injectionRegistration, InjectionRegistrationDTO.class))
					.collect(Collectors.toList());

			Map<String, Object> response = new HashMap<>();
			response.put("listnews", listDtos);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@Override
	public boolean confirm(Long id) {
		if (id != null) {
			InjectionRegistration injectionRegistration = injectionRegistrationRepository.findById(id).get();
			injectionRegistration.setStatus(1L);
			injectionRegistrationRepository.save(injectionRegistration);
			return true;
		}
		return false;
	}
	@Override
	public boolean cancel(Long id) {
		if (id != null) {
			InjectionRegistration injectionRegistration = injectionRegistrationRepository.findById(id).get();
			injectionRegistration.setStatus(2L);
			injectionRegistrationRepository.save(injectionRegistration);
			return true;
		}
		return false;
	}
}
