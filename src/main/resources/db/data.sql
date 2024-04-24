-- user 더미 생성
insert into user_tb(email, password, name, phone, state, birth, created_at)
values ('ssar@nate.com', '1234', '김쌀', '01012341234', 'ACTIVE', '2000-01-01', now());
insert into user_tb(email, password, name, phone, state, birth, created_at)
values ('cos@nate.com', '1234', '이코스', '01012345678', 'ACTIVE', '2003-03-03', now());
insert into user_tb(email, password, name, phone, state, birth, created_at)
values ('love@nate.com', '1234', '박러브', '01087654321', 'ACTIVE', '1999-05-05', now());

-- company 더미 생성
insert into company_tb(email, password, businessName, businessNumber, businessAddress, phone, name, state, created_at)
values ('com1@nate.com', '1234', '김숙박회사', '108-32-34324', '부산시 해운대구', '01011112222', '김회사', 'ACTIVE', now());
insert into company_tb(email, password, businessName, businessNumber, businessAddress, phone, name, state, created_at)
values ('com2@nate.com', '1234', '이숙소회사', '432-51-44324', '경상남도 창원시', '01022223333', '이회사', 'ACTIVE', now());
insert into company_tb(email, password, businessName, businessNumber, businessAddress, phone, name, state, created_at)
values ('com3@nate.com', '1234', '박스테이회사', '723-45-23123', '서울특별시', '01077776666', '박회사', 'ACTIVE', now());

-- admin 더미 생성
insert into admin_tb(name, password, created_at)
values ('admin1', '1234', now());
insert into admin_tb(name, password, created_at)
values ('admin2', '1234', now());
insert into admin_tb(name, password, created_at)
values ('admin3', '1234', now());

-- stay 더미 생성
-- 일단 이미지는 생략
insert into stay_tb(company_id, name, category, address, intro, information, created_at)
values ('1', '호텔 블루 하버', '호텔', '부산시 해운대구 좌동순환로 99 (좌동)', '해운대 최고의 오션뷰를 자랑하는 숙소!', '객실 내 취사 금지', now());
insert into stay_tb(company_id, name, category, address, intro, information, created_at)
values ('2', '호텔 오아시스', '호텔', '경상남도 창원시 원이대로 998 (상남동)', '창원시 최고의 반쪽 숙소!', '객실 내 취사 금지', now());
insert into stay_tb(company_id, name, category, address, intro, information, created_at)
values ('3', '호텔 미라클랜드', '호텔', '서울시 중구 장충동2가 300', '서울시 중구의 중구파 숙소!', '객실 내 취사 금지', now());

-- option 더미 생성
-- 아이콘은 fontAwesome 으로 넣어둠.
insert into option_tb(stay_id, name, iconName, created_at)
values ('1', '피트니스', 'dumbbell', now());
insert into option_tb(stay_id, name, iconName, created_at)
values ('1', '침대', 'bad', now());
insert into option_tb(stay_id, name, iconName, created_at)
values ('1', '미니바', 'glassEmpty', now());
insert into option_tb(stay_id, name, iconName, created_at)
values ('1', '와이파이', 'wifi', now());
insert into option_tb(stay_id, name, iconName, created_at)
values ('1', '수영장', 'waterLadder', now());

insert into option_tb(stay_id, name, iconName, created_at)
values ('2', '침대', 'bed', now());
insert into option_tb(stay_id, name, iconName, created_at)
values ('2', '와이파이', 'wifi', now());
insert into option_tb(stay_id, name, iconName, created_at)
values ('2', '피트니스', 'dumbbell', now());

insert into option_tb(stay_id, name, iconName, created_at)
values ('3', '침대', 'bed', now());
insert into option_tb(stay_id, name, iconName, created_at)
values ('3', '와이파이', 'wifi', now());

-- room 더미 생성