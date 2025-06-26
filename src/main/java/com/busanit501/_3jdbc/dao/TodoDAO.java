package com.busanit501._3jdbc.dao;


import com.busanit501._3jdbc.domain.TodoVO;
import lombok.Cleanup;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TodoDAO {
//    1. 테스트용 으로 현재시간 가져오는 메서드
    // 굳이 DB 서버에서 받아오기.
    public String getTime() {
        String now = null; // 디비로부터 받은 시간을 담아둘 임시변수
        // 디비연결, SQL 전달, 값 가져오기. 자원 반납(자동으로 하기 위해서 try~resource).
        try(Connection connection = ConnectionUtil.INSTANCE.getConnection(); // ConnectionUtil : enum클래스
            PreparedStatement pstmt = connection.prepareStatement("select now()");
            ResultSet rs = pstmt.executeQuery();) {

            rs.next();
            now = rs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

//    2. 똑같은 기능으로...
//    자원 반납을 다르게 표현해보자.
//    Lombok 기능중에서 @Cleanup 이용해서 자동반납
    public  String getTime2() throws Exception {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement("select now()");
        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next(); // 가상테이블에서 데이터를 가져옴
        String now = rs.getString(1);
        return now;
    }


    // 등록기능... 화면에서 todo의 내용을 전달받아와서 DB에 넣을예정
//    화면에서 데이터를 각각 받는것보다 모델클래스에 담아서 전달함.
//    전달개요 : 화면 -> 컨트롤러(C) -> 서비스(S) -> DAO(현재위치) -> DB //
    public void insert(TodoVO todoVO) throws Exception{
        // 등록 insert sql 문장 작성.
        String sql = "insert into tbl_todo (title, dueDate, finished) values (?, ?, ?)";

//        디비에 연결할 객체 작성... 자원반납방법은 @Cleanup
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1, todoVO.getTitle());
        pstmt.setDate(2, Date.valueOf(todoVO.getDueDate()));
                    // Date.valueOf : java.time.LocalDate를 java.sql.Date로 변환하는 필수 작업
        pstmt.setBoolean(3, todoVO.isFinished());

        // 실제 디비서버에 반영하기. 쓰기 작업 진행.
        pstmt.executeUpdate();

    }

    // 전체 조회기능
    public List<TodoVO> selectAll() throws Exception{
        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
//        조회.. ResultSet 필요함.
        @Cleanup ResultSet rs = pstmt.executeQuery();

        // DB로부터 전달받은 내용(TodoVO의 tno, title 등)을 담아둘 임시 리스트
        List<TodoVO> list = new ArrayList<>();

//        검색 결과 -> 모델에 담고 -> 리스트에 담기, 반복
        while (rs.next()) { //다음 행(row)**이 존재하면 true를 반환하고, 커서를 그 다음 행으로 이동

            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate()) //java.sql.Date를 java.time.LocalDate로 변환
                    .finished(rs.getBoolean("finished"))
                    .build();
                // 모델(TodoVO) -> list 에 담기
            list.add(vo);
        }
        return list;
    }

    // 하나조회
    public  TodoVO selectOne(Long tno) throws Exception{
        String sql = "select * from tbl_todo where tno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, tno);
        // 조회, ResultSet 필요함.
        @Cleanup ResultSet rs = pstmt.executeQuery(); //조회라서 executeQuery()
        rs.next();

        TodoVO vo = TodoVO.builder()
                .tno(rs.getLong("tno"))
                .title(rs.getString("title"))
                .dueDate(rs.getDate("dueDate").toLocalDate())
                .build();
        return vo;

    }

//    한개 데이터 삭제하기.
    public void deleteOne(Long tno) throws Exception{
        String sql = "delete from tbl_todo where tno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, tno);
        pstmt.executeUpdate();

    }
//    데이터 수정하기.
                    // TodoVO todoVO -> 화면에서 변경할 데이터를 todoVO에 담아둠 즉 모델 클래스
    public void updateOne(TodoVO todoVO) throws Exception{
        String sql = "update tbl_todo set title = ?, dueDate = ?, finished = ? where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, todoVO.getTitle());
        pstmt.setDate(2, Date.valueOf(todoVO.getDueDate()));
        pstmt.setBoolean(3, todoVO.isFinished());
        pstmt.setLong(4, todoVO.getTno());
        pstmt.executeUpdate();

    }

}
