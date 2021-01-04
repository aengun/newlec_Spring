package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.dao.jdbc.JdbcMemberDao;
import com.newlecture.web.entity.Member;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = new JdbcMemberDao();
	}
	
	public List<Member> getList() {
		List<Member> list = new ArrayList<>();
		
		String url = "jdbc:oracle:thin:@hi.namoolab.com:1521/xepdb1";
		String sql = "SELECT * FROM MEMBER WHERE PWD = '111'"; 
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "11111");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nicname = rs.getString("nicname");
				String pwd =rs.getString("pwd");
				String name = rs.getString("name");
				
				Member m = new Member();
				m.setId(id);
				m.setNicName(nicname);
				m.setName(name);
				m.setPwd(pwd);
				
				list.add(m);
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

	public boolean isValid(String uid, String pwd) {
		
		Member member = memberDao.get(uid);
		
		if(member == null) // 회원이 아닌 경우
			return false;
		else if(!member.getPwd().equals(pwd)) // 회원이긴 한데.. 비번이 일치하지 않는 경우
			return false;
		
		return true;
	}
	
}
