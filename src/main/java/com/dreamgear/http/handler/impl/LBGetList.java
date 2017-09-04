package com.dreamgear.http.handler.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dreamgear.http.handler.HttpHandler;
import com.dreamgear.http.request.HttpRequestMessage;
import com.dreamgear.http.response.HttpResponseMessage;
import com.dreamgear.leaderboard.Rank.RankData;
import com.dreamgear.leaderboard.Rank.RankManager;

public class LBGetList extends HttpHandler{
	private static final Logger logger = LoggerFactory.getLogger(LBGetList.class);

	@Override
	public String handle(HttpRequestMessage request, HttpResponseMessage response) {
		// TODO Auto-generated method stub
		String appids = request.getParameter("appid");
		String uids = request.getParameter("uid");
		String types = request.getParameter("type");
		String counts = request.getParameter("count");
		
		long appid = -1;
		long uid = -1;
		int type = 0;
		int count = 0;
		try{
			appid = Long.parseLong(appids);
			uid = Long.parseLong(uids);
			type = Integer.parseInt(types);
			count = Integer.parseInt(counts);
		}catch(Exception e){
			this.SetFail(response, "参数不正确");
			return null;
		}
		
		Map<String,Object> retmap = new HashMap<String,Object>();
		List<RankData> ret = RankManager.getInstance().GetRank(appid, uid, type, count);
		retmap.put("data", ret);
		retmap.put("allcount", RankManager.getInstance().GetAllRankCount(appid));
		
		this.SetSuccess(response, retmap);
		
		return null;
	}

}
