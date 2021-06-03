package com.pb.weixin.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 关卡表
 * @author web1
 *
 */
@Entity
@Table(name="t_hurdle")
public class Hurdle implements Serializable{

	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)   //表示是ID自增
	private Integer id;
	private String hurdleName;   //关卡名称
	private String hurdleNumber;   //关卡数
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHurdleName() {
		return hurdleName;
	}
	public void setHurdleName(String hurdleName) {
		this.hurdleName = hurdleName;
	}
	public String getHurdleNumber() {
		return hurdleNumber;
	}
	public void setHurdleNumber(String hurdleNumber) {
		this.hurdleNumber = hurdleNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
}
