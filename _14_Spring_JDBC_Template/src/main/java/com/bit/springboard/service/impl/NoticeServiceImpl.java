package com.bit.springboard.service.impl;

import com.bit.springboard.dao.NoticeDaoJDBC;
import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements BoardService {
    private NoticeDaoJDBC noticeDao;

    @Autowired
    public NoticeServiceImpl(NoticeDaoJDBC noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    public void post(BoardDto boardDto) {
        noticeDao.post(boardDto);
    }

    @Override
    public void modify(BoardDto boardDto) {
        noticeDao.modify(boardDto);
    }

    @Override
    public void delete(int id) {
        noticeDao.delete(id);
    }

    @Override
    public List<BoardDto> getBoardList() {
        return noticeDao.getBoardList();
    }

    @Override
    public BoardDto getBoard(int id) {
        return noticeDao.getBoard(id);
    }
}
