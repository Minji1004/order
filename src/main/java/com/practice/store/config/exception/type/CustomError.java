package com.practice.store.config.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomError {
    E400("유효성 검사 실패"),
    E401("인증 실패"),
    E403("권한 없음"),
    E404("리소스 없음"),
    E500("시스템 오류");

    private String message;
}
