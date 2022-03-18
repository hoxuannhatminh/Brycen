package com.brycen.vn.service.iml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.vn.entity.UserGroup;
import com.brycen.vn.repositories.UserGroupRepository;
import com.brycen.vn.service.IUserGroupService;

@Service
public class UserGroupImpl implements IUserGroupService {

	@Autowired
	private UserGroupRepository userGroupRepository;

	@Override
	public List<UserGroup> findRole(String username) {
		return userGroupRepository.findByName(username);
	}

}
