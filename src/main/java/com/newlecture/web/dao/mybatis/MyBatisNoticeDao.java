package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

@Repository
public class MyBatisNoticeDao implements NoticeDao {

//	@Autowired
//	private SqlSession session;

	private NoticeDao mapper;

	@Autowired
	public MyBatisNoticeDao(SqlSession session) {
		// 1. Dao를 구현한 것 구현체는 factory 담겨있다. : Mybatis가 Mapper(annotation매핑)나 xml이 읽어서 담아둠
		// 2. 담아둔 객체를 MybatisDao가 가져다 씀
		// 이 때(가져다 쓸 때) 사용되는 도구 : session 도구 : getMapper(매퍼 객체 내놔!)
		// sqlSession : IoC Container에 담겨있음..(autowired)
//		this.session = session;
		mapper = session.getMapper(NoticeDao.class);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS) //전파옵션
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
	public List<Notice> getList(int offset) {
		return null;
	}

	@Override
	public List<Notice> getList(int offset, int size, String field, String query) {
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
	public List<NoticeView> getViewList(int offset, int size) {
		return mapper.getViewList(offset, size, "title", "");
	}

	//이것만 구현할 수 있으면 되는 것일 뿐..
	@Override
	public List<NoticeView> getViewList(int offset, int size, String field, String query) {
		return mapper.getViewList(offset, size, field, query);
	}

	@Override
	public Notice getLast() {
		return null;
	}

	@Override
	public int getCount(String field, String query) {
		return mapper.getCount(field, query);
	}

	@Override
	public Notice getPrev(int id) {
		return mapper.getPrev(id);
	}

	@Override
	public Notice getNext(int id) {
		return mapper.getNext(id);
	}

	@Override
	public int deleteAll(int[] ids) {
		return mapper.deleteAll(ids);
	}

}
