# Coffee-pos-machine
커피 주문 시스템

<br />

## 💬 API 명세

### 기능 구현
  - 커피 메뉴 목록 조회하기
  - 커피 주문/결제하기
  - 인기 메뉴 목록 조회하기
  - 포인트 충전하기

### Swagger : [coffee-pos-machine](https://app.swaggerhub.com/apis-docs/deingvelop/coffee-pos-machine/1.0.0#/default/get_beverages_favorites)



<br />

## 📜 기술스택
|분류|기술|
| :-: |:- |
|Language|<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white">|
|Framework|<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=Springboot&logoColor=white">|
|Build Tool|<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">|
|DB|<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">|


<br />

## 🏰 아키텍쳐
<details>
<summary> <b>아키텍쳐 바로보기</b> </summary> 
준비중
</details>

<br />

## 📕 DB 설계
    
<details>
<summary> <b>ERD</b> </summary>

![erd - 230212.png](src%2Fmain%2Fresources%2Fstatic%2Ferd%20-%20230212.png)

</details>

    
<details>
<summary> <b>SQL - DDL </b> </summary>

```SQL
CREATE TABLE `beverage` (
    `id` BIGINT(20) NOT NULL auto_increment,
    `name` varchar(20) NOT NULL,
    `price` INT(11) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `user` (
    `id` BIGINT(20) NOT NULL auto_increment,
    `point` BIGINT(20) NOT NULL DEFAULT 0,
    PRIMARY KEY(`id`)
);

CREATE TABLE `point_log` (
    `id` BIGINT(20) NOT NULL auto_increment,
    `point` BIGINT(20) NOT NULL,
    `uesr_id` BIGINT(20) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `orderLog` (
    `id` BIGINT(20) NOT NULL auto_increment,
    `created_at` DATETIME NOT NULL,
    `user_id` BIGINT(20) NOT NULL,
    `beverage_id` BIGINT(20) NOT NULL,
    PRIMARY KEY(`id`),
    KEY idx_created_at(`created_at`)
);
```

</details>



<br />

## ✨ 기술적인 도전

1. Kafka로 로그 데이터 전송하기 (+ ELK-stack)
2. JPA 및 Redis로 동시성 제어
3. 대용량 트래픽 관련 도전
    - Nginx로 로드 밸런싱
4. 캐싱으로 성능 향상시키기
    - Redis로 일주일간 데이터 캐싱?
  
<br />
