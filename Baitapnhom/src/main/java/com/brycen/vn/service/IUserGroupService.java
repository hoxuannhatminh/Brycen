package com.brycen.vn.service;

import java.util.List;

import com.brycen.vn.entity.UserGroup;

public interface IUserGroupService {
	List<UserGroup> findRole(String username);

}
