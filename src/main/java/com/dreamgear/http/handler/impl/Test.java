package com.dreamgear.http.handler.impl;

import java.util.HashMap;
import java.util.Map;

import com.dreamgear.http.handler.HttpHandler;
import com.dreamgear.http.handler.domain.ReturnEntity;
import com.dreamgear.http.request.HttpRequestMessage;
import com.dreamgear.http.response.HttpResponseMessage;
import com.dreamgear.utils.JsonUtil;

public class Test extends HttpHandler{

	@Override
	public String handle(HttpRequestMessage request, HttpResponseMessage response) {
		// TODO Auto-generated method stub

		String test = request.getParameter("test");

		Map<String,Object> data = new HashMap<String,Object>();
		
		data.put("data", "test:"+test);

		ReturnEntity ret = ReturnEntity.createSucc(data);
		response.appendBody(JsonUtil.ObjectToJsonString(ret));
		response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
		
		return null;
	}

}
