package com.gn.spring;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@EnableAspectJAutoProxy
@Controller // 서블릿으로서 사용한다는 의미
public class HomeController {
	
	// 의존성 주입
	// 보통 private static final 을 사용 -> 변수명을 대문자로 설정
	
	// 클라이언트가 요청한 서비스 주소와 메소드를 연결
	// http://localhost:8090
	// http://localhost:8090/ 
	// => 브라우저는 같다고 생각, 자바는 다르다고 생각
	@RequestMapping(value= {"","/"},method=RequestMethod.GET)
	public String home() {
//		LOGGER.info("게시판 프로그램 시작");
		// /WEB-INF/views/home.jsp 와 같은 뜻
		return "home";
	}
}
