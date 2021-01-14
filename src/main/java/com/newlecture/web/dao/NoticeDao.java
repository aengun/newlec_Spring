package com.newlecture.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

//@Mapper // >> MyBatisNoticeDao로 구현
public interface NoticeDao {
	int insert(Notice notice);
	int update(Notice notice);
	int delete(int id);
	
//	@Select("SELECT * FROM NOTICE WHERE ID=#{id}") //키를 심을 때 : ${id} : newlec, 값을 심을 때 : #{id} : 'newlec' 
	Notice get(int id);
	
//	@Results(id="noticeMap" value= { //camel-case 해결법, 여러개인경우
//			@Result(property = "writerId", column = "WRITER_ID")
//	})
	
//	@Select("SELECT * FROM NOTICE")
//	@Result(property = "writerId", column = "WRITER_ID")
//	@Result(property = "regDate", column = "REG_DATE")
	
//	List<Notice> getList();	
//	List<Notice> getList(int startIndex);
//	List<Notice> getList(int startIndex, int endIndex, String field, String query);
	// mysql사용을 위해 인터페이스 인자 변경
	List<Notice> getList();	
	List<Notice> getList(int offset);
	List<Notice> getList(int offset, int size, String field, String query);
	
//	List<NoticeView> getViewList();
//	List<NoticeView> getViewList(int startIndex, int endIndex);
//	List<NoticeView> getViewList(int startIndex, int endIndex, String field, String query);
	// mysql사용을 위해 인터페이스 인자 변경
	List<NoticeView> getViewList();
	List<NoticeView> getViewList(int offset, int size);
	List<NoticeView> getViewList(int offset, int size, String field, String query);
	
	int getCount(String field, String query);
	Notice getLast();
	Notice getPrev(int id);
	Notice getNext(int id);
}
