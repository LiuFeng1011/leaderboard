package com.dreamgear.http.handler.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dreamgear.http.handler.HttpHandler;
import com.dreamgear.http.handler.domain.ReturnEntity;
import com.dreamgear.http.request.HttpRequestMessage;
import com.dreamgear.http.response.HttpResponseMessage;
import com.dreamgear.leaderboard.App.AppManager;
import com.dreamgear.utils.JsonUtil;

public class AppCreate extends HttpHandler{
	private static final Logger logger = LoggerFactory.getLogger(AppCreate.class);

	@Override
	public String handle(HttpRequestMessage request, HttpResponseMessage response) {
		String appname = request.getParameter("appname");
		String resettime = request.getParameter("resettime");
		long resettimel = -1;
		try{
			resettimel = Long.parseLong(resettime);
		}catch(Exception e){
			this.SetFail(response, "参数不正确");
			return null;
		}
		
		AppManager.getInstance().CreateApp(appname, resettimel);
		
		// TODO Auto-generated method stub
		Map<String,Object> data = new HashMap<String,Object>();
		
		this.SetSuccess(response, data);
		return null;
	}


}
