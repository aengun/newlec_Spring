package com.newlecture.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

// @Component // 구성요소 : 스프링 어플리케이션을 구성하는 구성요소 객체라는 의미.
// @Component는 @Controller, @Service, @Repository로 대체할 수 있다.

@Service // IoC Container에 담아서 객체화한다.
public class NoticeServiceImp implements NoticeService{
	
	@Autowired
	private NoticeDao noticeDao; //SpringNoticeDao는 @Repository로
	
	public NoticeServiceImp() {
//		noticeDao = new JdbcNoticeDao();
	}

	public List<Notice> getList(int page, int size, String field, String query) {
		
		int startIndex = 1+(page-1)*size;//1, 11, 21, 31, ...
		int endIndex = page*10;//10,20,30,40,50,60... 
		
		// [*제목][ 하하 ] [검색]
		
//		return noticeDao.getList(startIndex, endIndex, field, query);
		return noticeDao.getList();
	}
	
	public int deleteAll(int[] ids) {
		
		// Dao를 사용하는 이유
		// 1. 협업
		// 2. 재사용
		// 3. 데이터(소스)를 숨기는 것
		
		
		// DELETE NOTICE WHERE ID IN (......);
		//noticeDao.deleteAll(ids);
		
		int result = 0;
		for(int i=0; i<ids.length; i++) {
			int id = ids[i];
			result += noticeDao.delete(id);
		}
		
		return result;
	}
	
	public int hitUp(int id) {
		
		//int result = noticeDao.hitUp(id);
		
		// SELECT * FROM NOTICE WHERE ID=?
		Notice notice  = noticeDao.get(id); 
		notice.setHit(notice.getHit()+1);
		// UPDATE NOTICE SET ... WHERE ID=?
		int result = noticeDao.update(notice);
		
		return result;
	}
	
	public List<NoticeView> getViewList(int page, int size) {
		
		int startIndex = 1+(page-1)*size;//1, 11, 21, 31, ...
		int endIndex = page*10;//10,20,30,40,50,60... 
				
		return noticeDao.getViewList(startIndex, endIndex);
	}
	
	public Notice get(int id) {
		
		return noticeDao.get(id);
	}

	public int insert(Notice notice) {
		return noticeDao.insert(notice);
	}


	public int update(Notice notice) {
		int result = 0;
		
		result = noticeDao.update(notice);
		
		return result;
	}
	
	public int delete(int id) {
		int result =0;
		
		result = noticeDao.delete(id);
		
		
		return result; 
	}

	public int getLastId() {
		Notice n = noticeDao.getLast();
		
		// 업데이트 -> 컬럼
		// 서비스에서는 공개값 업데이트/좋아요 업데이트/조회수 업데이트
		// get -> set -> update();
		return n.getId();
	}

	public List<NoticeView> getViewList(int page, int size, String field, String query) {
//		int startIndex = 1+(page-1)*size;//1, 11, 21, 31, ...
//		int endIndex = page*10;//10,20,30,40,50,60... 
		
		int offset = (page-1) * 10; // page 1 -> 0, 2 -> 10, 3 -> 20
				
		return noticeDao.getViewList(offset, size, field, query);
	}

	@Override
	public int getCount(String field, String query) {
		return noticeDao.getCount(field, query);
	}

	@Override
	public Notice getPrev(int id) {
		return noticeDao.getPrev(id);
	}

	@Override
	public Notice getNext(int id) {
		return noticeDao.getNext(id);
	}

	
}
