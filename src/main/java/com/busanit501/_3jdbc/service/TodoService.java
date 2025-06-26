package com.busanit501._3jdbc.service;

import com.busanit501._3jdbc.dao.TodoDAO;
import com.busanit501._3jdbc.domain.TodoVO;
import com.busanit501._3jdbc.dto.TodoDTO;
import com.busanit501._3jdbc.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {
    INSTANCE;

    // 다른기능 불러와서 사용.
    // (1) DB 로직처리 DAO기능
    private TodoDAO dao;

    // DTO <--> VO 변환해주는 도구 (모델 맵퍼)
    private ModelMapper modelMapper;

    TodoService() {
        dao = new TodoDAO();
//        this.modelMapper = new ModelMapper(); //초기화
//        this.modelMapper.getConfiguration().setFieldMatchingEnabled(true)
//                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
//                .setMatchingStrategy(MatchingStrategies.STRICT); //일치여부 확인

        modelMapper = MapperUtil.INSTANCE.get(); // MapperUtil 클래스에서 만들어놓은거 가져오기

    }

    // 등록기능
//    화면에서 데이터 입력후 전달 -> 컨트롤러에서 받아서 DTO에 담기
//    -> DTO를 서비스에 전달 -> (서비스) DTO를 VO로 변환 -> DB에 전달
//    -> DB에서 VO처리하고 서비스에 전달 -> (서비스) VO를 DTO로 변환
//    -> DTO를 화면에 전달.
//    ** DAO가 DB를 쓰는 과정임. **
    // 전달 개요 : 화면 -> 컨트롤러(C) -> 서비스 (S):현위치 - > DAO() -> DB
    public void register(TodoDTO todoDTO) throws  Exception {
            //System.out.println("화면으로부터 받은 데이터 확인. todoVO : "+todoDTO);
            log.info("화면으로부터 받은 데이터 확인. todoVO : "+todoDTO);
        // DTO --> VO로 변환
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
            //System.out.println("TodoService에서 변환 데이터 확인. todoVO : "+todoVO);
        log.info("TodoService에서 변환 데이터 확인. todoVO : "+todoVO);
        // 서비스 --> DAO의 기능을 이용하자(=의존하자, 재사용하자)
        dao.insert(todoVO);


    }
    // 목록기능 (전체조회)
    public List<TodoDTO> listAll() throws Exception {
        // DAO에서, DB에서 데이터 전체조회 기능 있음. 일단 가져오기
        List<TodoVO> voList = dao.selectAll();
        log.info("현재 TodoService 작업중. listAll()");
        log.info("데이터 확인 : " +voList);

//        전.
        // TodoVO -> TodoDTO로 변환 (전체조회라서 반복문으로 전부 반복)
//        List<TodoDTO> dtoListFor = new ArrayList<TodoDTO>();
//        for(int i=0; i < voList.size(); i++){
//            TodoVO todoVO = voList.get(i);
//            TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
//            dtoListFor.add(todoDTO);
//        }

//        후
//        병렬처리
        List<TodoDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }


    // 하나 조회 DTO -> VO로 변환해야함.
    public TodoDTO getByTno(long tno) throws  Exception {
        log.info("TodoService. 하나조회 기능");
        // DAO 로 부터 전달받은 데이터타입
        TodoVO todoVo = dao.selectOne(tno);
        // 받은 VO -> DTO 변환하기.
        TodoDTO todoDTO = modelMapper.map(todoVo, TodoDTO.class);
        return todoDTO;
        // 기능 만들고 뭐한다? 단위테스트 ---> TodoServiceTests

    }


}
