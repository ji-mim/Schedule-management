use schedule;

CREATE TABLE user
(
    name VARCHAR(45) PRIMARY KEY COMMENT '유저 이름'
);

CREATE TABLE schedule
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '일정 식별자',
    password VARCHAR(45) COMMENT '일정 비밀번호',
    title VARCHAR(45) COMMENT '일정 제목',
    content TEXT COMMENT '일정 내용',
    username VARCHAR(45) COMMENT '작성자 이름',
    FOREIGN KEY (username) REFERENCES user(name)
)