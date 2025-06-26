package com.busanit501.service;

import com.busanit501._3jdbc.dto.TodoDTO;
import com.busanit501._3jdbc.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@Log4j2
public class TodoServiceTests {
    // 3의 TodoService 에서 등록기능 불러오기
    private TodoService  todoService;

    //
    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE; //초기화

    }

    // 등록기능 서비스
    @Test
    public void testRegister() throws Exception {
        // 화면 --> 컨트롤러 ...DTO 받아왔다고 가정
        TodoDTO todoDTO = TodoDTO.builder()
                .title("서비스, 단위테스트 진행")
                .dueDate(LocalDate.now())
                .build();
            log.info("TodoService 테스트 : ");
                log.info("TodoService 테스트에서 todoDTO : " +todoDTO);

        // 서비스에서 기능 테스트
        todoService.register(todoDTO);

    }

    // 하나조회 기능 테스트

    @Test
    public void testGetByTno() throws Exception {
        // 실제 조회할 디비 데이터 내용파악
        TodoDTO todoDTO = todoService.getByTno(3L);
        log.info("하나조회 기능 단위테스트 :"+todoDTO);
            log.info("DAO -> SERVICE 과정 ");
    }

    // 삭제 기능 테스트
    @Test
    public void testRemove() throws Exception {
        // 실제 삭제할 tno 번호 파악
        todoService.remove(10L); // 삭제할거라 받는게 필요없음.
        log.info("DAO -> SERVICE 에서 삭제과정 ");
    }

    // 수정기능 테스트
    @Test
    public void testModify() throws Exception {
        // 수정할 tno 번호 파악
        TodoDTO todoDTO = todoService.getByTno(4L);
        todoDTO.setTitle("제목 변경하기.");
        log.info("서비스 단위테스트 수정 확인. todoDTO : "+todoDTO);

        todoService.modify(todoDTO);
        log.info("서비스 단위테스트 수정 확인2 ");
    }
}
