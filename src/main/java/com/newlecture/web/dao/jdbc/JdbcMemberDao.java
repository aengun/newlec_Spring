package com.newlecture.web.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

public class JdbcMemberDao implements MemberDao {

	@Override
	public Member get(String nicname) {
		Member m = null;
		
		String url = DBContext.URL;
		String sql = "SELECT * FROM MEMBER WHERE NICNAME='"+nicname+"'"; 
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "11111");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);	
			
			if(rs.next()) {
				 int id = rs.getInt("id");
                String nicName = rs.getString("nicName");
                String pwd = rs.getString("pwd");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String birthday = rs.getString("birthday");    
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String regdate = rs.getString("regdate");
               
               m = new Member(
                      id,
                      nicname,
                      pwd,
                      name,
                      gender,
                      birthday,
                      phone,
                      email,
                      regdate
               );

				
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
		return m;
	}

}
