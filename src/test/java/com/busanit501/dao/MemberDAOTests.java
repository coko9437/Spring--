package com.busanit501.dao;

import com.busanit501._3jdbc.dao.MemberDAO;

import com.busanit501._3jdbc.domain.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@Log4j2
public class MemberDAOTests {
    private MemberDAO memberDAO; // 선언

    @BeforeEach // 각각의 @Test 메서드가 실행되기전에 실행되는메서드.
    public void ready() {
        memberDAO = new MemberDAO();
    }

    // 아이디, 패스워드 ,mid,mpw를 이용해서, 한명 회원 조회 하기.
    @Test
    public void getMember() throws Exception {
        String mid = "lsy";
        String mpw = "1234";

        MemberVO memberVO = memberDAO.getMemberVO(mid,mpw);
        log.info("조회한 멤버 : " + memberVO);
    }

    //mid로 유저의 uuid를 업데이트 시키는 기능
    @Test
    public void testUpdateUuid() throws Exception {
        String mid = "lsy";
        String uuid = UUID.randomUUID().toString();
        memberDAO.updateUuid(mid,uuid);
    }

    @Test
    public void testSearchWithUuid() throws Exception {
        // 실제 테이블에서 uuid를 복사해서 사용하기.
        String uuid = "cde3a4c7-abc5-4527-9fe2-a96d37ce3c8f";
        MemberVO memberVO = memberDAO.getMemberVOByUuid(uuid);
        log.info("uuid로 검색한 유저 : " + memberVO);
    }

}
