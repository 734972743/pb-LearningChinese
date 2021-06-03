package com.pb.weixin.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

//@Transient 在 import的时候要注意引用的是 javax.persistence这个包下的

@Entity
@Table(name="t_user")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)   //表示是ID自增
	@Column(name = "user_id")
	private Integer userId;   //用户编号
	
	private String nickname;    //用户名称
	private Integer gender  ;  //用户性别  0:男  1：女
	private String email;      //用户邮箱
	private String phone;       //用户手机
	private Integer userType = 0;       //用户类型编号，默认0; 0:普通用户，1.vip，2.后台管理员admin
	private String sign;         //个人签名
	private String avatarUrl;    //用户头像图片链接地址
	private String province;    //省份
	private String city;   //市
	private String language;    //所用的语言  
	
	@Column(name = "openid")
	private String openid;   // 微信的用户唯一标识
	
	@Transient
	private String code; //登录时获取的 code
	
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JSONField(format="yyyy/MM/dd")
	private Date registationDate;      //注册日期  类型TIMESTAMP（包括年月日，时分秒）
	private Integer userStateId = 0;      //用户状态信息编号（可拓展字段）默认0;0未激活状态（需要邮箱激活）1激活状态  2注销状态（账号废弃）
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Date getRegistationDate() {
		return registationDate;
	}
	public void setRegistationDate(Date registationDate) {
		this.registationDate = registationDate;
	}
	public Integer getUserStateId() {
		return userStateId;
	}
	public void setUserStateId(Integer userStateId) {
		this.userStateId = userStateId;
	}
	
	@Transient
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
       
	
	
}
