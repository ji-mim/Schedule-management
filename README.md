# API 명세서 


| 기능       | Method | URL                       | Request         | Response | 상태코드     | 에러 메시지 설명                                                                    |
|----------|--------|---------------------------|------------------|--------|--------------|------------------------------------------------------------------------------|
| 유저 등록    | POST   | `/users`                  | 요청 body        | 등록된 유저 정보 | 201 CREATED | email 형식이 맞지 않거나, 누락될 경우<br/>                                                |
| 유저 조회        | GET    | `/users/{email}`          | 경로 param                          | 유저 정보  | 200 OK       | 해당 email로 등록된 유저가 없을 경우                    |
| 유저 이름 변경   | PATCH  | `/users/{email}`          | 요청 body                        | 수정된 유저 정보 | 200 OK       | email에 해당하는 유저가 존재하지 않을 경우 |
| 일정 등록    | POST   | `/schedules`              | 요청 body        | 등록된 일정 정보 | 201 CREATED | 1. email 형식이 맞지 않거나, 누락될 경우<br/>2.password 가 누락될 경우<br/>3.할일이 누락되거나 200자를 넘길경우 |
| 일정 조회    | GET    | `/schedules/{scheduleId}` | 경로 param       | 단건 일정 정보 | 200 OK       | ID가 존재하지 않을 경우 에러 메시지                                                        |
| 일정 목록 조회 | GET    | `/schedules`              | 쿼리 param (userEmail, updatedAt) | 다건 일정 정보 | 200 OK       | email이나 updatedAt에 해당하는 결과가 없으면 빈 배열 반환                                      |
| 일정 수정    | PATCH  | `/schedules/{scheduleId}` | 요청 body        | 수정된 일정 정보 | 200 OK       | 1. 등록된 email이 존재하지 않을 때<br/>2. password가 일치하지 않거나 누락될 때<br/>3.할일이 누락되거나 200자를 넘길 때 |
| 일정 삭제    | DELETE | `/schedules/{scheduleId}` | 요청 body        | 없음     | 200 OK       | ID가 존재하지 않거나 비밀번호가 불일치할 때, 비밀번호가 누락될 때                                       |
| 일정 페이징 조회 | GET    | `/schedules/page`         | 쿼리 param (userEmail, updatedAt, page, size) | 페이징된 일정 목록 | 200 OK       | email이나 updatedAt에 해당하는 결과가 없으면 빈 배열 반환, 페이지 범위 초과 시 빈 배열                    |

