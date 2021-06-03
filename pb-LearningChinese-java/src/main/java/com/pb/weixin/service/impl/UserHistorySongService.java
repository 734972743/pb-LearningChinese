package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.IWordDao;
import com.pb.weixin.dao.IUserHistorySongDao;
import com.pb.weixin.service.IWordService;
import com.pb.weixin.service.IUserHistoryService;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Word;
import com.pb.weixin.vo.UserHistory;

@Service
public class UserHistorySongService extends BaseService<UserHistory> implements IUserHistoryService {

	@Autowired
	private IUserHistorySongDao userHistorySongDao;

	@Override
	public List<UserHistory> getUserHistoryByUserId(int userId) {
		// TODO Auto-generated method stub
		return userHistorySongDao.getUserHistoryByUserId(userId);
	}

	@Override
	public int addUserHistory(UserHistory t) {
		// TODO Auto-generated method stub
		return userHistorySongDao.addUserHistory(t);
	}

	@Override
	public int deleteAllHistoryByUserId(int userId) {
		// TODO Auto-generated method stub
		return userHistorySongDao.deleteAllHistoryByUserId(userId);
	}

	@Override
	public UserHistory getUserHistoryByUserIdAndWordId(UserHistory t) {
		// TODO Auto-generated method stub
		List<UserHistory> userHistorySongs = userHistorySongDao.getUserHistoryByUserIdAndWordId(t.getUserId(), t.getWords().get(0).getWordId());
		if(userHistorySongs.size()>0) {
			return userHistorySongs.get(0);
		}else {
			return null;
		}
	}

	@Override
	public int updateHistoryByUhsId(UserHistory t) {
		// TODO Auto-generated method stub
		return userHistorySongDao.updateHistoryByUhsId(t.getHistoryDate(), t.getUhsId());
	}
	


	

	

}
