package com.bit.springboard.service.impl;

import com.bit.springboard.common.LogConsoleV2;
import com.bit.springboard.dao.FreeBoardDao;
import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreeBoardServiceImpl implements BoardService {
    private FreeBoardDao freeBoardDao;
    private LogConsoleV2 logConsoleV2;

    @Autowired
    public FreeBoardServiceImpl(FreeBoardDao freeBoardDao) {
        this.freeBoardDao = freeBoardDao;
        this.logConsoleV2 = new LogConsoleV2();
    }

    @Override
    public void post(BoardDto boardDto) {
        logConsoleV2.consoleLogPlus();
        freeBoardDao.post(boardDto);
    }

    @Override
    public void modify(BoardDto boardDto) {
        logConsoleV2.consoleLogPlus();
        freeBoardDao.modify(boardDto);
    }

    @Override
    public void delete(int id) {
        logConsoleV2.consoleLogPlus();
        freeBoardDao.delete(id);
    }

    @Override
    public List<BoardDto> getBoardList() {
        logConsoleV2.consoleLogPlus();
        return freeBoardDao.getBoardList();
    }

    @Override
    public BoardDto getBoard(int id) {
        logConsoleV2.consoleLogPlus();
        return freeBoardDao.getBoard(id);
    }
}
