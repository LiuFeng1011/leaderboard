package com.dreamgear.http.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dreamgear.http.handler.HttpHandler;
import com.dreamgear.http.request.HttpRequestMessage;
import com.dreamgear.http.response.HttpResponseMessage;
import com.dreamgear.leaderboard.User.UserManager;

public class UserGetID extends HttpHandler{
	private static final Logger logger = LoggerFactory.getLogger(UserGetID.class);
	
	@Override
	public String handle(HttpRequestMessage request, HttpResponseMessage response) {
		// TODO Auto-generated method stub
		String uname = request.getParameter("uname");
		
		if(uname == null || uname == ""){
			this.SetFail(response, "参数不正确");
			return null;
		}
		
		long ret = UserManager.getInstance().GetUserId(uname);
		
		this.SetSuccess(response, ret);
		
		return null;
	}

}
