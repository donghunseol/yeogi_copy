-- user 더미 생성
insert into user(email, password, name, phone, state, birth, created_at)
values ('ssar@nate.com', '1234', '김쌀', '01012341234', 'ACTIVE', '2000-01-01', now());
insert into user(email, password, name, phone, state, birth, created_at)
values ('cos@nate.com', '1234', '이코스', '01012345678', 'ACTIVE', '2003-03-03', now());
insert into user(email, password, name, phone, state, birth, created_at)
values ('love@nate.com', '1234', '박러브', '01087654321', 'ACTIVE', '1999-05-05', now());

-- company 더미 생성
insert into company(email, password, businessName, businessNumber, businessAddress, phone, name, state, created_at)
values ('com1@nate.com', '1234', '김숙박회사', '1083234324', '부산시 해운대구', '01011112222', '김회사', 'ACTIVE', now());
insert into company(email, password, businessName, businessNumber, businessAddress, phone, name, state, created_at)
values ('com2@nate.com', '1234', '이숙소회사', '4325144324', '경상남도 창원시', '01022223333', '이회사', 'ACTIVE', now());
insert into company(email, password, businessName, businessNumber, businessAddress, phone, name, state, created_at)
values ('com3@nate.com', '1234', '박스테이회사', '7234523123', '서울특별시', '01077776666', '박회사', 'ACTIVE', now());

-- admin 더미 생성
insert into admin(name, password, created_at)
values ('admin1', '1234', now());
insert into admin(name, password, created_at)
values ('admin2', '1234', now());
insert into admin(name, password, created_at)
values ('admin3', '1234', now());

-- stay 더미 생성
-- 일단 이미지는 생략
-- insert into stay(company_id, name, category, address, intro, information, created_at)
-- values ('1', '킹호텔', '')