package com.dreamgear.leaderboard.Rank;

public class RankData implements Cloneable{
	public long uid;
	public String uname;
	public int head;
	public int scores;
	public long updateTime;
	public String otherdata;
	public  int rank;//仅在返回客户端数据时使用
	
	public static RankData Create(long uid,String uname,int head,int scores,long updatetime,String otherdata){
		RankData data = new RankData();
		data.setUid(uid);
		data.setUname(uname);
		data.setHead(head);
		data.setScores(scores);
		data.setUpdateTime(updatetime);
		data.setOtherdata(otherdata);
		return data;
	}
	
	public void Update(String uname,int head,int scores,long updatetime,String otherdata){
		this.uname = uname;
		this.head = head;
		this.scores = scores;
		this.updateTime = updatetime;
		this.otherdata = otherdata;
	}
	@Override  
	public Object clone(){  
		RankData ret = null;  
	    try{  
	    	ret = (RankData)super.clone();  
		}catch(CloneNotSupportedException e) {  
	    	e.printStackTrace();  
		}  
		return ret;  
	}        
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
	}
	public int getScores() {
		return scores;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public String getOtherdata() {
		return otherdata;
	}
	public void setOtherdata(String otherdata) {
		this.otherdata = otherdata;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	
}
