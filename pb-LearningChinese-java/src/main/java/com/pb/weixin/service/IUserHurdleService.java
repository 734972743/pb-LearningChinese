package com.pb.weixin.service;

import java.util.List;

import com.pb.weixin.vo.Hurdle;

public interface IUserHurdleService {

	
	
	public List<Hurdle> getAllCyrics(Hurdle cyric);
	
	public List<Hurdle> getAllCyricsByCyric(Hurdle cyric);
	
	public void updateCyric(Hurdle cyric);
	
	public void deleteCyric(Hurdle cyric);
	
	public void addCyric(Hurdle cyric);
}
