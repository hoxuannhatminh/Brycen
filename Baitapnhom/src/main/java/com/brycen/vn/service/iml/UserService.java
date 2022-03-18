package com.brycen.vn.service.iml;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.vn.dto.UserDTO;
import com.brycen.vn.entity.User;
import com.brycen.vn.repositories.UserRepository;
import com.brycen.vn.service.IUserService;

@Service
public class UserService implements IUserService {

	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean checkLogin(String username, String password) {
		User userMySql = userRepository.getByUsernameAndPassword(username, password);
		if (userMySql != null) {
			System.out.println("username2"+ userMySql.getUsername());
			return true;
		}
		return false;
	}

	@Override
	public User loadUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<UserDTO> getAll() {

		List<User> listUser = userRepository.findAll();
		List<UserDTO> listDto = listUser.stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		return listDto;
	}

	@Override
	public UserDTO findByid(Long id) {
		// TODO Auto-generated method stub

		User user = userRepository.findById(id).get();

		UserDTO userDto = modelMapper.map(user, UserDTO.class);

		return userDto;
	}
	


	@Override
	public boolean register(User user) {
		User user1 = userRepository.findByUsername(user.getUsername());
		if(user1==null) {
			userRepository.save(user);
			return true;
		}
		return false;
	}

}
