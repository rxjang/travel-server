# 여행지(도시) 관리 API 서버
여행지 (도시)를 관리하는 REST API서버 입니다. 

## 사용 기술
* Java 11
* Spring boot 2.7.5
* Gradle 7.5.1
* Jpa, Querydsl
* H2, MySQL 8.0.31

## 실행 방법
📍 실행 환경은 `deployment`,  `test`,  `production` 이 있습니다.  
👉 `production` 환경 실행 시
* 아래 명령어로 MySql 컨테이너를 실행시킵니다. (db 스키마,  계정,  권한 관련 쿼리는  `src/main/resources/sql/init.sql` 에 있습니다. )
    ```bash
    docker-compose up
    ```
* `src/main/resources/sql/schema-mysql.sql` 내의 DDL을 실행해 테이블을 생성합니다. 
* `src/main/resources/sql/data-mysql.sql` 을 통해 dummy data를 insert합니다.

운영  환경이기에 실행시마다 자동으로 쿼리를 실행하지 않고 직접 sql을 입력하도록 했습니다. 

👉 그 외의 환경은 바로 스프링 애플리케이션을 빌드, 실행합니다.  

```bash
./gradlew build
java -jar -Dspring.profiles.active=${실행 환경} build/libs/travel-0.0.1-SNAPSHOT.jar 
```
위 명령어로 스프링 애플리케이션을 시작합니다. `deployment` 환경으로 실행 시,  `-Dspring.profiles.active=${실행 환경}` 는 제거해도 됩니다.
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
<img width="586" alt="스크린샷 2022-11-26 오전 1 35 01" src="https://user-images.githubusercontent.com/68838251/204029036-196c0eb6-6b29-4710-9ae8-0cb77cfa6baf.png">   

기능 동작 확인을 위해 간단하게 설계 했습니다.
 InnoDB를 DB Engine으로 사용했솝니다.

## 구현 API
### 여행지(도시) API
__base path: cities__

<details>
<summary>도시 등록</summary>
Request

```http request
POST /api/v1/cities HTTP/1.1
Content-Type: application/json

{
    "name" : "Seoul"
}
```
Response
```http response
HTTP/1.1 201 CREATED
Content-Type: application/json
Location: /api/v1/cities/1
```
</details>

<details>
<summary>도시 수정</summary>
Request

```http request
PATCH /api/v1/cities/1 HTTP/1.1
Content-Type: application/json

{
    "name" : "Jeju"
}
```
Response
```http response
HTTP/1.1 204 NO CONTENT
```
</details>


<details>
<summary>도시 삭제</summary>
Request

```http request
DELETE /api/v1/cities/1 HTTP/1.1
```
Response
```http response
HTTP/1.1 204 NO CONTENT
```
</details>


<details>
<summary>단일 도시 조회</summary>
Request

```http request
GET /api/v1/cities/1 HTTP/1.1
```
Response
```http response
HTTP/1.1 200 OK
Content-Type: application/json
{
    "id": 1,
    "name": "Seoul"
}
```
</details>

<details>
<summary>사용자별 도시 목록 조회 API</summary>
Request

```http request
GET /api/v1/cities?member=1 HTTP/1.1
```
Response
```http response
HTTP/1.1 200 OK
Content-Type: application/json
{
    "cities": [
        {
            "id": 1,
            "name": "Seoul",
            "travelStartDate": "2022-11-22T02:20:11",
            "enrolledAt": "2022-11-16T02:20:11",
            "searchedAt": null,
            "traveling": true
        },
        {
            "id": 2,
            "name": "Busan",
            "travelStartDate": "2022-11-27T02:20:11",
            "enrolledAt": "2022-11-24T02:20:11",
            "searchedAt": null,
            "traveling": false
        },
        {
            "id": 3,
            "name": "Gwangju",
            "travelStartDate": "2022-11-29T02:20:11",
            "enrolledAt": "2022-11-16T02:20:11",
            "searchedAt": null,
            "traveling": false
        },
        {
            "id": 4,
            "name": "Daegu",
            "travelStartDate": null,
            "enrolledAt": "2022-11-26T02:20:11",
            "searchedAt": null,
            "traveling": false
        },
        {
            "id": 6,
            "name": "Gangneung",
            "travelStartDate": null,
            "enrolledAt": "2022-11-26T02:20:11",
            "searchedAt": null,
            "traveling": false
        },
        {
            "id": 8,
            "name": "Jeju",
            "travelStartDate": null,
            "enrolledAt": "2022-11-26T02:20:11",
            "searchedAt": null,
            "traveling": false
        },
        {
            "id": 10,
            "name": "London",
            "travelStartDate": null,
            "enrolledAt": "2022-11-26T02:20:11",
            "searchedAt": null,
            "traveling": false
        },
        {
            "id": 12,
            "name": "Guam",
            "travelStartDate": null,
            "enrolledAt": "2022-11-26T02:20:11",
            "searchedAt": null,
            "traveling": false
        },
        {
            "id": 11,
            "name": "NewYork",
            "travelStartDate": null,
            "enrolledAt": "2022-11-24T02:20:11",
            "searchedAt": "2022-11-26T02:20:11",
            "traveling": false
        },
        {
            "id": 1,
            "name": "Seoul",
            "travelStartDate": null,
            "enrolledAt": "2022-11-16T02:20:11",
            "searchedAt": "2022-11-20T02:20:11",
            "traveling": false
        },
        {
            "id": 5,
            "name": "Daejeon",
            "travelStartDate": null,
            "enrolledAt": "2022-11-24T02:20:11",
            "searchedAt": null,
            "traveling": false
        }
    ]
}
```
</details>

### 여행 관리 API
__base path: travel__

<details>
<summary>여행 등록</summary>
Request

```http request
POST /api/v1/travel HTTP/1.1
Content-Type: application/json

{
    "cityId" : 1,
    "memberId" : 1,
    "startDate": "2022-11-22T10:54:18",
    "endDate": "2022-11-22T11:54:18"
}
```
Response
```http response
HTTP/1.1 201 CREATED
Content-Type: application/json
Location: /api/v1/travel/1
```
</details>

<details>
<summary>여행 수정</summary>
Request

```http request
PATCH /api/v1/travel/1 HTTP/1.1
Content-Type: application/json

{
    "endDate": "2022-11-24T10:54:18"
}
```
Response
```http response
HTTP/1.1 204 NO CONTENT
```
</details>


<details>
<summary>여행 삭제</summary>
Request

```http request
DELETE /api/v1/travel/1 HTTP/1.1
```
Response
```http response
HTTP/1.1 204 NO CONTENT
```
</details>


<details>
<summary>단일 여행 조회</summary>
Request

```http request
GET /api/v1/travel/1 HTTP/1.1
```
Response
```http response
HTTP/1.1 200 OK
Content-Type: application/json
{
    "id": 1,
    "cityName": "Sydney",
    "tourist": "test",
    "startDate": "2022-11-16T02:20:11",
    "endDate": "2022-11-23T02:20:11"
}
```
</details>

## 구현 내용
###  👉 환경별로 데이터베이스 구분 해 application.yml 작성
스프링 부트 2.4 버전 이상을 사용해 설정 값은 application.yml 파일 하나에 관리하기로 했습니다.
```yaml
spring:
  profiles:
    active: deployment

---
spring:
  config:
    activate:
      on-profile: deployment, test

  jpa:
    database: h2

    ...
    
---
spring:
  config:
    activate:
      on-profile: production

  jpa:
    database: mysql
    
    ...

```
기본 환경은 상단에 `deployment`로 선언했습니다. `deployment` 와 `test` 환경은 `h2` DB를 사용하므로 같이 구성했고, 
`production` 환경은 `mysql`을 사용하도록 구분했습니다.
###  👉 지정된 여행이 없을 경우만 도시 삭제
여행 기간이 지난 여행이라도, 여행이 한 번이라도 등록되면 삭제 불가능하게 구현했습니다. 당장의 여행이 없더라도, 사용자가 기록을 확인 해야 한다고 생각했습니다.
``` java
private void checkCouldDeleteCity(City city) {
    if (travelRepository.existsByCity(city)) {
        throw new CannotDeleteCityException();
    }
}
```
`existsByCity`는 jpql로, 아래와 같이 쿼리가 살행됩니다.
```sql
SELECT t.id FROM travel t WHERE tcity_id = 1 limit 1;
```
limit을 통해 존재 여부를 확인하고, java code에서는 boolean값으로 받게 됩니다. 
지정된 여행이 있을 시에는 `CannotDeleteCityException` 이 발생하며, 진행 중인 여행이 있다는 안내 문구를 보여줍니다.
```json
{
    "message": "진행 중인 여행이 있으므로 도시 삭제가 불가능합니다."
}
```
### 👉 여행 종료일은 미래만 허용
여행을 등록, 수정할 때 모두 종료일을 체크 해야하므로, 해당 로직은 `travel` 도메인 내에 구현했습니다.
``` java
public void validateEndDate() {
    if (isEndDateBeforeNow() || isEndDateBeforeStartDate()) {
        throw new InvalidDateException();
    }
}

private boolean isEndDateBeforeNow() {
    return this.endDate.isBefore(LocalDateTime.now());
}

private boolean isEndDateBeforeStartDate() {
    return this.endDate.isBefore(this.startDate);
}
```
`isEndDateBeforeNow()`  는 종료일이 현재보다 과거인지를,  `isEndDateBeforeStartDate()`  는 시작일이 종료일보다 과거인지를 체크합니다.
`validateEndDate()`  는 둘 중 하나의 값이라도 ture가 나오면  `InvalidDateException`  예외를 던집니다. 사용자는 아래의 메시지를 받게 됩니다.
```json
{
    "message": "여행 종료일은 현재 또는 시작일보다 미래여야 합니다."
}
```
### 👉 사용자별 도시 목록 조회 정렬
정렬 기준은 아래와 같습니다.
* 중복 없이 상위 10개의 도시만 노출 (Pagination 없음)
* 여행 중인 도시는 중복이 허용되며 노출 개수와 무관
* 여행 중인 도시: 여행 시작일이 빠른 것 부터
* 여행 예정된 도시: 여행 시작일이 가까운 것부터
* 하루 이내에 등록된 도시: 기장 최근에 등록한 것부터 ==> 하루 이내에 등록된 도시만 정렬
* 최근 일주일 이내에 한 번 이상 조회된 도시: 가장 최근에 조회한 것부터
* 위의 조건에 해당하지 않는 모든 도시: 무작위  

여행 중인 도시는 중복이 허용됩니다. 즉, 사용자가 서울을 여행중이고, 다른 정렬 조건에 또 서울이 있다면 서울은 목록 내에 2번 보일 수 있고, 총 목록의 갯수는 11개가 됩니다. 
이 조건과 여행 중인 도시는 노출 개수와 무관하다는 조건을 통해, 해당 로직은 총 2번의 쿼리로 구현했습니다. 
``` java
public List<CityByMemberResponse> findTravelingCity(final Long memberId, final LocalDateTime now) {
    return jpaQueryFactory
            .select(new QCityByMemberResponse(
                    city.id,
                    city.name,
                    travel.startDate,
                    city.createdAt
            ))
            .from(travel)
            .join(travel.city, city)
            .where(travel.member.id.eq(memberId),
                    travel.startDate.before(now),
                    travel.endDate.after(now))
            .orderBy(travel.startDate.asc())
            .fetch();
}
```
여행 중인 도시를 찾는 쿼리 입니다. 이미 여행이 끝났거나, 여행이 시작되지 않은 데이터는 제외 시켜야되므로 where절에 위와 같이 구현했습니다. 
여행 중인 도시는 시작일이 빠른 것이 먼저 노출되므로, `startDate`  오름차순으로 정렬했습니다.
``` java
public List<CityByMemberResponse> findCitiesByMemberId(final Long memberId, final LocalDateTime now) {

        DateTimeExpression<LocalDateTime> enrolledOrder = new CaseBuilder()
                .when(city.createdAt.after(now.minusDays(1)))
                .then(city.createdAt)
                .otherwise(LocalDateTime.MIN);

        return jpaQueryFactory
                .select(new QCityByMemberResponse(
                        city.id,
                        city.name,
                        Expressions.asBoolean(false),
                        travel.startDate,
                        city.createdAt,
                        cityViewHistory.createdAt
                ))
                .from(city)
                .leftJoin(travel).on(city.eq(travel.city),
                        travel.member.id.eq(memberId),
                        travel.startDate.after(now))
                .leftJoin(cityViewHistory).on(city.eq(cityViewHistory.city),
                        cityViewHistory.member.id.eq(memberId),
                        cityViewHistory.createdAt.after(now.minusWeeks(1)))
                .orderBy(
                        travel.startDate.asc().nullsLast(), // 1
                        enrolledOrder.desc(),   // 2
                        cityViewHistory.createdAt.desc())   // 3
                .limit(10)
                .fetch();
    }
```
우선 _조회 여부는 해당 API를 사용하는 사용자의 조회 이력을 기준으로 했습니다._  도시들 중에 여행, 조회 여부와 무관한 도시가 있을 수 있으므로  `travel`,  `cityViewHistory` 와 leftJoin을 사용했습니다. 
사용자 정보는 이 두 테이블과 연관이 있으므로, 관련 조건은 on절 안으로 넣었습니다.
orderBy에는 총 세 가지의 정렬 조건이 있습니다. 
1. 여행 시작일이 빠른 것부터  
    우선 여행 시작일의 오름차순으로 정렬했습니다. 그러자 여행 데이터가 없는(null) 도시들이 먼저 보여져, nullLast()를 통해 null 값이 나중에 오도록 했습니다.
2. 하루 이내에 등록된 도시는 가장 최근에 등록한 것부터  
    우선 총 목록에서 하루 이내 등록 된(등록은 과거만 가능)도시만 걸러야 했으므로, `DateTimeExpression()` 를 사용했습니다.
    하루 이내일 경우는 등록된 시간으로, 아닐 경우는 `LocalDateTime.MIN` 값을 주어 내림차순으로 정렬했습니다. 
3. 최근 일주일 이내 조회 이력이 있는 도시는 가장 최근에 조회한 것부터  
   일주일 이내에 조회된 이력이 없는 도시의 조회 데이터는 필요하지 않으므로 on절에서 제외시켰습니다. 그리곤 조회일 내림차순으로 정렬했습니다.

### 👉 사용자별 도시 목록 조회 API Response
해당 API의 response는 `List<CityByMemberResponse>`가 아니라, 이를 객체로 한 번 감 싼 `CityByMemberListResponse` 입니다.
추후 list 밖의 추가 데이터가 들어 갈 수 도있어 확장성을 고려했고,
react를 사용했을 때 List로 바로 받는 것보다 상위에서 한 번 감싸져 오는게 편했던 경험이 있어 다음과 같이 구현했습니다. 

## 테스트 내역
테스트에는 Junit5, BDDMockito, MockMVC, AssertJ를 사용했습니다. API검증 테스트는 모두 진행 했으며, 단위 테스트는 City만 진행했습니다. 
테스트는 `src/test/java/io/travel` 에서 확인할 수 있습니다. 실행 내역은 아래와 같습니다. 

## 개선이 필요한 항목
### 📍 로그인 기능 구현 -> member정보 파라미터로 넘기지 않기
현재 서버는 로그인 기능이 없어서 member정보를 param으로 넘기게 설계되어 있습니다. (GET /api/v1/cities?member={id}). 
로그인한 사용자는 자신의 정보를 param으로 넘길일이 없도록, Session 또는 jwt로 사용자 정보를 얻을 수 있도록 개선하는 부분이 필요합니다. 




