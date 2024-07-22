package com.bit.springboard.service.impl;

import com.bit.springboard.common.FileUtils;
import com.bit.springboard.dao.FreeBoardDao;
import com.bit.springboard.dto.BoardDto;
import com.bit.springboard.dto.BoardFileDto;
import com.bit.springboard.dto.Criteria;
import com.bit.springboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FreeBoardServiceImpl implements BoardService {
    private FreeBoardDao freeBoardDao;

    @Autowired
    public FreeBoardServiceImpl(FreeBoardDao freeBoardDao) {
        this.freeBoardDao = freeBoardDao;
    }

    @Override
    public void post(BoardDto boardDto, MultipartFile[] uploadFiles) {
        List<BoardFileDto> boardFileDtoList = new ArrayList<>();

        if(uploadFiles != null && uploadFiles.length > 0) {
            // 업로드 폴더 지정
            String attachPath = "C:/tmp/upload/";

            File directory = new File(attachPath);

            // 업로드 폴더가 존재하지 않으면 폴더 생성
            if(!directory.exists()) {
                // 하위폴더도 생성하려면 mkdirs 메소드를 호출한다.
                directory.mkdirs();
            }

            Arrays.stream(uploadFiles).forEach(file -> {
                if(file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
                    BoardFileDto boardFileDto = FileUtils.parseFileInfo(file, attachPath);
                    boardFileDtoList.add(boardFileDto);
                }
            });
        }

        freeBoardDao.post(boardDto, boardFileDtoList);
    }

    @Override
    public void modify(BoardDto boardDto) {
        boardDto.setModdate(LocalDateTime.now());
        freeBoardDao.modify(boardDto);
    }

    @Override
    public void plusCnt(int id) {
        freeBoardDao.plusCnt(id);
    }

    @Override
    public void delete(int id) {
        freeBoardDao.delete(id);
    }

    @Override
    public List<BoardDto> getBoardList(Map<String, String> searchMap, Criteria cri) {
        cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());

        // mybatis에서 parameter를 하나만 받을 수 있다.
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("search", searchMap);
        paramMap.put("cri", cri);

        return freeBoardDao.getBoardList(paramMap);
    }

    @Override
    public BoardDto getBoard(int id) {
        return freeBoardDao.getBoard(id);
    }

    @Override
    public int getBoardTotalCnt(Map<String, String> searchMap) {
        return freeBoardDao.getBoardTotalCnt(searchMap);
    }

    @Override
    public List<BoardFileDto> getBoardFileList(int id) {
        return freeBoardDao.getFreeBoardFileList(id);
    }
}
