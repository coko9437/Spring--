package com.busanit501.dao.hello_project._3jdbc.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

//소스코드 생성이 아니라 롬복 이용해서 사용해보기

@Getter
@ToString
@Builder

public class TodoVO {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;

}

