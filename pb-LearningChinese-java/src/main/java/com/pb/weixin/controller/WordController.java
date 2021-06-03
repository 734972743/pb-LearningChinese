package com.pb.weixin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pb.weixin.service.IWordService;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Pinyin;
import com.pb.weixin.vo.Word;

@RestController
@RequestMapping("/word")
public class WordController {
	
	@Autowired
	private IWordService wordService;
	

	@RequestMapping(value="/getSongsAll", method=RequestMethod.GET)
	public BaseResult<List<Word>> getSongsAll(){
		BaseResult<List<Word>> result = new BaseResult<List<Word>>();
		try {
			List<Word> songs = wordService.getWordsAll();
			result.setCode(200);
			result.setData(songs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setData(null);
		}
		return result;
	}
	
	
	//根据条件查询并带分页效果
	@RequestMapping(value="/queryListPage/{curPage}/{pageSize}", method=RequestMethod.POST)
	public BaseResult<List<Word>> queryListPage(@PathVariable("curPage") int curPage, @PathVariable("pageSize")int pageSize, @RequestBody Word song){
		Page page = new Page(curPage,pageSize);
		
		BaseResult<List<Word>> result = new BaseResult<List<Word>>();
		
		result = (BaseResult<List<Word>>) wordService.queryListPage(page,song);
		
		
		return result;
	}
	
	
	
	@RequestMapping(value="/getSongsBy", method=RequestMethod.POST)
	public BaseResult<List<Word>> getSongsBy(@RequestBody Word song){
		BaseResult<List<Word>> result = new BaseResult<List<Word>>();
		try {
			List<Word> songs = wordService.getWordsBy(song);
			result.setCode(200);
			result.setData(songs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setData(null);
		}
		return result;
	}
	
	
	//根据用户上传歌曲
//	@RequestMapping(value="/addSong/{singerName}", method=RequestMethod.POST)
//	public BaseResult<Integer> addSong(@PathVariable("singerName") String singerName, @RequestBody Word song){
//		BaseResult<Integer> result = new BaseResult<Integer>();
//		int singerId = 0 ;
//		try {
//			if(!singerName.equals("")) {
//				//首先先存歌手信息
//				Singer singer = new Singer();
//				singer.setSingerName(singerName);
//				List<Singer> singers = singerService.getSingersBy(singer);
//				if(singers.size() == 0) {
//					singerId = singerService.addSingerName(singer);
//				}
//			}
//			
//			//再保存歌曲信息
//			if(singerId > 0) {
//				song.setSingerId(singerId);
//			}
//			int data = songService.addSong(song);
//			if(data>0) {
//				result.setCode(200);
//				result.setData(data);
//			}else {
//				result.setCode(500);
//				result.setData(null);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			result.setCode(500);
//			result.setData(null);
//		}
//		return result;
//	}

	
	
	@RequestMapping(value="/wordToPinyin", method=RequestMethod.POST)
	public BaseResult<Pinyin> wordToPinyin2(@RequestBody Pinyin py){
//		System.out.println(py.getText());
		
		System.out.println(py.getText());
		BaseResult<Pinyin> result = new BaseResult<Pinyin>();
		try {
			
			String pyStr = wordService.wordToPinyin(py.getText(), py.getTong(), py.getType());
			py.setPyStr(pyStr);
			result.setCode(200);
			result.setData(py);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setData(null);
		}
		return result;
	}
	
	
	
}
