package com.dreamgear.leaderboard.Rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.dreamgear.db.DBManager;
import com.dreamgear.leaderboard.GameConfig;
import com.dreamgear.leaderboard.App.AppData;
import com.dreamgear.utils.JsonUtil;
import com.dreamgear.utils.TimeUtils;

public class Rank {
	private static final Logger logger = LoggerFactory.getLogger(Rank.class);
	AppData data;
	
	Vector<RankData> dataList = new Vector<RankData>();
	public Rank(AppData data){
		this.data = data;
		
		//加载数据
		List<RankData> list = DBManager.getInstance().getRankDAO().getRankList(data.getAppname());
		for(int i = 0 ; i < list.size() ; i ++){
			dataList.add(list.get(i));
		}
	}
	
	//更新一个分数
	public void AddScores(long uid,String uname,int head,int scores,String otherdata){
		RankData data = null;
		
		//更新排名
		int nowrank = GetSelfRank(uid);

		if(nowrank != -1){
			data = dataList.get(nowrank);
			
			if(data.getScores() >= scores){
				return;
			}
			dataList.remove(nowrank);
			
			//更新数据
			data.Update(uname, head, scores, TimeUtils.nowLong(), otherdata);
			
			DBManager.getInstance().getRankDAO().updateRank(data, this.data.getAppname());
		}else{
			data = RankData.Create(uid, uname, head, scores,TimeUtils.nowLong(), otherdata);
			//更新数据
			data.Update(uname, head, scores, TimeUtils.nowLong(), otherdata);
			
			DBManager.getInstance().getRankDAO().addRank(data, this.data.getAppname());
		}

		synchronized (dataList) {
			for(int i = 0 ; i < dataList.size() ; i ++){
				if(data.scores > dataList.get(i).getScores()){
					dataList.add(i, data);
					return;
				}
			}
		}
		dataList.add(data);
	}

	/**
	 * 获取排行榜数据
	 * @param uid
	 * @param type 0,表示从第一名开始获取 1，表示获取自己排名附近的玩家 2，分页
	 * @param count 获取排行的个数 type为2时表示页数
	 * @return
	 */
	public List<RankData> GetRankData(long uid,int type,int count){

		int myrank = GetSelfRank(uid);
		
		int startrank = 0;
		
		if(type == 1 && myrank != -1){
			startrank = myrank - (count / 2);
			startrank = Math.max(0, startrank);
		}else if(type == 2){
			startrank = count * GameConfig.lb_page_count;
			count = 30;
		}
		
		List<RankData> ret = new ArrayList<RankData>();

		synchronized (dataList) {
			for(int i = startrank ; i < startrank + count ; i ++){
				if(i >= dataList.size() ){
					break;
				}
				dataList.get(i).setRank(i+1);
				ret.add(dataList.get(i));
			}
			if(myrank != -1){
				ret.add((RankData) dataList.get(myrank).clone());
			}else{
				RankData selfdata = RankData.Create(-1, "", 0, 0, 0, "");
				ret.add(selfdata);
			}
		}
		logger.info(JsonUtil.ObjectToJsonString(ret));
		return ret;
	}
	
	//获取自己的排名
	public int GetSelfRank(long uid){
		synchronized (dataList) {
			for(int i = 0 ; i < dataList.size() ; i ++){
				if(dataList.get(i).getUid() == uid){
					return i;
				}
			}
		}
		return -1;
	}
	
	//获取全部数据
	public int GetAllRankCount(){
		return dataList.size();
	}

	/**
	 * 删除一条数据
	 * @param uid
	 * @return
	 */
	public boolean DeleteData(long uid){
		synchronized (dataList) {
			for(int i = 0 ; i < dataList.size() ; i ++){
				if(dataList.get(i).getUid() == uid){
					DBManager.getInstance().getRankDAO().deleteData(this.data.getAppname(), uid);
					dataList.remove(i);
					return true;
				}
			}
		}
		return true;
	}
	
	/**
	 * 清空排行榜
	 * @return
	 */
	public boolean ClearLB(){
		DBManager.getInstance().getRankDAO().ClearData(this.data.getAppname());
		dataList.clear();
		return true;
	}
	
//	public int GetSelfRank(long uid){
//		if(dataList.size() == 0) return -1;
//		if(!dataMap.containsKey(uid)){
//			return -1;
//		}
//		RankData data = dataMap.get(uid);
//		int nowrank = GetRank(data.scores);
//		logger.info("selfe rank : " + nowrank);
//		//这里考虑的是并列的情况
//		//由于GetRank返回的是此分数最后一个排名,所以这里只要向上遍历即可
//		for(int i = nowrank ; i >= 0 ; i --){
//			if(dataList.get(i).uid == uid) return i;
//			if(dataList.get(i).scores != data.scores ){
//				break;
//			}
//		}
//		return -1;
//	}
	
	
	/**
	 * 二分法查找排名
	 * @param scores
	 * @return 返回此分数最后一个排名
	 */
//	public int GetRank(int scores){
//		int ret = 0;
//		
//		int lastrank = dataList.size() - 1;
//		int nowrank = 0;
//		
//		if(lastrank <= 0) return ret;
//		while(lastrank - nowrank > 1){
//
//			int rank = nowrank + (lastrank - nowrank) / 2;//二分
//			
//			if(dataList.get(rank).getScores() < scores){
//				lastrank = rank;
//			}else{
//				nowrank = rank;
//			}
//		}
//		if(dataList.get(nowrank).getScores() > scores){
//			ret = lastrank;
//		}else{
//			ret = nowrank;
//		}
//		return ret;
//	}
	
	
}
