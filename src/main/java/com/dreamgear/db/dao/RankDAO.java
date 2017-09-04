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

import com.dreamgear.db.row.RankDataMapper;
import com.dreamgear.leaderboard.Rank.RankData;

public class RankDAO extends JdbcDaoSupport   {
	private static final String SQL_CREATE_RANK_TABLE = 
			"CREATE TABLE lb_%s ( "
			+ "id BIGINT(20) NOT NULL AUTO_INCREMENT ,"
			+ "uid BIGINT(20) NOT NULL,"
			+ "uname VARCHAR(32) NOT NULL,"
			+ "head INT(11),"
			+ "scores INT(11),"
			+ "updateTime BIGINT(20),"
			+ "otherdata VARCHAR(128) ,"
			+ "PRIMARY KEY (id));";

	private static final String SQL_RANK_QUERY = "SELECT * FROM lb_%s ORDER BY scores DESC";
	private static final String SQL_RANK_UPDATE = "UPDATE lb_%s SET "
			+ "uname=?,head=?,scores=?,updateTime=?,otherdata=? "
			+ "WHERE uid=?";
	private static final String SQL_RANK_INSERT = "INSERT INTO lb_%s "
			+ "(uid,uname,head,scores,updateTime,otherdata) "
			+ "VALUES"
			+ "(?,?,?,?,?,?)";
	
	private static final String SQL_RANK_DELETE = "DELETE FROM lb_%s WHERE uid = ?  ";

	private static final String SQL_RANK_CLEAR = "DELETE FROM lb_%s";
	

	private RankDataMapper dataMapper = new RankDataMapper();

	//查询语句
	public int GetPlayerCount(String sql){
		try {
			return getJdbcTemplate().queryForInt(sql);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	/**
	 * 创建新表
	 * @param tablename
	 */
	public void CreateTable(String tablename){
		String sql = String.format(SQL_CREATE_RANK_TABLE, tablename);
		
		getJdbcTemplate().execute(sql);
	}

	/**
	 * 获取全部数据
	 * @return
	 */
	public List<RankData> getRankList(String tablename){
		try {
			return getJdbcTemplate().query(String.format(SQL_RANK_QUERY, tablename),dataMapper);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 更新一个数据
	 * @param data
	 */
	public void updateRank(final RankData data,String tablename){
		//logger.info(" data : " + JSON.toJSONString(data));
		getJdbcTemplate().update(String.format(SQL_RANK_UPDATE, tablename), 
				data.getUname(),
				data.getHead(),
				data.getScores(),
				data.getUpdateTime(),
				data.getOtherdata(),
				data.getUid());
	}

	/**
	 * 添加
	 * @param data
	 * uid,uname,head,scores,updateTime,otherdata
	 * @return
	 */
	public long addRank(final RankData data,final String tablename){
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(String.format(SQL_RANK_INSERT, tablename),
							Statement.RETURN_GENERATED_KEYS);
					
					ps.setLong(1, data.getUid());
					ps.setString(2, data.getUname());
					ps.setInt(3,data.getHead());
					ps.setLong(4,data.getScores());
					ps.setLong(5,data.getUpdateTime());
					ps.setString(6,data.getOtherdata());
					return ps;
				}
			},keyHolder);

			return keyHolder.getKey().longValue();
		}catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/**
	 * 删除
	 * @param roleId
	 */
	public void deleteData(final String tablename,final long uid){
		try {
			getJdbcTemplate().update(String.format(SQL_RANK_DELETE, tablename),uid);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * 清空排行榜数据
	 * @param tablename
	 */
	public void ClearData(final String tablename){
		try {
			getJdbcTemplate().update(String.format(SQL_RANK_CLEAR, tablename));
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return;
		}
	}
	
}
