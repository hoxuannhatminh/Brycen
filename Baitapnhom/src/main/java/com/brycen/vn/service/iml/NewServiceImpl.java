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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.brycen.vn.dto.NewDTO;
import com.brycen.vn.entity.New;
import com.brycen.vn.repositories.NewRepository;
import com.brycen.vn.service.INewService;

@Service
public class NewServiceImpl implements INewService {

	@Autowired
	private NewRepository newRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<Map<String, Object>> gellAll(int page) {
		try {

			int size = 10;
			List<New> listnews = new ArrayList<New>();
			Page<New> pageTuts = newRepository.findAll(PageRequest.of(page - 1, size));
			listnews = pageTuts.getContent();
			List<NewDTO> listDtos = listnews.stream().map(news -> modelMapper.map(news, NewDTO.class))
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
	public NewDTO getNewsById(Long id) {
		New news = newRepository.findById(id).get();
		NewDTO newDto = modelMapper.map(news, NewDTO.class);
		return newDto;
	}

	@Override
	public boolean insertNew(New news) {
		if (news != null) {
			newRepository.save(news);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteNew(Long id) {
		if (id != null) {
			New news = newRepository.findById(id).get();
			news.setDeleted(true);
			newRepository.save(news);
			return true;
		}
		return false;
	}

}
