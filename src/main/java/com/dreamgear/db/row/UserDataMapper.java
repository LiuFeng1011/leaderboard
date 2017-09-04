package com.dreamgear.db.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dreamgear.leaderboard.User.UserData;

public class UserDataMapper  implements RowMapper<UserData>{

	public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		UserData data = new UserData();
		data.setId(rs.getLong("id"));
		data.setUname(rs.getString("uname"));
		data.setCreatetime(rs.getLong("createtime"));
		
		return data;
	}

}
