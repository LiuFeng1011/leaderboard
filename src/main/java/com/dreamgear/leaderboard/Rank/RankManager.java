package com.dreamgear.leaderboard.Rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.dreamgear.leaderboard.App.AppData;
import com.dreamgear.leaderboard.App.AppManager;

public class RankManager {
	private static RankManager instance = null;
	
	public static RankManager getInstance()
	{
	    if (instance == null)
	    {
	      instance = new RankManager();
	    }

	    return instance;
	}
	
	Map<Long,Rank> rankMap = new HashMap<Long,Rank>();
	
	public void LoadRank(AppData data){
		Rank rank = new Rank(data);
		rankMap.put(data.getId(), rank);
	}
	
	/**
	 * 新增或更新一个玩家的分数
	 * @param appid
	 * @param uid
	 * @param uname
	 * @param head
	 * @param scores
	 * @param otherdata
	 */
	public void AddScores(long appid,long uid,String uname,int head,int scores,String otherdata){
		if(!rankMap.containsKey(appid)){
			return;
		}
		
		rankMap.get(appid).AddScores(uid, uname, head, scores, otherdata);
	}
	
	/**
	 * 获取排行榜
	 * @param appid
	 * @param uid
	 * @param type 0,表示从第一名开始获取 1，表示获取自己排名附近的玩家 2，分页
	 * @param count 获取排行的个数 type为2时表示页数
	 * @return
	 */
	public List<RankData> GetRank(long appid,long uid,int type,int count){
		if(!rankMap.containsKey(appid)){
			return new ArrayList<RankData>();
		}
		
		List<RankData> ret = rankMap.get(appid).GetRankData(uid, type, count);
		
		return ret;
	}
	
	/**
	 * 删除一条数据
	 * @param uid
	 * @return
	 */
	public boolean DeleteData(long appid,long uid){
		if(!rankMap.containsKey(appid)){
			return false;
		}
		
		return rankMap.get(appid).DeleteData(uid);
	}
	
	/**
	 * 清空排行榜
	 * @return
	 */
	public boolean ClearLB(long appid){
		if(!rankMap.containsKey(appid)){
			return false;
		}
		
		return rankMap.get(appid).ClearLB();
	}
	
	/**
	 * 获取排行数量
	 * @param appid
	 * @return
	 */
	public int GetAllRankCount(long appid){
		if(!rankMap.containsKey(appid)){
			return 0;
		}
		return rankMap.get(appid).GetAllRankCount();
	}
	
}
