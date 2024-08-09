package com.gn.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gn.spring.member.model.dao.MemberDao;
import com.gn.spring.member.model.vo.Member;

@Component("loginService")
public class SecurityService implements UserDetailsService{

	@Autowired
	MemberDao memberDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 실제 데이터베이스에서 회원정보를 조회
		// 조회한 회원 정보를 User 형태로 변환해서 제공
		// spring security는 id를 'username'이라고 칭함
		Member member = memberDao.selectMemberById(username);
		if(member != null) {
			return new SecurityUser(member);
		}
		return null;
	}
	
}
