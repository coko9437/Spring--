package com.busanit501;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {

    @Test
    public void test1() {
        int v1 = 10;
        int v2 = 10;
                    // .assertEquals : 같은지 확인하는 메서드
        Assertions.assertEquals(v1, v2);
                    // 같으면 성공/ 다르면 실패
    }

    // 2번째 테스트, DB 연결 테스트
    @Test
    public void testConnection() throws Exception {
        // 1. 드라이버 연결
        Class.forName("org.mariadb.jdbc.Driver");

        // 2. 연결 커넥션 객체 도구 이용
        // 접근하는 DB 서버 1)주소, 2)계정, 3)패스워드
        Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/webdb",
                "webuser",
                "webuser");

        // 3. 연결 도구의 객체가 유효한가 체크하기
        Assertions.assertNotNull(connection); //NULL이 아니면 성공 /NULL이면 실패

        // 4. 확인 후, 자원반납
        connection.close();
    }
}
