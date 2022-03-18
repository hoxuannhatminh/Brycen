package com.brycen.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.vn.dto.UserDTO;
import com.brycen.vn.entity.User;
import com.brycen.vn.service.iml.JwtService;
import com.brycen.vn.service.iml.UserService;

@RestController
@RequestMapping("/congthongtin")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;
	
	//login
	@PostMapping("/login")
	public ResponseEntity<String> LoginUser(@RequestBody User user) {
		String result = "";
		HttpStatus httpStatus = null;
		try {
			System.out.println("username"+user.getUsername() + " password " + user.getPassword());
			if (userService.checkLogin(user.getUsername(), user.getPassword())) {
				result = jwtService.generateTokenLogin(user.getUsername());
				httpStatus = HttpStatus.OK;
			} else {
				result = "Wrong userId and password";
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception ex) {
			result = ex.getMessage();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(result, httpStatus);
	}
	
	
//	// đăng nhập thành công hiển thị profile theo tên đăng nhập
//	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Object> getUserById(@PathVariable Long id) {
//		UserDTO user = userService.findByid(id);
//		if (user != null) {
//			return new ResponseEntity<Object>(user, HttpStatus.OK);
//		}
//		return new ResponseEntity<Object>("Not Found User", HttpStatus.NO_CONTENT);
//	}
//	
//	
//	// Đăng kí tài khoản
//	@RequestMapping(value = "/users", method = RequestMethod.POST)
//	public ResponseEntity<String> createUser(@RequestBody User user) {
//		if (userService.register(user)) {
//			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
//		} else {
//			return new ResponseEntity<String>("User Existed!", HttpStatus.BAD_REQUEST);
//		}
//	}
	
	
	// admin hiển thị danh sách user
		@GetMapping("/admin/users")
		public ResponseEntity<List<UserDTO>> getAllUser() {
			return new ResponseEntity<List<UserDTO>>(userService.getAll(), HttpStatus.OK);
		}

	
	
	


}
