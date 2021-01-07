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

//	@Autowired
	private SqlSession session;

	private NoticeDao mapper;

	@Autowired
	public MyBatisNoticeDao(SqlSession session) {
		this.session = session;
		mapper = session.getMapper(NoticeDao.class);
	}

	@Override
	public int insert(Notice notice) {
//		NoticeDao mapper = session.getMapper(NoticeDao.class);
		return mapper.insert(notice);
	}

	@Override
	public int update(Notice notice) {
//		NoticeDao mapper = session.getMapper(NoticeDao.class);
		return mapper.update(notice);
	}

	@Override
	public int delete(int id) {
//		NoticeDao mapper = session.getMapper(NoticeDao.class);
		return mapper.delete(id);
	}

	@Override
	public Notice get(int id) {
//		NoticeDao mapper = session.getMapper(NoticeDao.class);
		return mapper.get(id);
	}

	@Override
	public List<Notice> getList() {
//		NoticeDao mapper = session.getMapper(NoticeDao.class);
		return mapper.getList();
	}

	@Override
	public List<Notice> getList(int startIndex, int endIndex, String field, String query) {
		return null;
	}

	@Override
	public List<Notice> getList(int startIndex) {
		return null;
	}

	// Service : 1페이지
	// <---> Dao : SQL을 객체화.. 레코드/컬럼/정렬/집계

	@Override
	public List<NoticeView> getViewList() {
		// mybatis는 오버로드 지원하지 않음 --> 인자가 많은 것을 구현하고 나머지는 기본값을 넘겨준다.
		return mapper.getViewList(1, 10, "title", "");
	}

	@Override
	public List<NoticeView> getViewList(int startIndex, int endIndex) {
		return mapper.getViewList(startIndex, endIndex, "title", "");
	}

	//이것만 구현할 수 있으면 되는 것일 뿐..
	@Override
	public List<NoticeView> getViewList(int startIndex, int endIndex, String field, String query) {
		return mapper.getViewList(startIndex, endIndex, field, query);
	}

	@Override
	public Notice getLast() {
		return null;
	}

}
