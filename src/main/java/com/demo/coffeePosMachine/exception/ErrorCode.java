package com.demo.coffeePosMachine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Business Exceptions
    USER_NOT_FOUND("USER_NOT_FOUND", "입력된 식별값은 등록되지 않은 사용자 ID입니다.", 404),
    BEVERAGE_NOT_FOUND("BEVERAGE_NOT_FOUND", "입력된 식별값은 등록되지 않은 음료 ID입니다.", 404),
    OUT_OF_POINT("OUT_OF_POINT", "입력된 음료는 현재 사용자의 포인트 잔액으로 주문할 수 없습니다.", 400),
    INVALID_POINT_VALUE("INVALID_POINT_VALUE", "포인트 충전은 양의 정수 단위로만 가능합니다.", 400),

    // Basic Exceptions
    INVALID_INPUT_VALUE("INVALID_INPUT_VALUE", "유효하지 않은 입력값입니다.", 400),
    TYPE_MISMATCH("TYPE_MISMATCH", "입력된 enum값이 유효하지 않습니다.", 400),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "유효하지 않은 HTTP method입니다.", 400),
    URL_NOT_FOUND("URL_NOT_FOUND", "URL을 찾을 수 없습니다.", 404),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다.", 500);

    private final String code;
    private final String message;
    private final int status;

}