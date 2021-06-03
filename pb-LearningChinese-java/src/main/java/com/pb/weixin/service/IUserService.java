package com.pb.weixin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.User;

public interface IUserService {

	
	public List<User> getUsers(User user);
	
	
	public int getByLoginId(Integer loginId);
	
	public int  saveUser(User user);
	
	
	//根据条件动态修改user
	public void updateUserByCondition(User user);
	
	public void deleteUserById(User user);
	
	
	//根据openid来查找用户
	public List<User> getUsersByOpenid(String openid);
}
