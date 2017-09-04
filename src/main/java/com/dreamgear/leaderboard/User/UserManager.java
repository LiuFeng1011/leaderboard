package com.dreamgear.leaderboard.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dreamgear.db.DBManager;
import com.dreamgear.leaderboard.Rank.Rank;
import com.dreamgear.utils.TimeUtils;

public class UserManager {
	private static final Logger logger = LoggerFactory.getLogger(Rank.class);
	
	private static UserManager instance = null;
	
	public static UserManager getInstance()
	{
	    if (instance == null)
	    {
	      instance = new UserManager();
	    }

	    return instance;
	}

	Map<String,UserData> userMap = new HashMap<String,UserData>();
	
	private UserManager(){
		List<UserData> userList = DBManager.getInstance().getUserDAO().getUserList();
		
		for(int i = 0 ; i < userList.size() ; i ++){
			UserData data = userList.get(i);
			userMap.put(data.uname, data);
		}
	}
	
	//获取用户id
	public long GetUserId(String uname){
		if(userMap.containsKey(uname)){
			return userMap.get(uname).getId();
		}
		
		UserData data = new UserData();
		data.setUname(uname);
		data.setCreatetime(TimeUtils.nowLong());
		
		DBManager.getInstance().getUserDAO().addUser(data);
		userMap.put(data.uname, data);
		return data.getId();
	}

}
