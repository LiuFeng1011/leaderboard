package com.dreamgear.db.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dreamgear.leaderboard.Rank.RankData;

public class RankDataMapper implements RowMapper<RankData> {

	public RankData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		RankData data = new RankData();
		
		data.setUid(rs.getLong("uid"));
		data.setUname(rs.getString("uname"));
		data.setHead(rs.getInt("head"));
		data.setScores(rs.getInt("scores"));
		data.setUpdateTime(rs.getLong("updateTime"));
		data.setOtherdata(rs.getString("otherdata"));
		
		return data;
	}
	
}
