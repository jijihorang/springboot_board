package org.example.adminboot.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.adminboot.admin.dto.BoardRegisterDTO;
import org.example.adminboot.admin.service.BoardService;
import org.example.adminboot.admin.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final UploadUtil uploadUtil;

    private final BoardService boardService;

    // 등록
    @PostMapping("/register")
    public String register(BoardRegisterDTO boardRegisterDTO, RedirectAttributes rttr) {

        log.info("register board : " + boardRegisterDTO);

        // 업로드 작업을 실제로 처리하는 부분
        // 업로드가 완료되면, 업로드된 파일들의 이름이 List<String> 형태로 반환
        List<String> uploadFileNames = uploadUtil.upload(boardRegisterDTO.getImages());

        // uploadFileNames 리스트를 boardRegisterDTO 객체의 fileNames 필드에 설정
        boardRegisterDTO.setFileNames(uploadFileNames);

        // 서비스 등록
        boardService.register(boardRegisterDTO);

        rttr.addFlashAttribute("bno",  boardRegisterDTO.getBno());

        return "redirect:/admin/list";
    }

    @GetMapping("/register")
    public void register() {

    }

    @GetMapping("/list")
    public void list(Model model) {

    }

    @GetMapping("/ex1")
    public void  admimex1(Model model) {
        log.info("admim ex1");

    }
}
