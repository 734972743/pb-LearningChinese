package com.pb.weixin.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.Word;
import com.pb.weixin.vo.UserHistory;

public interface IUserHistorySongDao extends BaseDao<UserHistory> {

	
	
	//根据用户ID来查询自己收听过的歌曲历史信息 
	public List<UserHistory> getUserHistoryByUserId(@Param("userId") int userId);
	
	
	//根据用户id和歌曲id来查找歌曲
	public List<UserHistory> getUserHistoryByUserIdAndWordId(@Param("userId") int userId, @Param("wordId") int wordId);
	
	public int addUserHistory(@Param("t") UserHistory t);
	
	//根据用户ID来清空他所有的历史记录
	public int deleteAllHistoryByUserId(@Param("userId") int userId);	
	
	public int updateHistoryByUhsId(@Param("historyDate") Date historyDate, @Param("uhsId") int uhsId);
}
