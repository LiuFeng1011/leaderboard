package com.dreamgear.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dreamgear.db.dao.AppDAO;
import com.dreamgear.db.dao.RankDAO;
import com.dreamgear.db.dao.UserDAO;




public class DBManager {
	private static DBManager dbManager = null;
	private ApplicationContext context = null;
	private ApplicationContext common_context = null;

	private AppDAO appDAO;
	private RankDAO rankDAO;
	private UserDAO userDAO;
	
	public static DBManager getInstance() {
		if (dbManager == null) {
			dbManager = new DBManager();
		}
		return dbManager;
	}


	public void init() {
		context = new ClassPathXmlApplicationContext("applicationContext-game.xml");

		appDAO = (AppDAO) context.getBean("appDAO");
		rankDAO = (RankDAO) context.getBean("rankDAO");
		userDAO = (UserDAO) context.getBean("userDAO");
	}

	public ApplicationContext getContext() {
		return context;
	}


	public void setContext(ApplicationContext context) {
		this.context = context;
	}


	public AppDAO getAppDAO() {
		return appDAO;
	}


	public void setAppDAO(AppDAO appDAO) {
		this.appDAO = appDAO;
	}


	public RankDAO getRankDAO() {
		return rankDAO;
	}


	public void setRankDAO(RankDAO rankDAO) {
		this.rankDAO = rankDAO;
	}


	public UserDAO getUserDAO() {
		return userDAO;
	}


	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
