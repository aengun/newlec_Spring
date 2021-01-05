package com.newlecture.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

@Mapper
public interface NoticeDao {
	int insert(Notice notice);
	int update(Notice notice);
	int delete(int id);
	
	@Select("SELECT * FROM NOTICE WHERE ID=#{id}") //키를 심을 때 : ${id} : newlec, 값을 심을 때 : #{id} : 'newlec' 
	Notice get(int id);
	
	@Select("SELECT * FROM NOTICE")
	List<Notice> getList();
	
	List<Notice> getList(int startIndex, int endIndex, String field, String query);
	List<Notice> getList(int startIndex);
	List<NoticeView> getViewList();
	List<NoticeView> getViewList(int startIndex, int endIndex);
	List<NoticeView> getViewList(int startIndex, int endIndex, String field, String query);
	Notice getLast();
}
