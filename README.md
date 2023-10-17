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

## ✍🏻 도전 과제
- 동시성 관련 테스트코드 작성 및 DB Lock을 활용한 동시성 제어
- Swagger를 활용한 API Documentation
- Redis 캐싱을 활용하여 인기 메뉴 조회 성능 최적화
- Redis 분산 락을 활용하여 동시성 제어
- Kafka를 활용하여 별도의 데이터 수집 플랫폼으로 로그를 보내는 로직 작성


<br />

## 📜 기술스택
|분류|기술|
| :-: |:- |
|Language|<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white">|
|Framework|<img src="https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=Springboot&logoColor=white">|
|Build Tool|<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">|
|DB|<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">|
|Caching & Locking|<img src="https://img.shields.io/badge/redis-B71C1C?style=for-the-badge&logo=redis&logoColor=white">|
|Logging|<img src="https://img.shields.io/badge/Apache Kafka-3498DB?style=for-the-badge&logo=apachekafka&logoColor=white"> |
|Documentation|<img src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white"> |


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

CREATE TABLE `order` (
    `id` BIGINT(20) NOT NULL auto_increment,
    `created_at` DATETIME NOT NULL,
    `user_id` BIGINT(20) NOT NULL,
    `beverage_id` BIGINT(20) NOT NULL,
    PRIMARY KEY(`id`),
    KEY idx_created_at(`created_at`)
);
```

</details>
