package com.dreamgear.leaderboard.App;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dreamgear.db.DBManager;
import com.dreamgear.leaderboard.GameConfig;
import com.dreamgear.leaderboard.Rank.RankManager;
import com.dreamgear.tools.Tools;
import com.dreamgear.utils.TimeUtils;

public class AppManager {
	private static AppManager instance = null;
	
	public static AppManager getInstance()
	{
	    if (instance == null)
	    {
	      instance = new AppManager();
	    }

	    return instance;
	}
	
	Map<String,AppData> appMap = new HashMap<String,AppData>();
	
	public void Init(){
		List<AppData> appList = DBManager.getInstance().getAppDAO().getAppList();
		
		for(int i = 0 ; i < appList.size() ; i ++){
			AppData data = appList.get(i);
			appMap.put(data.getId()+"", data);
			RankManager.getInstance().LoadRank(data);
			
		}
	}
	
	//创建应用
	public void CreateApp(String appname,long resettime){
		AppData data = new AppData();
		
		data.setAppname(appname);
		data.setCreatetime(TimeUtils.nowLong());
		data.setKey(Tools.getNonceStr(GameConfig.app_key_length));
		data.setResettime(resettime);
		
		long id = DBManager.getInstance().getAppDAO().addApp(data);
		data.setId(id);
		appMap.put(id+"",data);

		DBManager.getInstance().getRankDAO().CreateTable(appname);
		RankManager.getInstance().LoadRank(data);
	}
	
	//更新应用
	public boolean UpdateApp(long appid,String appname,long resettime){
		if(!appMap.containsKey(appid+"")){
			return false;
		}
		AppData data = appMap.get(appid+"");
		
		data.setAppname(appname);
		data.setResettime(resettime);
		
		DBManager.getInstance().getAppDAO().updateApp(data);
		return true;
	}

	//获取应用列表
	public Map<String, AppData> getAppMap() {
		return appMap;
	}

	
	
}
