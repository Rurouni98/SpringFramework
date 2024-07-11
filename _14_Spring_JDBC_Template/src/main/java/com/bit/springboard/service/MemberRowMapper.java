package com.bit.springboard.service;

import com.bit.springboard.dto.MemberDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<MemberDto> {

    @Override
    public MemberDto mapRow(ResultSet rs, int rowNum){
        MemberDto memberDto = new MemberDto();

        try {
            memberDto.setId(rs.getInt("ID"));
            memberDto.setUsername(rs.getString("USERNAME"));
            memberDto.setPassword(rs.getString("PASSWORD"));
            memberDto.setEmail(rs.getString("EMAIL"));
            memberDto.setNickname(rs.getString("NICKNAME"));
            memberDto.setTel(rs.getString("TEL"));

        } catch(SQLException se) {
            System.out.println(se.getMessage());
        }

        return memberDto;
    }
}
