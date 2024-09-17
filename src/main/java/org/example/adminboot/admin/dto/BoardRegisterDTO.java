package org.example.adminboot.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardRegisterDTO {

    private Long bno;
    private String title;
    private String content;
    private String writer;

    private MultipartFile[] images; // 실제로 업로드된 파일 데이터를 처리하는 배열

    private java.util.List<String> fileNames; // 여러 파일의 이름들을 저장

    private String tag;

}
