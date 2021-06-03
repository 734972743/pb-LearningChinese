package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.Word;

public interface IWordDao extends BaseDao<Word> {

	/**
	 * 查询所有的单词
	 * @return
	 */
	public List<Word> getWordsAll();
	
	/**
	 * 根据条件来查询单词
	 * @return
	 */
	public List<Word> getWordsBy(Word word);
	
	/**
	 * 增加一首单词
	 * @param Word
	 * @return
	 */
	public int addWord(@Param("t") Word word);
	
	/**
	 * 修改一首单词
	 * @param Word
	 * @return
	 */
	public int updateWord(@Param("t") Word word);
	
	
	/**
	 * 删除一首单词
	 * @param Word
	 * @return
	 */
	public int deleteWord(@Param("t") Word word);
	
	
	
	
	
	
}
