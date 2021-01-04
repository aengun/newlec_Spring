package com.newlecture.web.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

import oracle.jdbc.OraclePreparedStatement;

public class JdbcNoticeDao implements NoticeDao {

	@Override
	public int insert(Notice notice) {
		int result = 0;

		String url = DBContext.URL;
		String sql = "INSERT INTO NOTICE(TITLE, CONTENT, WRITER_ID) VALUES(?,?,?) RETURNING ID INTO ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			OraclePreparedStatement st = (OraclePreparedStatement) con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getWriterId());
			st.registerReturnParameter(4, Types.INTEGER);

			st.execute();
			ResultSet rs = st.getReturnResultSet();
			rs.next();
			result = rs.getInt(1);
			System.out.println("result : " + result);
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Notice notice) {
		int result = 0;

		String url = DBContext.URL;
		String sql = "UPDATE NOTICE SET TITLE=?, CONTENT=? WHERE ID=?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setInt(3, notice.getId());
			// ResultSet rs = st.executeQuery(); // select 문장에만
			result = st.executeUpdate(); // insert, update, delete 문장일 때
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(int id) {
		int result = 0;

		String url = DBContext.URL;
		String sql = "DELETE FROM NOTICE WHERE ID=?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "11111");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			// ResultSet rs = st.executeQuery(); // select 문장에만
			result = st.executeUpdate(); // insert, update, delete 문장일 때
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Notice get(int id) {
		Notice n = null;

		String url = DBContext.URL;
		String sql = "SELECT * FROM NOTICE WHERE ID=" + id;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "11111");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				String title = rs.getString("title");
				String writerId = rs.getString("writer_id");
				String content = rs.getString("content");
				// content = content.replace("\r\n", "<br >");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");

				n = new Notice(id, title, writerId, content, regdate, hit, files);

			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public List<Notice> getList() {
		List<Notice> list = new ArrayList<>();

		String url = DBContext.URL;
		String sql = "SELECT * FROM NOTICE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writerId = rs.getString("writer_id");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");

				Notice n = new Notice(id, title, writerId, content, regdate, hit, files);

				list.add(n);
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Notice> getList(int startIndex, int endIndex, String field, String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notice> getList(int startIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeView> getViewList() {

		return getViewList(1, 10, "TITLE", "");
	}

	@Override
	public List<NoticeView> getViewList(int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		return getViewList(startIndex, endIndex, "TITLE", "");
	}

	@Override
	public List<NoticeView> getViewList(int startIndex, int endIndex, String field, String query) {

		List<NoticeView> list = new ArrayList<NoticeView>();

		String sql = "SELECT * " + "FROM (" + "    SELECT ROWNUM NUM, N.* " + "    FROM ("
				+ "        SELECT * FROM NOTICE_VIEW ORDER BY REGDATE DESC" + "    ) N" + ") WHERE NUM BETWEEN ? AND ?";

		String url = DBContext.URL;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, startIndex);
			st.setInt(2, endIndex);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writerId = rs.getString("writer_id");
				// String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				String writerName = rs.getString("writer_name");
				int cmtCount = rs.getInt("cmt_count");

				NoticeView n = new NoticeView(id, title, writerId, "", regdate, hit, files, writerName, cmtCount);

				list.add(n);
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Notice getLast() {
		Notice n = null;

		String url = DBContext.URL;
		String sql = "SELECT * FROM NOTICE WHERE ID = (SELECT MAX(ID) FROM NOTICE)";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "11111");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writerId = rs.getString("writer_id");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");

				n = new Notice(id, title, writerId, content, regdate, hit, files);

			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}

}
