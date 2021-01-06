package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

@Repository
public class MyBatisNoticeDao implements NoticeDao {
	
	@Autowired
	private SqlSession session;

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
		// TODO Auto-generated method stub
		return null;
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
