//package com.newlecture.web.dao.spring;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import com.newlecture.web.dao.NoticeDao;
//import com.newlecture.web.entity.Notice;
//import com.newlecture.web.entity.NoticeView;
//
////@Repository // 보따리에 빠짐(Mybatis 사용해야함)
//public class SpringNoticeDao implements NoticeDao {
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//	// 3. JdbcTemplate -> 1. MyBatis -> 2. JPA
//
//	@Override
//	public int insert(Notice notice) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int update(Notice notice) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int delete(int id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Notice get(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Notice> getList() {
//
////		List<Notice> list = new ArrayList<>();
////		list.add(new Notice("제목1", "헤헤"));
////		
////		return list;
//		String sql = "SELECT * FROM NOTICE";
//
//		return jdbcTemplate.query(sql, (rs, rowNum) -> {
//
//			int id = rs.getInt("id");
//			String title = rs.getString("title");
//			String writerId = rs.getString("writer_id");
//			String content = rs.getString("content");
//			Date regdate = rs.getDate("regdate");
//			int hit = rs.getInt("hit");
//			String files = rs.getString("files");
//
//			Notice n = new Notice(id, title, writerId, content, regdate, hit, files);
//			System.out.println(n.getId());
//			return n;
//		});
//
//	}
//
//	@Override
//	public List<Notice> getList(int startIndex, int endIndex, String field, String query) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Notice> getList(int startIndex) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<NoticeView> getViewList() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<NoticeView> getViewList(int startIndex, int endIndex) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<NoticeView> getViewList(int startIndex, int endIndex, String field, String query) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Notice getLast() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
