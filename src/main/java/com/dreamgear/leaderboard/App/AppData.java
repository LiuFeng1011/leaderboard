package com.dreamgear.leaderboard.App;

public class AppData {
	private long id;
	private String appname;
	private long createtime;
	private String appkey;
	private long resettime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}
	public String getKey() {
		return appkey;
	}
	public void setKey(String key) {
		this.appkey = key;
	}
	public long getResettime() {
		return resettime;
	}
	public void setResettime(long resettime) {
		this.resettime = resettime;
	}
	
	
}
