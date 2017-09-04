package com.dreamgear.http.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dreamgear.http.handler.HttpHandler;
import com.dreamgear.http.request.HttpRequestMessage;
import com.dreamgear.http.response.HttpResponseMessage;
import com.dreamgear.leaderboard.App.AppData;
import com.dreamgear.leaderboard.App.AppManager;
import com.dreamgear.leaderboard.Rank.RankManager;
import com.dreamgear.tools.Tools;

public class LBUpdateScores extends HttpHandler{
	private static final Logger logger = LoggerFactory.getLogger(LBUpdateScores.class);

	@Override
	public String handle(HttpRequestMessage request, HttpResponseMessage response) {
		// TODO Auto-generated method stub
		String appids = request.getParameter("appid");
		String uids = request.getParameter("uid");
		String unames = request.getParameter("uname");
		String heads = request.getParameter("head");
		String scores = request.getParameter("scores");
		String otherdata = request.getParameter("otherdata");
		String signature = request.getParameter("signature");

		AppData ad = AppManager.getInstance().getAppMap().get(appids);

		if(ad == null){
			this.SetFail(response, "应用不存在");
			return null;
		}
		
		long appid = -1;
		long uid = -1;
		int head = 0;
		int score = 0;
		
		try{
			appid = Long.parseLong(appids);
			uid = Long.parseLong(uids);
			head = Integer.parseInt(heads);
			score = Integer.parseInt(scores);
		}catch(Exception e){
			this.SetFail(response, "参数不正确");
			return null;
		}
		
		String appkey = ad.getKey();
		
		String string = appids+uids+unames+heads+scores+otherdata+appkey;
		String md5 = Tools.getMd5(string);
		if(!md5.equals(signature)){
			this.SetFail(response, "签名错误");
			return null;
		}
		
		
		RankManager.getInstance().AddScores(appid, uid, unames, head, score, otherdata);
		this.SetSuccess(response, "success");
		
		return null;
	}


}
