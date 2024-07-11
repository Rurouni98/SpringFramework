package com.bit.springboard.dao;

import com.bit.springboard.common.JDBCUtil;
import com.bit.springboard.dto.MemberDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// DAO(Data Access Object): 데이터베이스에 직접 접근해서 쿼리를 실행하는 클래스
@Repository
public class MemberDao {
    // JDBC 변수들
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    // 쿼리문 작성
    private final String JOIN = "INSERT INTO MEMBER(USERNAME, PASSWORD, EMAIL, NICKNAME, TEL) VALUES(?, ?, ?, ?, ?)";
    private final String GET_MEMBERS = "SELECT ID" +
            "                                , USERNAME" +
            "                                , PASSWORD" +
            "                                , NICKNAME" +
            "                                , EMAIL" +
            "                                , TEL" +
            "                              FROM MEMBER";
    // 특정 회원 조회 쿼리문
    private final String GET_MEMBER_BY_USERNAME = "SELECT ID" +
            "                                           , USERNAME" +
            "                                           , PASSWORD" +
            "                                           , NICKNAME" +
            "                                           , EMAIL" +
            "                                           , TEL" +
            "                                          FROM MEMBER" +
            "                                          WHERE USERNAME = ?";

    public void join(MemberDto memberDto) {
        // MemberDto에 담겨있는 내용을 MEMBER 테이블에 insert
        System.out.println("MemberDao의 join 메소드 실행");
        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(JOIN);

            stmt.setString(1, memberDto.getUsername());
            stmt.setString(2, memberDto.getPassword());
            stmt.setString(3, memberDto.getEmail());
            stmt.setString(4, memberDto.getNickname());
            stmt.setString(5, memberDto.getTel());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt);
        }
        System.out.println("MemberDao의 join 메소드 실행 종료");
    }

    public List<MemberDto> getMembers() {
        // MemberDto에 담겨있는 내용을 MEMBER 테이블에 insert
        System.out.println("MemberDao의 getMember 메소드 실행");
        List<MemberDto> memberDtoList = new ArrayList<MemberDto>();
        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_MEMBERS);

            rs = stmt.executeQuery();

            while(rs.next()) {
                MemberDto memberDto = new MemberDto();
                memberDto.setId(rs.getInt("ID"));
                memberDto.setUsername(rs.getString("USERNAME"));
                memberDto.setPassword(rs.getString("PASSWORD"));
                memberDto.setEmail(rs.getString("EMAIL"));
                memberDto.setNickname(rs.getString("NICKNAME"));
                memberDto.setTel(rs.getString("TEL"));

                memberDtoList.add(memberDto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }

        System.out.println("MemberDao의 getMember 메소드 실행 종료");
        return memberDtoList;
    }

    public MemberDto getMemberByUsername(MemberDto memberDto) {
        // MemberDto에 담겨있는 내용을 MEMBER 테이블에 insert
        System.out.println("MemberDao의 getMemberByUsername 메소드 실행");
        MemberDto returnMemberDto = new MemberDto();

        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(GET_MEMBER_BY_USERNAME);
            stmt.setString(1, memberDto.getUsername());
            rs = stmt.executeQuery();

            if(rs.next()) {
                returnMemberDto.setId(rs.getInt("ID"));
                returnMemberDto.setUsername(rs.getString("USERNAME"));
                returnMemberDto.setPassword(rs.getString("PASSWORD"));
                returnMemberDto.setEmail(rs.getString("EMAIL"));
                returnMemberDto.setNickname(rs.getString("NICKNAME"));
                returnMemberDto.setTel(rs.getString("TEL"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }

        System.out.println("MemberDao의 getMemberByUsername 메소드 실행 종료");
        return returnMemberDto;
    }
}

