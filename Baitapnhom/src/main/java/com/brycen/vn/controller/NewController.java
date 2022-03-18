package com.brycen.vn.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.vn.dto.NewDTO;
import com.brycen.vn.entity.New;
import com.brycen.vn.service.iml.NewServiceImpl;

@RestController
@RequestMapping("/congthongtin")
@CrossOrigin("*")
public class NewController {
	@Autowired
	private NewServiceImpl newServiceImpl;

	// phân trang
	@GetMapping("/news")
	public ResponseEntity<Map<String, Object>> getAllNews(@RequestParam(value = "page", defaultValue = "1") int page) {

		return newServiceImpl.gellAll(page);
	}

	// xem chi tiết nội dung
	@RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getNewsById(@PathVariable Long id) {
		NewDTO newDTO = newServiceImpl.getNewsById(id);
		if (newDTO != null) {
			return new ResponseEntity<Object>(newDTO, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Not Found New", HttpStatus.NO_CONTENT);
	}
	
//	@PutMapping("/news")
//	public ResponseEntity<String> updateNews(@RequestBody New news) {
//		if (newServiceImpl.insertNew(news)) {
//			return new ResponseEntity<String>("Update!", HttpStatus.CREATED);
//		} else {
//			return new ResponseEntity<String>("New update Existed!", HttpStatus.BAD_REQUEST);
//		}
//	}

	

	// admin thêm tin mới
	@RequestMapping(value = "/admin/news", method = RequestMethod.POST)
	public ResponseEntity<String> createNews(@RequestBody New news) {
		if (newServiceImpl.insertNew(news)) {
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("New Existed!", HttpStatus.BAD_REQUEST);
		}
	}

	// admin quan li tin tức
	@GetMapping("/admin/news")
	public ResponseEntity<Map<String, Object>> getAllNewAdmin(
			@RequestParam(value = "page", defaultValue = "1") int page) {
		return newServiceImpl.gellAll(page);
	}

	// admin xóa tin tức
	@DeleteMapping("/admin/news/{id}")
	public ResponseEntity<String> deleteNews(@PathVariable Long id) {
		if (newServiceImpl.deleteNew(id)) {
			return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("New Existed!", HttpStatus.BAD_REQUEST);
	}

}
