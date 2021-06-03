package com.pb.weixin.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

/**
 * 这个是单词表
 * @author web1
 *
 */
public class Word implements Serializable{

	
	private Integer wordId; // 单词编号

	private String wordName; // 单词名称
	private Integer typeId; // 单词类型id
	
	
	public Integer getWordId() {
		return wordId;
	}
	public void setWordId(Integer wordId) {
		this.wordId = wordId;
	}
	public String getWordName() {
		return wordName;
	}
	public void setWordName(String wordName) {
		this.wordName = wordName;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	
		

	
}
