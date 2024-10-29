package com.user.model;

import java.util.*;

public interface UserDAO_interface {
	public void insert(UserVO userVO);

	public void update(UserVO userVO);

	public void delete(Integer userId);

	public UserVO findByPrimaryKey(Integer userId);

	public List<UserVO> getAll();
	
	public UserVO compare(String email);
}
