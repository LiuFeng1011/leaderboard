package com.dreamgear.db.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dreamgear.leaderboard.App.AppData;


public class AppDataMapper  implements RowMapper<AppData>{

	public AppData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub

		AppData pd = new AppData();
		pd.setId(rs.getLong("id"));
		pd.setAppname(rs.getString("appname"));
		pd.setCreatetime(rs.getLong("createtime"));
		pd.setKey(rs.getString("appkey"));
		pd.setResettime(rs.getLong("resettime"));
		return pd;
	}

}
