package com.pb.weixin.service;

import java.util.List;

import com.pb.weixin.vo.Hurdle;

public interface IHurdleService {

	
	
	public List<Hurdle> getAllCyrics(Hurdle hurdle);
	
	public List<Hurdle> getAllCyricsByCyric(Hurdle hurdle);
	
	public void updateCyric(Hurdle hurdle);
	
	public void deleteCyric(Hurdle hurdle);
	
	public void addCyric(Hurdle hurdle);
}
