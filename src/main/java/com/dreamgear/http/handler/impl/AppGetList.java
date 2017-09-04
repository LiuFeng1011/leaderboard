package com.dreamgear.http.handler.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dreamgear.http.handler.HttpHandler;
import com.dreamgear.http.request.HttpRequestMessage;
import com.dreamgear.http.response.HttpResponseMessage;
import com.dreamgear.leaderboard.App.AppData;
import com.dreamgear.leaderboard.App.AppManager;

public class AppGetList extends HttpHandler{
	private static final Logger logger = LoggerFactory.getLogger(AppGetList.class);
	
	@Override
	public String handle(HttpRequestMessage request, HttpResponseMessage response) {
		Map<String, AppData> list = AppManager.getInstance().getAppMap();
		this.SetSuccess(response,list);
		return null;
	}
}
