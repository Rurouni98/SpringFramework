package com.bit.springboard.dao;

import com.bit.springboard.dto.NameTelDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NameTelDao {
    private SqlSessionTemplate mybatis;

    @Autowired
    public NameTelDao(SqlSessionTemplate sqlSessionTemplate) {
        this.mybatis = sqlSessionTemplate;
    }

    public void postNameTel(NameTelDto nameTelDto) {
        System.out.println("NameTelDto의 postNameTel 메소드 실행");

        mybatis.insert("NameTelDao.postNameTel", nameTelDto);

        System.out.println("NameTelDto의 postNameTel postNameTel 실행 종료");
    }

    public List<NameTelDto> getNameTels() {
        System.out.println("NameTelDao의 getNameTels 메소드 실행");
        return mybatis.selectList("NameTelDao.getNameTels");
    }
}
