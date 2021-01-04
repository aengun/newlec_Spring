package com.newlecture.web.dao.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

@Repository
public class SpringNoticeDao implements NoticeDao {

	private JdbcTemplate 
	
	@Override
	public int insert(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Notice get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notice> getList() {

		List<Notice> list = new ArrayList<>();
		list.add(new Notice("제목1", "헤헤"));
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeView> getViewList(int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeView> getViewList(int startIndex, int endIndex, String field, String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice getLast() {
		// TODO Auto-generated method stub
		return null;
	}

}
