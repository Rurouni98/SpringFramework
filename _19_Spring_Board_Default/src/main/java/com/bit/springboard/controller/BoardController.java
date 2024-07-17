package com.bit.springboard.controller;

import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.dto.MemberDto;
import com.bit.springboard.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;
    private ApplicationContext applicationContext;


    @Autowired
    public BoardController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("free-list.do")
    public String freeListView(Model model) {
        boardService = applicationContext.getBean("freeBoardServiceImpl", BoardService.class);

        model.addAttribute("freeBoardList", boardService.getBoardList());

        return "board/free-list";
    }

    @GetMapping("notice-list.do")
    public String noticeListView(Model model) {
        boardService = applicationContext.getBean("noticeServiceImpl", BoardService.class);

        model.addAttribute("noticeBoardList", boardService.getBoardList());

        return "board/notice-list";
    }

    @GetMapping("free-detail.do")
    public String freeDetailView(BoardDto boardDto, Model model) {
        boardService = applicationContext.getBean("freeBoardServiceImpl", BoardService.class);

        boardService.plusCnt(boardDto.getId());

//        System.out.println(boardService.getBoard(id));

        model.addAttribute("freeBoard", boardService.getBoard(boardDto.getId()));

        return "board/free-detail";
    }


    @GetMapping("notice-detail.do")
    public String noticeDetailView(BoardDto boardDto, Model model) {
        boardService = applicationContext.getBean("noticeServiceImpl", BoardService.class);

        boardService.plusCnt(boardDto.getId());

        model.addAttribute("notice", boardService.getBoard(boardDto.getId()));

        return "board/notice-detail";
    }

    @GetMapping("post.do")
    public String postView(HttpSession session) {
        MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");

        if(loginMember == null) {
            return "redirect:/member/login.do";
        }

        return "board/post";
    }

    @PostMapping("post.do")
    public String post(BoardDto boardDto) {
        // 게시판 타입에 따른 동적 의존성 주입
        if(boardDto.getType().equals("free")) {
            boardService = applicationContext.getBean("freeBoardServiceImpl", BoardService.class);
        } else {
            boardService = applicationContext.getBean("noticeServiceImpl", BoardService.class);
        }

        boardService.post(boardDto);

        if(boardDto.getType().equals("free")) {
            return "redirect:/board/free-list.do"; // 요청을 redirect로 보내지 않으면 post.do 라는 요청이 남아있어서 새로고침하면 post 요청이 다시감.
        } else {
            return "redirect:/board/notice-list.do";
        }
    }

    @PostMapping("modify.do")
    public String modify(BoardDto boardDto) {
        if(boardDto.getType().equals("free")) {
            boardService = applicationContext.getBean("freeBoardServiceImpl", BoardService.class);
        } else {
            boardService = applicationContext.getBean("noticeServiceImpl", BoardService.class);
        }

        boardService.modify(boardDto);

        if(boardDto.getType().equals("free")) {
            return "redirect:/board/free-detail.do?id=" + boardDto.getId();
        } else {
            return "redirect:/board/notice-detail.do?id=" + boardDto.getId();
        }
    }

    @GetMapping("delete.do")
    public String delete(BoardDto boardDto) {
        if(boardDto.getType().equals("free")) {
            boardService = applicationContext.getBean("freeBoardServiceImpl", BoardService.class);
        } else {
            boardService = applicationContext.getBean("noticeServiceImpl", BoardService.class);
        }

        boardService.delete(boardDto.getId());

//      model.addAttribute("freeBoardList", boardService.getBoardList());

        if(boardDto.getType().equals("free")) {
            return "redirect:/board/free-list.do";
        } else {
            return "redirect:/board/notice-list.do";
        }
    }
}
