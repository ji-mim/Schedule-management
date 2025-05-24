use schedule;

CREATE TABLE user
(
    name VARCHAR(45) COMMENT '유저 이름',
    email VARCHAR(45) PRIMARY KEY COMMENT '유저 이메일',
    created_at datetime   comment '생성 일자',
    updated_at datetime   comment '수정 일자'
);

CREATE TABLE schedule
(
    id bigint auto_increment comment '일정 식별자'
        primary key,
    password   varchar(45) not null comment '일정 비밀번호',
    contents   text        not null comment '일정 내용',
    user_email      varchar(45) not null comment '작성자 이메일',
    created_at datetime    null comment '생성 일자',
    updated_at datetime    null comment '수정 일자',
    FOREIGN KEY (user_email) REFERENCES user(email)
)