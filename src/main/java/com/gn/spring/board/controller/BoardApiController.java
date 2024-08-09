package com.gn.spring.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gn.spring.board.model.service.BoardService;
import com.gn.spring.board.model.service.UploadFileService;
import com.gn.spring.board.model.vo.Board;

@Controller
public class BoardApiController { // 기능을 처리하는 컨트롤러
	
	@Autowired
	UploadFileService uploadFileService;
	@Autowired
	BoardService boardService;
	
	@ResponseBody // HTTP요청의 응답을 'json' 형태로 변환하여 데이터를 보내줄 때
	@PostMapping("/board")
	public Map<String,String> createBoard(Board vo,
			@RequestParam("file") MultipartFile file){ 
		// RequestParam("파일명")
		// 'file'이라는 이름의 파일을 MultipartFile에 맵핑
		// MultipartFile은 spring Framework 에서 제공하는 인터페이스
		
//		LOGGER.info("file 데이터 :"+file.getOriginalFilename());
		
		// 3. 결과를 json 형태로 화면에 전달
		// Map 형태의 데이터를 json 형태의 데이터로 바꿔서(ResponseBody) 리턴
		Map<String,String> resultMap = new HashMap<String,String>();
		// 오류가 발생했을 때를 'default'값으로 리턴
		resultMap.put("res_code", "404");
		resultMap.put("res_msg", "게시글 등록 중 오류가 발생하였습니다.");
		
		// 2. 게시글 정보, 파일 정보 DB에 등록
		String savedFileName = uploadFileService.upload(file);
		if(!"".equals(savedFileName)) { // 새로운 파일명이 있다면(오류가 없다면)
			vo.setOri_thumbnail(file.getOriginalFilename());
			vo.setNew_thumbnail(savedFileName);
			// BoardService 의존성 주입
			// service -> dao -> mapper 게시글 insert
			
			int result = boardService.insertBoard(vo);
			if(result > 0) {
				resultMap.put("res_code", "200");
				resultMap.put("res_msg", "게시글이 성공적으로 등록되었습니다.");
			}	
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@PostMapping("/board/{board_no}")
	public Map<String,String> updateBoard(Board vo,
			@RequestParam("file") MultipartFile file){
		
		Map<String,String> resultMap = new HashMap<String,String>();

		resultMap.put("res_code", "404");
		resultMap.put("res_msg", "게시글 수정 중 오류가 발생하였습니다.");
		
		if(file != null && !"".equals(file.getOriginalFilename())) {
			String savedFileName = uploadFileService.upload(file);
			if(!"".equals(savedFileName)) { // 새로운 파일명이 있다면(오류가 없다면)
				vo.setOri_thumbnail(file.getOriginalFilename());
				vo.setNew_thumbnail(savedFileName);
			}
		}
		
		int result = boardService.updateBoard(vo);
		if(result > 0) {
			resultMap.put("res_code", "200");
			resultMap.put("res_msg", "게시글이 성공적으로 수정되었습니다.");
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@DeleteMapping("/board/{board_no}")
	public Map<String,String> deleteBoard(@PathVariable("board_no") int board_no){
		Map<String,String> resultMap = new HashMap<String,String>();
		
		resultMap.put("res_code", "404");
		resultMap.put("res_msg", "게시글 삭제 중 오류가 발생하였습니다.");
		
		int result = boardService.deleteBoard(board_no);
		if(result > 0) {
			resultMap.put("res_code", "200");
			resultMap.put("res_msg", "게시글이 성공적으로 삭제되었습니다.");
		}
		
		return resultMap;
	}
}
