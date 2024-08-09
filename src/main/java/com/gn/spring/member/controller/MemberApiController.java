package com.gn.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gn.spring.member.model.service.MemberService;
import com.gn.spring.member.model.vo.Member;

@Controller
public class MemberApiController {
	
	@Autowired
	MemberService memberService;
	
	@ResponseBody // Map<String,String> -> json 형태로 변환하여 데이터를 보내줌
	@PostMapping("/join")
	public Map<String,String> createMember(@RequestBody Member vo){
		// @RequestBody : json 형태로 데이터를 받음
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("res_code", "404");
		resultMap.put("res_msg", "회원가입 중 오류가 발생하였습니다.");
		
		if(memberService.createMember(vo) > 0) {
			resultMap.put("res_code", "200");
			resultMap.put("res_msg", "회원가입에 성공적으로 완료되었습니다.");
		}
		
		System.out.println(resultMap);
		
		return resultMap;
	}
}
