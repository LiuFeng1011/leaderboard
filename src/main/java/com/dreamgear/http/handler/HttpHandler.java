package com.dreamgear.http.handler;

import java.util.Map;

import com.dreamgear.http.handler.domain.ReturnEntity;
import com.dreamgear.http.request.HttpRequestMessage;
import com.dreamgear.http.response.HttpResponseMessage;
import com.dreamgear.utils.JsonUtil;

public abstract class HttpHandler {
	public  abstract String handle(HttpRequestMessage request,HttpResponseMessage response); 
	public void SetSuccess(HttpResponseMessage response,Object data){
		ReturnEntity ret = ReturnEntity.createSucc(data);
		response.appendBody(JsonUtil.ObjectToJsonString(ret));
		response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
	}
	
	public void SetFail(HttpResponseMessage response,String msg){
		ReturnEntity ret = ReturnEntity.createFail(msg);
		response.appendBody(JsonUtil.ObjectToJsonString(ret));
		response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
	}
}
