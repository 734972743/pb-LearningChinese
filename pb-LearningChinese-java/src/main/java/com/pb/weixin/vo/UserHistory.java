package com.pb.weixin.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//用户收听音乐
public class UserHistory implements Serializable{

	private Integer uhsId ;   //主键ID
	private Integer userId;  //用户ID
	private List<Word> words;  //歌曲ID
	
	
	private Date historyDate;  //收听的时间


	public Integer getUhsId() {
		return uhsId;
	}


	public void setUhsId(Integer uhsId) {
		this.uhsId = uhsId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public List<Word> getWords() {
		return words;
	}


	public void setWords(List<Word> words) {
		this.words = words;
	}


	public Date getHistoryDate() {
		return historyDate;
	}


	public void setHistoryDate(Date historyDate) {
		this.historyDate = historyDate;
	}


	
	
	
}
