package com.demo.coffeePosMachine.aop.logging;

import com.demo.coffeePosMachine.exception.BusinessException;
import com.demo.coffeePosMachine.exception.ErrorCode;
import com.demo.coffeePosMachine.order.OrderResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    private static final String TOPIC = "order";
    @Autowired
    private KafkaTemplate<String, String> orderKafkaTemplate;

//    ListenableFuture<SendResult<String, OrderResponseDto>> future = orderKafkaTemplate.send(empTopicName, OrderResponseDto);

    @AfterReturning(value = "@annotation(com.demo.coffeePosMachine.aop.logging.SendOrderLog)", returning = "result")
    public void sendSuccessLog(JoinPoint joinPoint, Object result) throws RuntimeException {

        OrderLogDto orderLog = new OrderLogDto((OrderResponseDto) result);
        log.info("▶▶▶▶▶▶▶▶ GIVING DATA TO KAFKA: " + orderLog.toString());

        this.orderKafkaTemplate.send(TOPIC, orderLog.toString());
    }

    @AfterThrowing(value = "@annotation(com.demo.coffeePosMachine.aop.logging.SendOrderLog)", throwing = "exception")
    public void sendErrorLog(JoinPoint joinPoint, Exception exception) throws RuntimeException {
        ErrorCode e = ((BusinessException) exception).getErrorCode();
        String errorInfo = "OrderException{" +
                "exceptionClass=" + exception.getClass().getSimpleName() +
                ", exceptionCode=" + e.getCode() +
                ", error message=" + e.getMessage() +
                '}';
        log.info("▶▶▶▶▶▶▶▶ GIVING DATA TO KAFKA: " + errorInfo);

        this.orderKafkaTemplate.send(TOPIC, errorInfo);

    }
}
