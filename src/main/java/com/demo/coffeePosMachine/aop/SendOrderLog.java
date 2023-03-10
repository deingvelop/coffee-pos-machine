package com.demo.coffeePosMachine.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 주문 내역(로그)을 데이터 수집 플랫폼으로 전송하는 어노테이션 생성
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SendOrderLog {
}
