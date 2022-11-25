# 여행지(도시) 관리 API 서버
여행지 (도시)를 관리하는 REST API서버 입니다. 

## 사용 기술
* Java 11
* Spring boot 2.7.5
* Gradle 7.5.1
* Jpa, Querydsl
* H2, MySQL 8.0.31

## 실행 방법
📍 실행 환경은 `deployment`, `test`, `production`이 있으며, default는 `deployment`입니다.  
👉 `production` 환경 실행 시, 아래 명령어로 MySql 컨테이너를 실행시킵니다.  
👉 db 스키마, 계정, 권한 관련 쿼리는 `src/main/resources/sql/init.sql`에 있습니다.
```bash
docker-compose up
```
그 외의 환경은 바로 스프링 애플리케이션을 빌드, 실행합니다. `deployment`환경으로 실행 시, `-Dspring.profiles.active=${실행 환경}`는 제거해도 됩니다. 
```bash
./gradlew build
java -jar -Dspring.profiles.active=${실행 환경} build/libs/travel-0.0.1-SNAPSHOT.jar 
```
## 프로젝트 구조
```
📂 src
├── 📂 main
│   ├── 📂 java.io.travel
│   │   ├── 📂 city          - 도시 domain (이하 모든 domain 폴더 구조 동일)
│   │   │    ├── 📂 api       - rest api
│   │   │    ├── 📂 model     - dto, entity 
│   │   │    ├── 📂 repository 
│   │   │    └── 📂 service   - buisness 로직 有
│   │   ├── 📂 common         - 서비스 공통 사항 (eg, 전역 에러 헨들링)
│   │   ├── 📂 config         - jpa, querydsl 설정
│   │   ├── 📂 exception      - Exception 파일
│   │   ├── 📂 member         - 회원 domain
│   │   ├── 📂 travel         - 여행 domain
│   │   └── 📄 TravelApplication.java
│   └── 📂 resources
│       ├── 📂 sql               - sql 쿼리 파일
│       └── 📄 appilcation.yml   - 프로젝트 설정값
├── 📂 test
├── 📂 java.io.travel
│   │   ├── 📂 city              - city domain api, service test
│   │   ├── 📂 common            - test에서 쓰일 전역 value
│   │   └── 📂 travel            - travel domain api test
│   └── 📂 resources
└──     └── 📄 appilcation.yml   -test 프로젝트 설정값
```
## DB 구조
기능 동작 확인을 위해 간단하게 설계 했습니다. 
InnoDB를 DB Engine으로 사용했솝니다.

## 구현 기능 및 API 명세
### 여행지(도시) API
base path: cities  

`도시 등록`

`도시 수정`

`도시 삭제`

`단일 도시 조회`

`사용자별 도시 목록 조회 API`


### 여행 관리 API
`여행 등록`

`여행 수정`

`여행 삭제`

`여행 도시 조회`

