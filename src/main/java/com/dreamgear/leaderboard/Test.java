package com.dreamgear.leaderboard;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class Test {
	
	List<Integer> dataList = new ArrayList<Integer>();
	
	/**
	 * 二分法查找排名
	 * @param scores
	 * @return
	 */
	public int GetRank(int scores){
		int ret = 0;
		
		int lastrank = dataList.size() - 1;
		int nowrank = 0;
		
		if(lastrank <= 0) return ret;
		while(lastrank - nowrank > 1){

			int rank = nowrank + (lastrank - nowrank) / 2;
			
			if(dataList.get(rank) < scores){
				lastrank = rank;
			}else{
				nowrank = rank;
			}
		}
		if(dataList.get(nowrank) > scores){
			ret = lastrank;
		}else{
			ret = nowrank;
		}
		return ret;
	}
	
	public Test(){
		dataList.add(90);
		dataList.add(80);
		dataList.add(10);
		
		int ret = this.GetRank(90);
		System.out.println("ret : " + ret);
		dataList.add(2,1);
		System.out.println("list : " + JSON.toJSONString(dataList));
	}
	
	public static void main( String[] args )
	{
		new Test();
	}
	
	
}
