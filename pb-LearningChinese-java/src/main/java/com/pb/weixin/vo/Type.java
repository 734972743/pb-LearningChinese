package com.pb.weixin.vo;

import java.io.Serializable;

public class Type implements Serializable{

	private Integer typeId;     //id
	private String typeName;    //类型名称
	
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	

	
	
	
}
