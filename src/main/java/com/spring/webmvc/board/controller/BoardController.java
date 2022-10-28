package com.spring.webmvc.board.controller;

import com.spring.webmvc.board.domain.Board;
import com.spring.webmvc.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board") // 공통 url 진입점 설정, /board로 오는놈은 다 내가받겠다!!
@Log4j2
public class BoardController {
    private final BoardService service;

    // 게시물 목록 조회 요청 처리
    @GetMapping("/list")
    public String list(Model model) { // 이렇게 void로 하면 저 list위치로(webapp/web-inf/views/board/list.jsp) 알아서 감, void 아니면 return으로 board/list해주면 됨, 아니면 ModelAndView 해서 해도됨
        List<Board> boardList = service.getList();

        log.info("/board/list GET! 요청 발생!");

        model.addAttribute("bList", boardList);

        return "board/list";
    }

    @GetMapping("/write")
    public String write(Board board) { // 이렇게 void로 하면 저 list위치로(webapp/web-inf/views/board/list.jsp) 알아서 감, void 아니면 return으로 board/list해주면 됨, 아니면 ModelAndView 해서 해도됨

        return "board/write";
    }

    // 게시물 등록 요청
    @PostMapping("/write")
    public String write(Board board, RedirectAttributes ra){
       // log.info("/board/write POST! - {}", board);

        boolean flag = service.insert(board);
        System.out.println(flag);
        ra.addFlashAttribute("msg", "insert-success");

        //참이면 왼쪽 실패면 오른쪽
        return flag ? "redirect:/board/list":"redirect:/";


    }


}
