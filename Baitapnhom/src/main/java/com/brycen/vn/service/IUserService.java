package com.brycen.vn.service;

import java.util.List;

import com.brycen.vn.dto.UserDTO;
import com.brycen.vn.entity.User;

public interface IUserService {
	boolean checkLogin(String username, String password);
	
	User loadUserByUsername(String username);
	
	List<UserDTO> getAll();
	
	UserDTO findByid(Long id);
	
	boolean register(User user);
	

}
