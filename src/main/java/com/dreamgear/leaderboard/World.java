package com.dreamgear.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dreamgear.db.DBManager;
import com.dreamgear.http.HttpServer;
import com.dreamgear.leaderboard.App.AppManager;

public class World {

	private static final Logger logger = LoggerFactory.getLogger(World.class);
	
    private static World instance = null;
    
    public static World getInstance()
	{
	    if (instance == null)
	    {
	      instance = new World();
	    }

	    return instance;
	}

	public void start(){
		try{

			logger.info("==============加载游戏配置=============");
			GameConfig.load();
			
			logger.info("==============初始化数据库=============");
			DBManager.getInstance().init();

			logger.info("==============开启http监听=============");
			HttpServer.getInstance().run(); 

			logger.info("==============初始化app管理器=============");
			AppManager.getInstance().Init();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//开启游戏主线程
		new MainLoop().start();
	}

	/**
	 * 游戏主线程
	 * 
	 */
	public class MainLoop extends Thread {
		long tickcount = 0;

		@Override
		public void run() {
			logger.info("启动主线程!");
			while (true) {
				try {
					// 游戏逻辑
					gameTick();
				} catch (Exception e) {
					logger.error("error in MainLoop tick : " + tickcount, e);
					tickcount++;
				}
			}
		}

		/**
		 * 游戏逻辑
		 */
		public void gameTick() {

		}

	}
}
