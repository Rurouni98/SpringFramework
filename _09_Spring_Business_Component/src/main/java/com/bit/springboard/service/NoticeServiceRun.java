package com.bit.springboard.service;

import com.bit.springboard.dto.BoardDto;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class NoticeServiceRun {
    public static void main(String[] args) {
        AbstractApplicationContext factory =
                new GenericXmlApplicationContext("root-context.xml");

        BoardService boardService = factory.getBean("noticeServiceImpl", BoardService.class);
        
        // 게시글 등록
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("공지게시글1");
        boardDto.setContent("공지게시글 1번입니다.");

        boardService.post(boardDto);
        
        // 게시글 수정
        BoardDto modifyBoardDto = new BoardDto();
    }
}
