package com.newlecture.web.config;

import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

public class AdminViewPreparer implements ViewPreparer{

	@Autowired
	private NoticeService noticeService;
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		
		List<NoticeView> list = noticeService.getViewList(1, 10);
		
		Map<String, Object> model = tilesContext.getContext("request");
		model.put("asideList", list);
				
	}

}
