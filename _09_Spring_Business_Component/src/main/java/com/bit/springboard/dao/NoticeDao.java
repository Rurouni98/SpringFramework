package com.bit.springboard.dao;

import com.bit.springboard.common.JDBCUtil;
import com.bit.springboard.dto.BoardDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NoticeDao {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

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
            "                                   ON NB.WRITER_ID = MB.ID" +
            "                                 WHERE N.ID = ?";

    public void post(BoardDto boardDto) {
        System.out.println("NoticeDao의 post 메소드 실행");
        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(POST);

            stmt.setString(1, boardDto.getTitle());
            stmt.setString(2, boardDto.getContent());
            stmt.setInt(3, boardDto.getWRITER_ID());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }

        System.out.println("NoticeDao의 post 메소드 실행 종료");
    }

    public void modify(BoardDto boardDto) {
        System.out.println("NoticeDao의 modify 메소드 실행");
        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(MODIFY);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }

        System.out.println("NoticeDao의 modify 메소드 실행 종료");
    }

    public List<BoardDto> getBoardList() {
        System.out.println("NoticeDao의 getBoardList 메소드 실행");
        List<BoardDto> boardDtoList = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_BOARD_LIST);

            rs = stmt.executeQuery();

            while(rs.next()) {
                BoardDto boardDto = new BoardDto();

                boardDto.setId(rs.getInt("ID"));
                boardDto.setTitle(rs.getString("TITLE"));
                boardDto.setContent(rs.getString("CONTENT"));
                boardDto.setWRITER_ID(rs.getInt("WRITER_ID"));
                boardDto.setNickname(rs.getString("NICKNAME"));
                boardDto.setRegdate(rs.getTimestamp("REGDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setModdate(rs.getTimestamp("MODDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setCnt(rs.getInt("CNT"));

                boardDtoList.add(boardDto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }

        System.out.println("NoticeDao의 getBoardList 메소드 실행 종료");
        return boardDtoList;
    }

    public void delete(int id) {
        System.out.println("Notice의 delete 메소드 실행");

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }

        System.out.println("Notice의 delete 메소드 실행 종료");
    }

    public BoardDto getBoard(int id) {
        System.out.println("Notice의 getBoard 메소드 실행");

        BoardDto boardDto = new BoardDto();

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_BOARD);

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()) {
                boardDto.setId(rs.getInt("ID"));
                boardDto.setTitle(rs.getString("TITLE"));
                boardDto.setContent(rs.getString("CONTENT"));
                boardDto.setWRITER_ID(rs.getInt("WRITER_ID"));
                boardDto.setNickname(rs.getString("NICKNAME"));
                boardDto.setRegdate(rs.getTimestamp("REGDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setModdate(rs.getTimestamp("MODDATE").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
                boardDto.setCnt(rs.getInt("CNT"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }

        System.out.println("Notice의 getBoard 메소드 실행 종료");
        return boardDto;
    }

}
