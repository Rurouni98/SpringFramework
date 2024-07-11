package com.bit.springboard.service;

import com.bit.springboard.dto.BoardDto;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDateTime;

public class FreeBoardServiceRun {
    public static void main(String[] args) {
        AbstractApplicationContext factory =
                new GenericXmlApplicationContext("root-context.xml");

        BoardService boardService = factory.getBean("freeBoardServiceImpl", BoardService.class );

        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("자유게시글1");
        boardDto.setContent("자유게시글 1번입니다.");
        // writer_id는 member 테이블의 id 컬럼을 foreign key로 가져오기 때문에
        // member 테이블에 존재하는 id 값만 입력할 수 있다.
        boardDto.setWRITER_ID(2);

        boardService.post(boardDto);
        
        // 게시글 수정

        BoardDto modifyBoardDto = new BoardDto();
        modifyBoardDto.setTitle("자유게시글1(수정2)");
        modifyBoardDto.setContent("자유게시글 1번(수정2)입니다.");
        modifyBoardDto.setModdate(LocalDateTime.now());
        modifyBoardDto.setId(3);

        boardService.modify(modifyBoardDto);

        // 게시글 삭제
        boardService.delete(5);

        // 게시글 목록 조회
        boardService.getBoardList().forEach(board -> {
            System.out.println(board);
        });

        // 특정 id의 게시글 하나만 조회
        System.out.println(boardService.getBoard(4));

        factory.close();
    }
}
