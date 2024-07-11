package com.bit.springboard.service;

import com.bit.springboard.dto.BoardDto;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDateTime;

public class NoticeServiceRun {
    public static void main(String[] args) {
        AbstractApplicationContext factory =
                new GenericXmlApplicationContext("root-context.xml");

        BoardService boardService = factory.getBean("noticeServiceImpl", BoardService.class);
        
        // 게시글 등록
        BoardDto boardDto = new BoardDto();
//        boardDto.setWRITER_ID(9);
//        boardDto.setTitle("공지게시글3");
//        boardDto.setContent("공지게시글 3번입니다.");
//
//
//        boardService.post(boardDto);

        // 게시글 수정

        BoardDto modifyBoardDto = new BoardDto();
        modifyBoardDto.setTitle("공지게시글3(수정)");
        modifyBoardDto.setContent("공지게시글 3번(수정)입니다.");
        modifyBoardDto.setModdate(LocalDateTime.now());
        modifyBoardDto.setId(12);

        boardService.modify(modifyBoardDto);

        // 게시글 삭제
        boardService.delete(10);

        // 게시글 목록 조회
        boardService.getBoardList().forEach(board -> {
            System.out.println(board);
        });

        // 특정 id의 게시글 하나만 조회
        System.out.println(boardService.getBoard(12));

        factory.close();
    }
}
