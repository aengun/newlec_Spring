package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

// SQL의 자바 함수화
// 업무는 몰라
public interface NoticeDao {
	int insert(Notice notice);
	int update(Notice notice);
	int delete(int id);
	
	Notice get(int id);
	List<Notice> getList();
	List<Notice> getList(int startIndex, int endIndex, String field, String query);
	List<Notice> getList(int startIndex);
	List<NoticeView> getViewList();
	List<NoticeView> getViewList(int startIndex, int endIndex);
	List<NoticeView> getViewList(int startIndex, int endIndex, String field, String query);
	Notice getLast();
}
