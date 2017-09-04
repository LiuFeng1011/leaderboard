package com.dreamgear.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.dreamgear.db.row.AppDataMapper;
import com.dreamgear.leaderboard.App.AppData;

public class AppDAO extends JdbcDaoSupport  {

	private static final String SQL_APP_QUERY = "SELECT * FROM app";
	private static final String SQL_APP_QUERYBYPAGE = "SELECT * FROM app LIMIT ?,?";
	private static final String SQL_APP_DELETE = "DELETE FROM app WHERE id = ?  ";
	private static final String SQL_APP_UPDATE = "UPDATE app SET "
			+ "appname=?,createtime=?,appkey=?,resettime=? "
			+ "WHERE id=?";
	private static final String SQL_APP_INSERT = "INSERT INTO app "
			+ "(appname,createtime,appkey,resettime) "
			+ "VALUES"
			+ "(?,?,?,?)";
	
	private AppDataMapper pdMapper = new AppDataMapper();
	
	//查询语句
	public int GetPlayerCount(String sql){
		try {
			return getJdbcTemplate().queryForInt(sql);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	/**
	 * 获取全部数据
	 * @return
	 */
	public List<AppData> getAppList(){
		try {
			return getJdbcTemplate().query(SQL_APP_QUERY,pdMapper);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 分页查找
	 * @param page
	 * @return
	 */
	public List<AppData> getAppListByPage(int start,int end){
		try {
			return getJdbcTemplate().query(SQL_APP_QUERYBYPAGE,pdMapper,start,end);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteApp(long id){
		try {
			getJdbcTemplate().update(SQL_APP_DELETE,id);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * 添加
	 * @param data
	 * @return
	 */
	public long addApp(final AppData data){
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_APP_INSERT,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, data.getAppname());
					ps.setLong(2, data.getCreatetime());
					ps.setString(3,data.getKey());
					ps.setLong(4,data.getResettime());
					return ps;
				}
			},keyHolder);
			data.setId(keyHolder.getKey().longValue());
			
			return data.getId();
		}catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 更新一个数据
	 * @param data
	 * uname=?,lv=?,head=?,country=?,lvstar=?,item_list=? 
	 */
	public void updateApp(final AppData data){
		//logger.info(" data : " + JSON.toJSONString(data));
		getJdbcTemplate().update(SQL_APP_UPDATE, 
				data.getAppname(),
				data.getCreatetime(),
				data.getKey(),
				data.getResettime(),
				data.getId());
	}
	
}
