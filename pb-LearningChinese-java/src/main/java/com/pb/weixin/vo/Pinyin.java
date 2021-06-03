package com.pb.weixin.vo;

public class Pinyin {

	
	public String text;  //文字
	public String tong = "1";
	public String type = "alt";
		
	public String pyStr; //拼音字符串
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTong() {
		return tong;
	}
	public void setTong(String tong) {
		this.tong = tong;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getPyStr() {
		return pyStr;
	}
	public void setPyStr(String pyStr) {
		this.pyStr = pyStr;
	}
	
	public Pinyin() {
		
	}
	
	public Pinyin(String text, String tong, String type) {
		super();
		this.text = text;
		this.tong = tong;
		this.type = type;
	}
	
	
	
}
