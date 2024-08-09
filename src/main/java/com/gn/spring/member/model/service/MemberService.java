package com.gn.spring.member.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gn.spring.member.model.dao.MemberDao;
import com.gn.spring.member.model.vo.Member;

@Service
public class MemberService {
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	public int createMember(Member vo) {
		int result = 0;
		try {
			vo.setUser_pw(bcryptPasswordEncoder.encode(vo.getUser_pw()));
			
			result = memberDao.createMember(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Member> selectMemberNotSender(int sender_no) {
		List<Member> resultList = new ArrayList<Member>();
		try{
			resultList = memberDao.selectMemberNotSender(sender_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

}
