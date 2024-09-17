package org.example.adminboot.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.adminboot.admin.dto.BoardRegisterDTO;
import org.example.adminboot.admin.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public void register(BoardRegisterDTO boardRegisterDTO) {

        boardMapper.insert(boardRegisterDTO);

        List<String> fileNames = boardRegisterDTO.getFileNames();

        for (int i = 0; i < fileNames.size(); i++) {
            boardMapper.insertAttach(boardRegisterDTO.getBno(),
                    fileNames.get(i),
                    i);
        } // end for

    }
}
