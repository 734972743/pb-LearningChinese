package com.pb.weixin.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  用户关卡表
 * @author web1
 *
 */
public class UserHurdle implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)   //表示是ID自增
	private Integer id;
	private String userId;   //用户id
	private String hurdleId;   //关卡id
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHurdleId() {
		return hurdleId;
	}
	public void setHurdleId(String hurdleId) {
		this.hurdleId = hurdleId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
	
}
