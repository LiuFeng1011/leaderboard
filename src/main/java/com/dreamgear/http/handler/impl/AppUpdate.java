package com.dreamgear.http.handler.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dreamgear.http.handler.HttpHandler;
import com.dreamgear.http.request.HttpRequestMessage;
import com.dreamgear.http.response.HttpResponseMessage;
import com.dreamgear.leaderboard.App.AppManager;

public class AppUpdate extends HttpHandler{
	private static final Logger logger = LoggerFactory.getLogger(AppUpdate.class);

	@Override
	public String handle(HttpRequestMessage request, HttpResponseMessage response) {
		String appid = request.getParameter("appid");
		String appname = request.getParameter("appname");
		String resettime = request.getParameter("resettime");
		long resettimel = -1;
		long appidl = -1;
		try{
			appidl = Long.parseLong(appid);
			resettimel = Long.parseLong(resettime);
		}catch(Exception e){
			this.SetFail(response, "参数不正确");
			return null;
		}
		
		boolean succ = AppManager.getInstance().UpdateApp(appidl,appname, resettimel);
		
		if(succ){
			this.SetSuccess(response, new HashMap<String,Object>());
		}else{
			this.SetFail(response, "修改失败");
		}

		return null;
	}
}
