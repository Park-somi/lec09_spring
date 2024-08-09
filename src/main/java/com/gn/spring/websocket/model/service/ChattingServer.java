package com.gn.spring.websocket.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gn.spring.websocket.model.vo.SendMessage;

public class ChattingServer extends TextWebSocketHandler{
	
//	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	// clients : 웹소켓 세션을 통해 클라이언트에게 메시지를 전송하기 위해
	private Map<String,WebSocketSession> clients = new HashMap<String,WebSocketSession>();
	
	// 클라이언트가 연결되었을 때 설정
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		sessionList.add(session);
	}
	
	// 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 설정
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		// 클라이언트가 보낸 메시지
		String payload = message.getPayload();
		// Jackson ObjectMapper 객체 생성
		ObjectMapper objectMapper = new ObjectMapper();
		// JSON -> SendMessage 형태 변환
		SendMessage msg = objectMapper.readValue(payload, SendMessage.class);
		
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("res_code", "404");
		resultMap.put("res_msg", "채팅 전송중 오류가 발생하였습니다.");
		
		switch(msg.getChat_type()) {
			case "open" : 
				// 채팅창을 열었을 때 보낸 사람의 no 를 client 에 저장
				clients.put(msg.getSender_no(), session); 
				// 데이터베이스 추가
				resultMap.put("res_code", "200");
				resultMap.put("res_msg", msg.getSender_no()+"님이 입장하셨습니다.");
				resultMap.put("res_type", "open");
			break;
			case "msg":
				resultMap.put("res_code", "200");
				resultMap.put("res_msg", msg.getChat_msg());
				resultMap.put("res_type", "msg");
				resultMap.put("sender_info", msg.getSender_no());
			break;
			
		}
		
		// TextMessage : 웹소켓을 통해 클라이언트와 서버 간의 텍스트 메시지를 송수신
		// writeValueAsString : Java 객체를 JSON 문자열로 변환
		TextMessage resultMsg = new TextMessage(objectMapper.writeValueAsString(resultMap));
		
		// sender_no가 메세지를 보낸 사람의 user_no와 받는 사람의 user_no 중에 같을 때 
		// 채팅방에 (sender_no)님이 입장하셨습니다. 문구 출력
		for(String no : clients.keySet()) {
			if(no.equals(msg.getSender_no())||no.equals(msg.getReceiver_no())) {
				// get : 특정 키에 대한 값을 가져오는 메소드(여기에서는 특정키가 no)
				WebSocketSession client = clients.get(no);
				client.sendMessage(resultMsg); // 해당 클라이언트에게 메세지 전송
			}
		}
		
		// 클라이언트가 보낸 메시지 확인하기
//		String payload = message.getPayload();
//		// Jackson ObjectMapper 객체 생성
//		ObjectMapper objectMapper = new ObjectMapper();
//		// JSON String 형태 데이터 -> 우리가 만든 클래스 형태로 변환
//		SendMessage msg = objectMapper.readValue(payload, SendMessage.class);
//		
//		Map<String,String> resultMap = new HashMap<String,String>();
//		resultMap.put("res_code", "404");
//		resultMap.put("res_msg", "채팅 전송 중 오류가 발생하였습니다.");
//		
//		TextMessage tm = new TextMessage(objectMapper.writeValueAsString(resultMap));
//		
//		for(WebSocketSession temp : sessionList) {
//			temp.sendMessage(tm);
//		}
	}
	
	// 클라이언트가 연결을 끊었을 때 설정
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//		sessionList.remove(session);
	}
	
}
