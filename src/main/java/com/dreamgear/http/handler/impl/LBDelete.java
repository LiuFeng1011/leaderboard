package com.dreamgear.http.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dreamgear.http.handler.HttpHandler;
import com.dreamgear.http.request.HttpRequestMessage;
import com.dreamgear.http.response.HttpResponseMessage;
import com.dreamgear.leaderboard.Rank.RankManager;

public class LBDelete extends HttpHandler{
	private static final Logger logger = LoggerFactory.getLogger(LBDelete.class);

	@Override
	public String handle(HttpRequestMessage request, HttpResponseMessage response) {
		// TODO Auto-generated method stub
		String appids = request.getParameter("appid");
		String uids = request.getParameter("uid");

		long appid = -1;
		long uid = -1;

		try{
			appid = Long.parseLong(appids);
			uid = Long.parseLong(uids);
		}catch(Exception e){
			this.SetFail(response, "参数不正确");
			return null;
		}
		
		RankManager.getInstance().DeleteData(appid, uid);
		
		this.SetSuccess(response, "success");
		
		return null;
	}


}
