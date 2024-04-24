-- user 더미 생성
insert into user_tb(email, password, name, phone, state, birth, created_at, report_count)
values ('ssar@nate.com', '1234', '김쌀', '01012341234', 'ACTIVE', '2000-01-01', now(), 0),
       ('cos@nate.com', '1234', '이코스', '01012345678', 'ACTIVE', '2003-03-03', now(), 0),
       ('love@nate.com', '1234', '박러브', '01087654321', 'ACTIVE', '1999-05-05', now(), 0);

-- company 더미 생성
insert into company_tb(email, password, business_name, business_number, business_address, phone, name, state,
                       created_at, report_count)
values ('com1@nate.com', '1234', '김숙박회사', '108-32-34324', '부산시 해운대구', '01011112222', '김회사', 'ACTIVE', now(), 0),
       ('com2@nate.com', '1234', '이숙소회사', '432-51-44324', '경상남도 창원시', '01022223333', '이회사', 'ACTIVE', now(), 0),
       ('com3@nate.com', '1234', '박스테이회사', '723-45-23123', '서울특별시', '01077776666', '박회사', 'ACTIVE', now(), 0);

-- admin 더미 생성
insert into admin_tb(name, password, created_at)
values ('admin1', '1234', now()),
       ('admin2', '1234', now()),
       ('admin3', '1234', now());

-- stay 더미 생성
insert into stay_tb(company_id, name, category, address, intro, information, created_at)
values ('1', '호텔 블루 하버', '호텔', '부산시 해운대구 좌동순환로 99 (좌동)', '해운대 최고의 오션뷰를 자랑하는 숙소!', '객실 내 취사 금지', now()),
       ('2', '호텔 오아시스', '호텔', '경상남도 창원시 원이대로 998 (상남동)', '창원시 최고의 반쪽 숙소!', '객실 내 취사 금지', now()),
       ('3', '호텔 미라클랜드', '호텔', '서울시 중구 장충동2가 300', '서울시 중구의 중구파 숙소!', '객실 내 취사 금지', now());

-- option 더미 생성
insert into option_tb(stay_id, name, icon_name, created_at)
values ('1', '피트니스', 'dumbbell', now()),
       ('1', '침대', 'bad', now()),
       ('1', '미니바', 'glassEmpty', now()),
       ('1', '와이파이', 'wifi', now()),
       ('1', '수영장', 'waterLadder', now()),
       ('2', '침대', 'bed', now()),
       ('2', '와이파이', 'wifi', now()),
       ('2', '피트니스', 'dumbbell', now()),
       ('3', '침대', 'bed', now()),
       ('3', '와이파이', 'wifi', now());

-- room 더미 생성
insert into room_tb(stay_id, name, tier, room_number, price, special_price, special_state, created_at)
values ('1', '스위트룸', 'Deluxe', 'A101', 150000, 130000, 'APPLIED', now()),
       ('1', '스탠다드룸', 'Standard', 'A102', 100000, 90000, 'APPLIED', now()),
       ('2', '더블룸', 'Deluxe', '101', 120000, 100000, 'APPLIED', now()),
       ('2', '싱글룸', 'Standard', '102', 80000, 70000, 'NOT_APPLIED', now()),
       ('3', '프리미엄룸', 'Premium', '샛별동', 200000, 180000, 'APPLIED', now()),
       ('3', '패밀리룸', 'Family', '햇빛동', 180000, 150000, 'APPLIED', now());

-- room_information 더미 생성
insert into room_information_tb(room_id, check_in, check_out, min_person, max_person, more_info, created_at)
values ('1', '15:00:00', '11:00:00', 2, 3, '조식 제공', now()),
       ('2', '15:00:00', '11:00:00', 2, 3, '조식 제공', now()),
       ('3', '15:00:00', '13:00:00', 2, 3, '조식 제공', now()),
       ('4', '16:00:00', '13:00:00', 1, 2, '조식 제공', now()),
       ('5', '14:00:00', '12:00:00', 2, 4, '조식, 중식 제공', now()),
       ('6', '14:00:00', '12:00:00', 2, 6, '조식 제공, 애견 동반 가능', now());

-- scrap 더미 생성
insert into scrap_tb(user_id, stay_id, created_at)
values ('1', '1', now()),
       ('1', '2', now()),
       ('2', '2', now()),
       ('2', '3', now()),
       ('3', '3', now());

-- review 더미 생성
insert into review_tb(user_id, room_id, score, content, delete, created_at)
values (1, 1, 5, '정말 좋았어요!', 'FLAWLESS', now()),
       (1, 2, 4, '괜찮은 편이에요.', 'FLAWLESS', now()),
       (2, 3, 3, '보통이에요.', 'FLAWLESS', now()),
       (2, 4, 5, '다시 오고 싶어요!', 'FLAWLESS', now()),
       (3, 5, 4, '좋았습니다!', 'FLAWLESS', now());

-- review_comment 더미 생성
insert into review_comment_tb (review_id, company_id, content, is_delete, created_at)
values (1, 1, '감사합니다!', 'FLAWLESS', now()),
       (2, 1, '조금 더 노력하겠습니다!', 'FLAWLESS', now()),
       (3, 2, '보통이면 최고가 되도록 노력하겠습니다!', 'FLAWLESS', now()),
       (4, 2, '감사합니다 최선을 다하겠습니다!', 'FLAWLESS', now()),
       (5, 3, '고마워요!', 'FLAWLESS', now());