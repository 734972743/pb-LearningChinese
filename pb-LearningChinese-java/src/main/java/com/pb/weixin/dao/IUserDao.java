package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.pb.weixin.vo.User;

public interface IUserDao extends JpaRepository<User, Integer>{

	//修改
//	@Transactional
//	@Modifying
//	@Query("update t_user u set u.nickname =?1 where u.userId=?2")
//	public void updateUserByCondition(String nickname, Integer userId);
	
	//根据openid来查找用户
	public List<User> findByOpenid(String openid);
}
