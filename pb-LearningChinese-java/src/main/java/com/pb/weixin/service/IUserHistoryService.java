package com.pb.weixin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.UserHistory;


public interface IUserHistoryService extends IBaseService<UserHistory> {

	
	
	//根据用户ID来查询自己收听过的歌曲历史信息 
	public List<UserHistory> getUserHistoryByUserId(int userId);
	
	//根据用户id和歌曲id来查找歌曲
	public UserHistory getUserHistoryByUserIdAndWordId(UserHistory t);
	
	public int addUserHistory(UserHistory t);
	
	//根据用户ID来清空他所有的历史记录
	public int deleteAllHistoryByUserId(int userId);
	
	//修改
	public int updateHistoryByUhsId(UserHistory t);
}
