package com.bit.springboard.service.impl;

import com.bit.springboard.common.LogConsoleV2;
import com.bit.springboard.dao.NoticeDao;
import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements BoardService {
    private NoticeDao noticeDao;
    private LogConsoleV2 logConsoleV2;

    @Autowired
    public NoticeServiceImpl(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
        this.logConsoleV2 = new LogConsoleV2();
    }

    @Override
    public void post(BoardDto boardDto) {
        logConsoleV2.consoleLogPlus();
        noticeDao.post(boardDto);
    }

    @Override
    public void modify(BoardDto boardDto) {
        logConsoleV2.consoleLogPlus();
        noticeDao.modify(boardDto);
    }

    @Override
    public void delete(int id) {
        logConsoleV2.consoleLogPlus();
        noticeDao.delete(id);
    }

    @Override
    public List<BoardDto> getBoardList() {
        logConsoleV2.consoleLogPlus();
        return noticeDao.getBoardList();
    }

    @Override
    public BoardDto getBoard(int id) {
        logConsoleV2.consoleLogPlus();
        return noticeDao.getBoard(id);
    }
}
