package com.gn.spring.websocket.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SendMessage {
	private String sender_no;
	private String receiver_no;
	private String chat_msg;
	private String is_from_sender;
	private String chat_type;
}
