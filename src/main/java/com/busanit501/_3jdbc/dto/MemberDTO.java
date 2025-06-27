package com.busanit501._3jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MemberDTO {
    public String mid;
    public String mpw;
    public String mname;
    // 추가, 자동로그인 완성
    private String uuid;
}
