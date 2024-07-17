package com.bit.springboard.dao;

import com.bit.springboard.dto.BoardDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeDao {
    private SqlSessionTemplate mybatis;

    @Autowired
    public NoticeDao(SqlSessionTemplate sqlSessionTemplate) {
        this.mybatis = sqlSessionTemplate;
    }

    public void post(BoardDto boardDto) {
        System.out.println("NoticeDao의 post 메소드 실행");

        mybatis.insert("NoticeDao.post", boardDto);

        System.out.println("NoticeDao의 post 메소드 실행 종료");
    }

    public void modify(BoardDto boardDto) {
        System.out.println("NoticeDao의 modify 메소드 실행");

        mybatis.update("NoticeDao.modify", boardDto);

        System.out.println("NoticeDao의 modify 메소드 실행 종료");
    }

    public List<BoardDto> getBoardList() {
        System.out.println("NoticeDao의 getBoardList 메소드 실행");

        return mybatis.selectList("NoticeDao.getBoardList");
    }

    public void delete(int id) {
        System.out.println("Notice의 delete 메소드 실행");

        mybatis.delete("NoticeDao.delete", id);

        System.out.println("Notice의 delete 메소드 실행 종료");
    }

    public BoardDto getBoard(int id) {
        System.out.println("Notice의 getBoard 메소드 실행");

        return mybatis.selectOne("NoticeDao.getBoard", id);
    }

    public void plusCnt(int id) {
        System.out.println("NoticeDao의 plusCnt 메소드 실행");

        mybatis.update("NoticeDao.plusCnt", id);

        System.out.println("NoticeDao의 plusCnt 메소드 실행 종료");
    }

}
