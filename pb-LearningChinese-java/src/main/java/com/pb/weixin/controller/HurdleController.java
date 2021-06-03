package com.pb.weixin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pb.weixin.service.IHurdleService;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Hurdle;

@RestController
@RequestMapping(value="/cyric")
public class HurdleController {

	@Autowired
	private IHurdleService hurdleService;
	
	//根据条件查询并带分页效果
	@RequestMapping(value="/getAllCyricsByCyric", method=RequestMethod.POST)
	public BaseResult<List<Hurdle>> getAllCyricsBy(@RequestBody Hurdle cyric){
		Page page = new Page();
		BaseResult<List<Hurdle>> result = new BaseResult<List<Hurdle>>();
		List<Hurdle> data = new ArrayList<Hurdle>();
		try {
			data =  hurdleService.getAllCyricsByCyric(cyric);
			result.setCode(200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setCode(500);
			e.printStackTrace();
		}
		result.setData(data);
		return result;
	}
	
	
}
