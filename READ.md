# API 명세서 


| 기능           | Method | URL                              | Request         | Response        | 상태코드     | 에러 메시지 설명 |
|----------------|--------|----------------------------------|------------------|------------------|--------------|------------------|
| 일정 등록       | POST   | `/schedules`                     | 요청 body        | 등록된 일정 정보 | 201 CREATED | 비밀번호 불일치 시 에러 메시지 |
| 일정 조회       | GET    | `/schedules/{scheduleId}`        | 경로 param       | 단건 일정 정보   | 200 OK       | ID가 존재하지 않을 경우 에러 메시지 |
| 일정 목록 조회   | GET    | `/schedules`                     | 쿼리 param (userEmail, updatedAt) | 다건 일정 정보   | 200 OK       | 작성자가 존재하지 않거나 결과 없음 |
| 일정 수정       | PATCH  | `/schedules/{scheduleId}`        | 요청 body        | 수정된 일정 정보 | 200 OK       | ID가 존재하지 않거나 비밀번호 불일치 |
| 일정 삭제       | DELETE | `/schedules/{scheduleId}`        | 요청 body        | 없음             | 200 OK       | ID가 존재하지 않거나 비밀번호 불일치 |
| 일정 페이징 조회 | GET    | `/schedules/page`                | 쿼리 param (userEmail, updatedAt, page, size) | 페이징된 일정 목록 | 200 OK       | 페이지 범위 초과 시 빈 배열 |

