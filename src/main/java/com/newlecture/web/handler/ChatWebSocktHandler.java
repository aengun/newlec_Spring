package com.newlecture.web.handler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocktHandler extends TextWebSocketHandler {
	// implements WebSocketHandler
	// extends TextWebSocketHandler : 텍스트 전문

	// 여러 사람과 채팅을 하기 위해 세션을 저장해야함. = > 그냥 ArrayList에 담으면 안됨 = > 쓰레드에 불안전
	// 쓰레드에 안전한 CopyOnWriteArrayList() 사용

	private List<WebSocketSession> sessions = new CopyOnWriteArrayList(); // 스레드에 안전함

	// 연결 될 때마다 세션 담기.
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		System.out.println("새로운 사용자가 연결되었습니다.");
		System.out.println("현재 접속 인원 : " + sessions.size());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for (WebSocketSession wsession : sessions) // 접속한 사용자가 들어있는 세션
			wsession.sendMessage(message); // .getPayload()
	}

	// 연결 해제될때마다 세션 삭제
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
		System.out.println("연결이 해제되었습니다.");
		System.out.println("현재 접속 인원 : " + sessions.size());
	}

}

// ============ Binary,Text 모두를 위한 핸들러 ================
//public class ChatWebSocketHandler implements WebSocketHandler {
//
//
// @Override
// public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//    System.out.println("누가 연결되었나?");
//
// }
//
// @Override
// public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//    System.out.println(message.toString());
//
// }
//
// @Override
// public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//    // TODO Auto-generated method stub
//
// }
//
// @Override
// public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//    // TODO Auto-generated method stub
//
// }
//
// @Override
// public boolean supportsPartialMessages() {
//    // TODO Auto-generated method stub
//    return false;
// }
