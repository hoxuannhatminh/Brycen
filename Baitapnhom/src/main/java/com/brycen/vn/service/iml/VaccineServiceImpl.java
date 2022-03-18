package com.brycen.vn.service.iml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.brycen.vn.dto.VaccineDTO;
import com.brycen.vn.entity.CategoryVaccine;
import com.brycen.vn.entity.Vaccine;
import com.brycen.vn.repositories.CategoryVaccineRepository;
import com.brycen.vn.repositories.VaccineRepository;
import com.brycen.vn.service.IVaccineService;

@Service
public class VaccineServiceImpl implements IVaccineService{
	
	@Autowired
	private VaccineRepository vaccineRepository;
	
	
	@Autowired
	private CategoryVaccineRepository categoryVaccineRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public ResponseEntity<Map<String, Object>> gellAll(int page) {
		try {
			int size = 10;
			List<Vaccine> listVaccine = new ArrayList<Vaccine>();
			Pageable pageable = PageRequest.of(page - 1, size);
			Page<Vaccine> pageTuts = vaccineRepository.findByDeleted(false, pageable);
			listVaccine = pageTuts.getContent();
			List<VaccineDTO> listDtos = listVaccine.stream().map(vaccine -> modelMapper.map(vaccine, VaccineDTO.class))
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
	public 	VaccineDTO createVaccine(VaccineDTO vaccineDTO) {
		Vaccine vaccine = new Vaccine();
		CategoryVaccine categoryVaccine = new CategoryVaccine();
		Optional<CategoryVaccine> listcategoryVaccine = categoryVaccineRepository.findById(vaccineDTO.getCategoryVaccineID());
		if(listcategoryVaccine.isPresent()) {
			categoryVaccine = listcategoryVaccine.get();
		}
		vaccine.setCode(vaccineDTO.getCode());
		vaccine.setName(vaccineDTO.getName());
		vaccine.setDescription(vaccineDTO.getDescription());
		vaccine.setExprirationDate(vaccineDTO.getExprirationDate());
		vaccine.setManufacturingDate(vaccineDTO.getManufacturingDate());
		vaccine.setProducer(vaccineDTO.getProducer());
		vaccine.setCategoryVaccine(categoryVaccine);
		vaccineRepository.save(vaccine);
		return null;
	}

	@Override
	public VaccineDTO updateVaccine(VaccineDTO vaccineDTO) {
		Optional<Vaccine> extsingvaccine = vaccineRepository.findById(vaccineDTO.getId());
		Vaccine vaccine = new Vaccine();	
		if(extsingvaccine.isPresent()) {
			vaccine.setId(vaccineDTO.getId());
			vaccine.setCode(vaccineDTO.getCode());
			vaccine.setName(vaccineDTO.getName());
			vaccine.setDescription(vaccineDTO.getDescription());
			vaccine.setExprirationDate(vaccineDTO.getExprirationDate());
			vaccine.setManufacturingDate(vaccineDTO.getManufacturingDate());
			vaccine.setProducer(vaccineDTO.getProducer());
			Optional<CategoryVaccine> listcategoryVaccine = categoryVaccineRepository.findById(vaccineDTO.getCategoryVaccineID()); //chứa giá trị null or null
			CategoryVaccine categoryVaccine = listcategoryVaccine.get();
			vaccine.setCategoryVaccine(categoryVaccine);
			vaccineRepository.save(vaccine);
			return vaccineDTO;
		}
		
		return null;
	}

	
	@Override
	public VaccineDTO getVaccineById(Long id) {
		Vaccine vaccine = vaccineRepository.findById(id).get();
		VaccineDTO vaccineDto = modelMapper.map(vaccine, VaccineDTO.class);
		return vaccineDto;
	}


	@Override
	public boolean deleteVaccine(Long id) {
		if (id != null) {
			Vaccine vaccine = vaccineRepository.findById(id).get();
			vaccine.setDeleted(true);
			vaccineRepository.save(vaccine);
			return true;
		}
		return false;
	}






}
