package com.example.myweb.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
	private static final Logger logger=LoggerFactory.getLogger(BoardController.class);
	
	// 어노테이션을 이용한 의존성 주입(DI) - 제어의 역전 IoC
	@Autowired
	private BoardDao boardDao;

	@RequestMapping(value = "/board/list.do", method = RequestMethod.GET)
	public String boardList(Model model) {
		// 목록 페이지로 forward
		System.out.println(">>> GET - /board/list.do 요청 받음");
		
		List<BoardDto> boardList = boardDao.selectAll();
		System.out.println(boardList);
		
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	@RequestMapping(value = "/board/input.do", method = RequestMethod.GET)
	public String boardInput() {
		// 입력 페이지로 forward
		System.out.println(">>> GET - /board/input.do 요청 받음");

		String viewName = "board/input";
		
		return viewName;
	}
	
	@RequestMapping(value = "/board/input.do", method = RequestMethod.POST)
	public String boardInputOk(String title, String writer, String content, String regdate) {
		// 게시글 입력 Process - 데이터 저장 후 목록으로 Redirect한다.
		System.out.println(">>> POST - /board/input.do 요청 받음");
		// Dao에 데이터를 저장하고 목록을 갱신하는 기능.
		
		BoardDto board=new BoardDto();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		board.setRegdate(regdate);
		
		boardDao.insert(board);
		System.out.println(">>>board insert: "+board);
		;
		return "redirect:list.do";
	}
	
	
	@RequestMapping(value = "/board/detail.do", method = RequestMethod.GET)
	public String boardDetail(BoardDto dto, Model model, int seq) {
		// 입력 페이지로 forward
		System.out.println(">>> GET - /board/boardDetail.do 요청 받음");
		//String seqParam = req.getParameter("seq");
		//System.out.println("seq => " + seqParam);
		
		dto=boardDao.findBySeq(seq);
		model.addAttribute("dto", dto);
		
		String viewName = "board/input";
		
		return viewName;
	}
}