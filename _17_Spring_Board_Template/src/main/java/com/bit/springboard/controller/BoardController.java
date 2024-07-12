package com.bit.springboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("free-list.do")
    public String freeListView() {
        return "board/free-list";
    }
    @GetMapping("notice-list.do")
    public String noticeListView() {
        return "board/notice-list";
    }
    @GetMapping("free-detail.do")
    public String freeDetailView() {
        return "board/free-detail";
    }
    @GetMapping("notice-detail.do")
    public String noticeDetailView() {
        return "board/notice-detail";
    }
    @GetMapping("post.do")
    public String postView() {
        return "board/post";
    }
}
