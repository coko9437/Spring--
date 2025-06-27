package com.busanit501._3jdbc.domain;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    // DAO에서 사용하는 박스
    private String mid;
    private String mpw;
    private String mname;
    // 추가, 자동로그인 완성
    private String uuid;
}
