package com.gn.spring.board.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gn.spring.board.model.service.BoardService;
import com.gn.spring.board.model.vo.Board;

@Controller
public class BoardViewController { // 화면을 처리하는 컨트롤러
	
	@Autowired
	BoardService boardService;
	
//	@GetMapping("/board")
//	public ModelAndView selectBoardList() {
//		// 화면단에 데이터 보내는 방법
//		// 1. ModelAndView(잘 사용 X) 
		// -> Model 과 View 정보 둘 다 들어있음 -> 하나라도 잘못되면 화면이 안보임 => 구분지어야 됌!
//		List<Board> resultList = boardService.selectBoardList();
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("resultList",resultList);
//		mav.setViewName("/board/list");
//		return mav;
//	}
	
	@GetMapping("/board")
	// Model : 컨트롤러에서 뷰로 데이터를 전달하는 데 사용되는 객체
	public String selectBoardList(Model model, Board option) {
		option.setTotalData(boardService.selectBoardCount(option));
		List<Board> resultList = boardService.selectBoardList(option);
//		LOGGER.info(resultList);
		
		// 2. Model(매개변수 설정!!) -> 대부분 이 방법 사용!
		// request.setAttribute와 같은 기능
		// 'return값'만 잘 해놓으면 화면전환은 잘 됌
		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", option);
		
		// /WEB-INF/views/board/list.jsp
		return "/board/list";
	}
	
	@GetMapping("/board/create")
	public String createBoardPage() {
		// /WEB-INF/views/board/create.jsp
		return "/board/create";
	}
	
	@GetMapping("/board/{board_no}")
	// @PathVariable() : 'url'에 변화가 생기는 데이터가 있을 때 사용
	// () 안에는 변화가 생기는 부분({}안의 부분) 작성
	// 메소드 안에서 부를 변수명 지정
	public String selectBoardOne(Model model, @PathVariable("board_no") int board_no) {
		
		Board result = boardService.selectBoardOne(board_no);
		model.addAttribute("result",result);
		// /WEB-INF/views/board/detail.jsp
		return "/board/detail";
	}
	
	@GetMapping("/board/update/{board_no}")
	public String updateBoard(Model model, @PathVariable("board_no") int board_no) {
		Board vo = boardService.selectBoardOne(board_no);
		
		model.addAttribute("vo",vo);
		return "/board/update";
	}

}
