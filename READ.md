# API 명세서 

|기능  | Method | URL | request |response | 상태코드        | 에러                       |
|-----|--------|-----|----------|--------|-------------|--------------------------|
|일정 등록| POST   |/api/schedules|요청 body | 등록 정보 | 201 CREATED | 비밀번호, 비밀번호 확인이  불일치하면 에러 메시지 | 
|일정 조회| GET    |/api/schedules/{scheduleId}|요청 param|단건 응답 정보 | 200 OK      | 찾으려는 ID가 없으면 에러 메시지      | 
|일정 목록 조회| GET    |/api/schedules| 요청 param|다건 응답 정보 | 200 OK | 작성자가 존재하지 않으면 에러 메시지     |
|일정 수정| PATCH  |/api/schedules/{scheduleId} | 요청 body | 수정 정보 | 200OK | id가 존재하지 않거나 비밀번호가 맞지 않을 때 에러 메시지 | 
|일정 삭제 | DELETE |/api/schedules/{scheduleId} | 요청 param |  - | 200OK | id가 존재하지 않거나 비밀번호가 맞지 않을 때 에러 메시지|


책 등록	POST	/api/books	요청 body	등록 정보	200: 정상등록
책 조회	GET	/api/books/{bookId}	요청 param	단건 응답 정보	200: 정상조회
책 목록 조회	GET	/api/books	요청 param	다건 응답 정보	200: 정상조회
책 수정	PUT	/api/books/{bookId}	요청 body	수정 정보	200: 정상수정
책 삭제	DELETE	/api/books/{bookId}	요청 param	-	200: 정상삭제