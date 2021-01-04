package com.newlecture.web.entity;

import java.util.Date;

public class NoticeView extends Notice {
	private String writerName;
	private int cmtCount;
	
	public NoticeView() {
		// TODO Auto-generated constructor stub
	}	
	

	public NoticeView(int id
			, String title
			, String writerId
			, String content
			, Date regdate
			, int hit
			, String files
			,String writerName
			, int cmtCount) {
		super(id, title, writerId, content, regdate, hit, files);
		this.writerName = writerName;
		this.cmtCount = cmtCount;
		
	}

	public NoticeView(String title, String content) {
		super(title, content);
		// TODO Auto-generated constructor stub
	}


	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}
	
	
	
	
}
