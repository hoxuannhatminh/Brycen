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

import com.brycen.vn.dto.CategoryVaccineDTO;
import com.brycen.vn.dto.NewDTO;
import com.brycen.vn.entity.CategoryVaccine;
import com.brycen.vn.entity.New;
import com.brycen.vn.repositories.CategoryVaccineRepository;
import com.brycen.vn.service.ICategoryVaccineService;

@Service
public class CategoryVaccineServiceImpl implements ICategoryVaccineService{

	
	@Autowired
	private CategoryVaccineRepository categoryVaccineRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<Map<String, Object>> gellAll(int page) {
		try {
			int size = 10;
			List<CategoryVaccine> listCategoryVaccine = new ArrayList<CategoryVaccine>();
			Pageable pageable = PageRequest.of(page - 1, size);
			Page<CategoryVaccine> pageTuts = categoryVaccineRepository.findByDeleted(false, pageable);
			listCategoryVaccine = pageTuts.getContent();
			List<CategoryVaccineDTO> listDtos = listCategoryVaccine.stream().map(categoryVaccine -> modelMapper.map(categoryVaccine, CategoryVaccineDTO.class))
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
	public CategoryVaccineDTO getCategoryVaccineById(Long id) {
		CategoryVaccine categoryVaccine = categoryVaccineRepository.findById(id).get();
		CategoryVaccineDTO categoryVaccineDto = modelMapper.map(categoryVaccine, CategoryVaccineDTO.class);
		return categoryVaccineDto;
	}



	@Override
	public boolean deleteCategoryVaccine(Long id) {
		if (id != null) {
			CategoryVaccine categoryVaccine = categoryVaccineRepository.findById(id).get();
			categoryVaccine.setDeleted(true);
			categoryVaccineRepository.save(categoryVaccine);
			return true;
		}
		return false;
	}

	@Override
	public boolean createCategoryVaccine(CategoryVaccine categoryVaccine) {
		if(categoryVaccine!=null) {
			categoryVaccineRepository.save(categoryVaccine);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCategoryVaccine(CategoryVaccine categoryVaccine, Long id) {
		CategoryVaccine existingCategoryVaccine = categoryVaccineRepository.findById(id).get();
		if (id != null) {
			existingCategoryVaccine.setCode(categoryVaccine.getCode());
			existingCategoryVaccine.setName(categoryVaccine.getName());
			categoryVaccineRepository.save(existingCategoryVaccine);
			return true;
		}
		return false;
	}


}
