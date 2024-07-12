package com.bit.springboard.dao;

import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.service.BoardRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

// JDBC Template 사용방식 1
// JdbcDaoSupport 클래스를 상속받아 사용하는 방식
@Repository
public class NoticeDaoJdbcDaoSupport extends JdbcDaoSupport {
    @Autowired
    public void setSuperDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    // 공지게시글 등록 쿼리
    private final String POST = "INSERT INTO NOTICE(TITLE, CONTENT, WRITER_ID) VALUES(?,?,?)";

    // 공지게시글 수정 쿼리
    private final String MODIFY = "UPDATE NOTICE SET TITLE = ?, CONTENT = ?, MODDATE = ? WHERE ID = ?";

    // 공지게시글 목록 조회 쿼리
    private final String GET_BOARD_LIST = "SELECT N.ID" +
            "                                   , N.TITLE" +
            "                                   , N.CONTENT" +
            "                                   , N.WRITER_ID" +
            "                                   , MB.NICKNAME" +
            "                                   , N.REGDATE" +
            "                                   , N.MODDATE" +
            "                                   , N.CNT" +
            "                                 FROM NOTICE N" +
            "                                 JOIN MEMBER MB" +
            "                                   ON N.WRITER_ID = MB.ID";

    // 공지게시글 삭제 쿼리
    private final String DELETE = "DELETE FROM NOTICE" +
            "                           WHERE ID = ?";

    // 특정 id의 게시글 하나만 조회
    private final String GET_BOARD = "SELECT N.ID" +
            "                                   , N.TITLE" +
            "                                   , N.CONTENT" +
            "                                   , N.WRITER_ID" +
            "                                   , MB.NICKNAME" +
            "                                   , N.REGDATE" +
            "                                   , N.MODDATE" +
            "                                   , N.CNT" +
            "                                 FROM NOTICE N" +
            "                                 JOIN MEMBER MB" +
            "                                   ON N.WRITER_ID = MB.ID" +
            "                                 WHERE N.ID = ?";

    public void post(BoardDto boardDto) {
        System.out.println("NoticeDao의 post 메소드 실행");

        getJdbcTemplate().update(POST, boardDto.getTitle(), boardDto.getContent(), boardDto.getWRITER_ID());

        System.out.println("NoticeDao의 post 메소드 실행 종료");
    }

    public void modify(BoardDto boardDto) {
        System.out.println("NoticeDao의 modify 메소드 실행");

        getJdbcTemplate().update(MODIFY, boardDto.getTitle(), boardDto.getContent(), boardDto.getModdate().toString(), boardDto.getId());

        System.out.println("NoticeDao의 modify 메소드 실행 종료");
    }

    public List<BoardDto> getBoardList() {
        System.out.println("NoticeDao의 getBoardList 메소드 실행");
        List<BoardDto> boardDtoList = new ArrayList<>();

        boardDtoList = getJdbcTemplate().query(GET_BOARD_LIST, new BoardRowMapper());

        System.out.println("NoticeDao의 getBoardList 메소드 실행 종료");
        return boardDtoList;
    }

    public void delete(int id) {
        System.out.println("Notice의 delete 메소드 실행");

        getJdbcTemplate().update(DELETE, id);

        System.out.println("Notice의 delete 메소드 실행 종료");
    }

    public BoardDto getBoard(int id) {
        System.out.println("Notice의 getBoard 메소드 실행");

        BoardDto boardDto = new BoardDto();

        // queryForObject의 두 번째 매개변수는 Object 배열 형태여야한다.
        Object[] args = {id};

        boardDto = getJdbcTemplate().queryForObject(GET_BOARD, args, new BoardRowMapper());

        System.out.println("Notice의 getBoard 메소드 실행 종료");
        return boardDto;
    }

}
