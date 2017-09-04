package com.dreamgear.db;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.dreamgear.leaderboard.App.AppData;
import com.dreamgear.leaderboard.App.AppManager;
import com.dreamgear.leaderboard.Rank.RankData;
import com.dreamgear.utils.TimeUtils;


public class Test {
	private static final Logger logger = LoggerFactory.getLogger(Test.class);
	 public static void main(String[] args) {

			//数据库
			DBManager.getInstance().init();

//			AppData data = new AppData();
//			data.setAppname("test");
//			data.setCreatetime(TimeUtils.nowLong());
//			data.setKey("abc");
//			data.setResettime(123);
//			
//			DBManager.getInstance().getAppDAO().addApp(data);
			
//			List<AppData> datalist = DBManager.getInstance().getAppDAO().getAppList();
//			logger.info(JSON.toJSONString(datalist));

			//创建应用
//			AppManager.getInstance().CreateApp("test1", -1);
//			AppManager.getInstance().CreateApp("test2", -1);
			
			for(int i = 0 ; i < 45 ; i ++){
				RankData rd1 = RankData.Create(i, "a"+i, 123, 324+i*23, TimeUtils.nowLong(), "sss");
				DBManager.getInstance().getRankDAO().addRank(rd1, "app_1");
			}

	 }
}
