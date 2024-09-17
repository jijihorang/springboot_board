package org.example.adminboot.admin;

import lombok.extern.log4j.Log4j2;
import org.example.adminboot.admin.dto.BoardRegisterDTO;
import org.example.adminboot.admin.mapper.BoardMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@ActiveProfiles("dev")
@Sql(scripts = {"classpath:sql/boardBefore.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)  // 테스트 하기 전에 테이블 생성, 더미데이터 넣기 등
@Log4j2
public class BoardMapperTests {

    @Autowired(required = false) // 빈이 스프링 컨테이너에 존재하지 않아도 예외를 발생 x
    private BoardMapper mapper;

    @Test
    @Transactional
    @Commit
    @Disabled  // 빌드 시 테스트하는걸 비활성화 시킨다는 의미
    public void testInsert100() {

        // 더미 테이터 반복문 (반복문이랑 같음)
        IntStream.rangeClosed(1, 100).forEach(j -> {
            // 가짜 더미 데이터
            BoardRegisterDTO dto = BoardRegisterDTO.builder()
                    .title("title")
                    .content("content")
                    .writer("writer")
                    .tag("aaa,bbb,ccc,ddd")
                    .fileNames(List.of(UUID.randomUUID() + ".jpg", UUID.randomUUID() + ".jpg"))
                    .build();

            mapper.insert(dto); // mapper 안에 dto 넣기

            Long bno = dto.getBno();

            log.info("bno : " + bno);

            // 파일 이름 반복문 처리
            for (int i = 0; i < dto.getFileNames().size(); i++) {
                mapper.insertAttach(bno, dto.getFileNames().get(i), i);
            } // end for

        }); // end stream
    }

}
