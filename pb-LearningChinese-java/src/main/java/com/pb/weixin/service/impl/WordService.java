package com.pb.weixin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.api.gateway.demo.util.HttpUtils;
import com.pb.weixin.dao.IWordDao;
import com.pb.weixin.service.IWordService;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Word;

@Service
public class WordService extends BaseService<Word> implements IWordService {

	@Autowired
	private IWordDao songDao;
	
	@Override
	public List<Word> getWordsAll() {
		// TODO Auto-generated method stub
		return songDao.getWordsAll();
	}

	@Override
	public List<Word> getWordsBy(Word word) {
		// TODO Auto-generated method stub
		return songDao.getWordsBy(word);
	}

	@Override
	public int addWord(Word word) {
		// TODO Auto-generated method stub
		return songDao.addWord(word);
	}

	@Override
	public int updateWord(Word word) {
		// TODO Auto-generated method stub
		return songDao.updateWord(word);
	}

	@Override
	public int deleteWord(Word word) {
		// TODO Auto-generated method stub
		return songDao.deleteWord(word);
	}

	@Override
	public String wordToPinyin(String word,String tone, String type) {
		// TODO Auto-generated method stub
		
		String host = "https://pinyin.market.alicloudapi.com";
	    String path = "/pinyin";
	    String method = "POST";
	    String appcode = "dd9a023528094a1898d5a214bd84e1bf";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    //根据API的要求，定义相对应的Content-Type
	    headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	    Map<String, String> querys = new HashMap<String, String>();
	    Map<String, String> bodys = new HashMap<String, String>();
	    bodys.put("src", word);
	    bodys.put("tone", tone);
	    bodys.put("type", type);


	    try {
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//	    	System.out.println(response.toString());
	    	//获取response的body
//	    	System.out.println(EntityUtils.toString(response.getEntity()));
	    	
	    	return EntityUtils.toString(response.getEntity());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		return null;
	}

	@Override
	public String pinyinToWord(String pinyin, String tone, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	



	

	

}
