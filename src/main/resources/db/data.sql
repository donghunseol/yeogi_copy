-- user 더미 생성
insert into user_tb(email, password, name, phone, state, birth, created_at, report_count)
values
    ('ssar@nate.com', '1234', '김쌀', '01012341234', 'ACTIVE', '2000-01-01', now(), 0),
    ('cos@nate.com', '1234', '이코스', '01012345678', 'ACTIVE', '2003-03-03', now(), 0),
    ('love@nate.com', '1234', '박러브', '01087654321', 'ACTIVE', '1999-05-05', now(), 0);

-- company 더미 생성
insert into company_tb(email, password, business_name, business_number, business_address, phone, name, state, created_at, report_count)
values
    ('com1@nate.com', '1234', '김숙박회사', '108-32-34324', '부산시 해운대구', '01011112222', '김회사', 'ACTIVE', now(), 0),
    ('com2@nate.com', '1234', '이숙소회사', '432-51-44324', '경상남도 창원시', '01022223333', '이회사', 'ACTIVE', now(), 0),
    ('com3@nate.com', '1234', '박스테이회사', '723-45-23123', '서울특별시', '01077776666', '박회사', 'ACTIVE', now(), 0);

-- admin 더미 생성
insert into admin_tb(name, password, created_at)
values
    ('admin1', '1234', now()),
    ('admin2', '1234', now()),
    ('admin3', '1234', now());

-- stay 더미 생성
insert into stay_tb(company_id, name, category, address, intro, information, created_at)
values
    ('1', '호텔 블루 하버', '호텔', '부산시 해운대구 좌동순환로 99 (좌동)', '해운대 최고의 오션뷰를 자랑하는 숙소!', '객실 내 취사 금지', now()),
    ('2', '호텔 오아시스', '호텔', '경상남도 창원시 원이대로 998 (상남동)', '창원시 최고의 반쪽 숙소!', '객실 내 취사 금지', now()),
    ('3', '호텔 미라클랜드', '호텔', '서울시 중구 장충동2가 300', '서울시 중구의 중구파 숙소!', '객실 내 취사 금지', now());

-- option 더미 생성
insert into option_tb(stay_id, name, icon_name, created_at)
values
    ('1', '피트니스', 'dumbbell', now()),
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
values
    ('1', '스위트룸', 'Deluxe', 'A101', 150000, 130000, 'APPLIED', now()),
    ('1', '스탠다드룸', 'Standard', 'A102', 100000, 90000, 'APPLIED', now()),
    ('2', '더블룸', 'Deluxe', '101', 120000, 100000, 'APPLIED', now()),
    ('2', '싱글룸', 'Standard', '102', 80000, 70000, 'NOT_APPLIED', now()),
    ('3', '프리미엄룸', 'Premium', '샛별동', 200000, 180000, 'APPLIED', now()),
    ('3', '패밀리룸', 'Family', '햇빛동', 180000, 150000, 'APPLIED', now());

-- room_information 더미 생성
insert into room_information_tb(room_id, check_in, check_out, min_person, max_person, more_info, created_at)
values
    ('1', '15:00:00', '11:00:00', 2, 3, '조식 제공', now()),
    ('2', '15:00:00', '11:00:00', 2, 3, '조식 제공', now()),
    ('3', '15:00:00', '13:00:00', 2, 3, '조식 제공', now()),
    ('4', '16:00:00', '13:00:00', 1, 2, '조식 제공', now()),
    ('5', '14:00:00', '12:00:00', 2, 4, '조식, 중식 제공', now()),
    ('6', '14:00:00', '12:00:00', 2, 6, '조식 제공, 애견 동반 가능', now());

-- admin_tb 더미 생성
insert into admin_tb(name,password,created_at)
values ('admin1@naver.com','1234',now()),
       ('admin2@daum.com','1234',now()),
       ('admin3@gmail.com','1234',now()),
       ('admin4@naver.com','1234',now()),
       ('admin5@daum.com','1234',now());

--admin_tb 더미생성
insert into faq_tb(admin_id,content,reply,created_at)
values (1,'예약을 취소하고 싶어요','예약취소는  앱/웹 > 내정보 > 예약/구매내역에서 직접 가능합니다.',now()),
       (1,'천재지변/감염병으로 인한 예약 최소는 어떻게 하나요?','천재지변(기상악화), 법정 감염병 등 불가항력적인 사유로 제휴점 이용이 불가할 경우 고객행복센터로 예약내역 및 증빙서류(결항확인서, e-티켓, 진단확인서 등)를 보내주시면 확인 후 예약취소 가능 여부를 확인해 드립니다.',now()),
       (2,'현금영수증 발급받고 싶어요','결제 단계에서 현금영수증을 신청하면 자동으로 발행되지만,
신청을 누락했거나 발행받지 못한 경우라면 영수증 확인 후 국세청에서 자진발급분을 등록해 주시길 바랍니다.',now()),
       (2,'영수증/거래내역서 발급받고 싶어요','예약 정보와 결제 정보가 기재되어 있는 영수증 또는 거래내역서는 아래의 경로를 통하여 발급받으실 수 있습니다.',now()),
       (4,'상품을 결제했는데 이용 횟수가 올라가지않아요','실 결제금액 5만원 이상의 상품을 구매하고 이용 완료까지 하셔야 이용 횟수가 올라가요.더불어, 이용 횟수 반영까지 최대 3일 정도 소요될 수 있어요. ',now());