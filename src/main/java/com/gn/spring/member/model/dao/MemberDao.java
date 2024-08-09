package com.gn.spring.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gn.spring.member.model.vo.Member;

@Repository
public class MemberDao {
	
	@Autowired
	SqlSession sqlSession;

	public int createMember(Member vo) {
		// resources/mappers 폴더 아래에 member-mapper.xml 파일 생성
		// 2. namespace = memberMapper로 변경
		// 3. id가 createMember이고 parameterType이 Member 클래스
		// 4. Insert 대상 컬럼 : user_id, user_pw, user_name
		// 5. 서버실행해서 화면에 resultMap 출력 확인
		return sqlSession.insert("memberMapper.createMember",vo);
	}

	public Member selectMemberById(String user_id) {
		return sqlSession.selectOne("memberMapper.selectMemberById",user_id);
	}

	public List<Member> selectMemberNotSender(int sender_no) {
		return sqlSession.selectList("memberMapper.selectMemberNotSender", sender_no);
	}

}
