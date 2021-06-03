package com.pb.weixin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.Word;

public interface IWordService extends IBaseService<Word> {

	/**
	 * 查询所有的音乐
	 * @return
	 */
	public List<Word> getWordsAll();
	
	/**
	 * 根据条件来查询音乐
	 * @return
	 */
	public List<Word> getWordsBy(Word word);
	
	/**
	 * 增加一首音乐
	 * @param song
	 * @return
	 */
	public int addWord(Word word);
	
	/**
	 * 修改一首音乐
	 * @param song
	 * @return
	 */
	public int updateWord(Word word);
	
	
	/**
	 * 删除一首音乐
	 * @param song
	 * @return
	 */
	public int deleteWord(Word word);
	
	
	/**
	 * 文字转拼音
	 * @param word
	 * @return
	 */
	public String wordToPinyin(String word, String tone, String type);
	
	
	/**
	 * 拼音转文字
	 * @param pinyin
	 * @return
	 */
	public String pinyinToWord(String pinyin, String tone, String type);
	
	
}
