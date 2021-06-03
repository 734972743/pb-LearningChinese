package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.HurdleDao;
import com.pb.weixin.service.IHurdleService;
import com.pb.weixin.vo.Hurdle;


@Service
public class HurdleServiceImpl implements IHurdleService {
	
	@Autowired
	private HurdleDao hurdleDao;

	@Override
	public List<Hurdle> getAllCyrics(Hurdle cyric) {
		// TODO Auto-generated method stub
		return hurdleDao.findAll();
	}

	@Override
	public List<Hurdle> getAllCyricsByCyric(Hurdle cyric) {
		// TODO Auto-generated method stub
		
		Example example = Example.of(cyric);
		return hurdleDao.findAll(example);
	}

	@Override
	public void updateCyric(Hurdle cyric) {
		// TODO Auto-generated method stub
		hurdleDao.saveAndFlush(cyric);
	}

	@Override
	public void deleteCyric(Hurdle cyric) {
		// TODO Auto-generated method stub
		hurdleDao.delete(cyric);
	}

	@Override
	public void addCyric(Hurdle cyric) {
		// TODO Auto-generated method stub
		hurdleDao.save(cyric);
	}

}
