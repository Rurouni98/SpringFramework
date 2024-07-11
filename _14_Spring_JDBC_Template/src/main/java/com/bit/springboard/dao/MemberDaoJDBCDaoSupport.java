package com.bit.springboard.dao;

import com.bit.springboard.dto.MemberDto;
import com.bit.springboard.service.MemberRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

// JDBC Template 사용방식 1
// JdbcDaoSupport 클래스를 상속받아 사용하는 방식
// DAO(Data Access Object): 데이터베이스에 직접 접근해서 쿼리를 실행하는 클래스
@Repository
public class MemberDaoJDBCDaoSupport extends JdbcDaoSupport {
    @Autowired
    public void setSuperDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }
    
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

        getJdbcTemplate().update(JOIN, memberDto.getUsername(), memberDto.getPassword(), memberDto.getEmail(), memberDto.getNickname(), memberDto.getTel());

        System.out.println("MemberDao의 join 메소드 실행 종료");
    }

    public List<MemberDto> getMembers() {
        // MemberDto에 담겨있는 내용을 MEMBER 테이블에 insert
        System.out.println("MemberDao의 getMember 메소드 실행");
        List<MemberDto> memberDtoList = new ArrayList<MemberDto>();

        memberDtoList = getJdbcTemplate().query(GET_MEMBERS, new MemberRowMapper());

        System.out.println("MemberDao의 getMember 메소드 실행 종료");
        return memberDtoList;
    }

    public MemberDto getMemberByUsername(MemberDto memberDto) {
        // MemberDto에 담겨있는 내용을 MEMBER 테이블에 insert
        System.out.println("MemberDao의 getMemberByUsername 메소드 실행");
        MemberDto returnMemberDto = new MemberDto();

        Object[] args = {memberDto};

        returnMemberDto = getJdbcTemplate().queryForObject(GET_MEMBER_BY_USERNAME, args, new MemberRowMapper());


        System.out.println("MemberDao의 getMemberByUsername 메소드 실행 종료");
        return returnMemberDto;
    }
}

