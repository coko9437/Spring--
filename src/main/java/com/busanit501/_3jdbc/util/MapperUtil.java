package com.busanit501._3jdbc.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

public enum MapperUtil {
    INSTANCE;

    // 변환도구 불러오기
    private ModelMapper modelMapper; //선언

    MapperUtil() { // 생성자
        // DTO <--> VO 2개의 모델클래스 변환하기위한 설정.
        this.modelMapper = new ModelMapper(); //초기화
        this.modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT); //일치여부 확인

//        .setFieldMatchingEnabled(true)
        // 필드 매칭을 활성화합니다. 이를 통해 ModelMapper는
        // 객체 간 필드 이름이 일치할 경우 직접 매핑을 수행할 수 있습니다.

//        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
        // 접근 레벨을 PRIVATE으로 설정합니다. 이렇게 하면 ModelMapper
        // private 필드까지 접근하여 매핑할 수 있게 됩니다.

//        .setMatchingStrategy(MatchingStrategies.STRICT) -
        // 매칭 전략을 STRICT(엄격)으로 설정합니다.
        // 이 설정은 소스와 대상의 필드 이름이 정확히
        // 일치해야만 매핑을 수행하도록 합니다.





    }
    public ModelMapper get() {
        return modelMapper;
    }
}
