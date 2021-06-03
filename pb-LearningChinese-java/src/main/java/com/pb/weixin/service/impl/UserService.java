package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.IUserDao;
import com.pb.weixin.service.IUserService;
import com.pb.weixin.vo.User;

@Service
public class UserService implements IUserService {
	
	@Autowired
	public IUserDao userDao;
	
	public List<User> getUsers(User user) {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}


	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		User u = userDao.saveAndFlush(user);
		return u.getUserId();
	}


	@Override
	public void updateUserByCondition(User user) {
		// TODO Auto-generated method stub
//		userDao.updateUserByCondition(user.getNickname(),user.getUserId());
		userDao.saveAndFlush(user);
	}

	@Override
	public void deleteUserById(User user) {
		// TODO Auto-generated method stub
		userDao.delete(user.getUserId());
	}

	@Override
	public int getByLoginId(Integer loginId) {
		// TODO Auto-generated method stub
		User user = userDao.findOne(loginId);
		return user.getUserId();  //根据id来查询
	}

	

	@Override
	public List<User> getUsersByOpenid(String openid) {
		// TODO Auto-generated method stub
		return userDao.findByOpenid(openid);
	}
	
}
