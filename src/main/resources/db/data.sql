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

-- admin_tb 더미 생성
insert into admin_tb(name,password,created_at)
values ('admin1@naver.com','1234',now()),
       ('admin2@daum.com','1234',now()),
       ('admin3@gmail.com','1234',now()),
       ('admin4@naver.com','1234',now()),
       ('admin5@daum.com','1234',now());

--faq_tb 더미생성
insert into faq_tb(admin_id,content,reply,created_at)
values (1,'예약을 취소하고 싶어요','예약취소는  앱/웹 > 내정보 > 예약/구매내역에서 직접 가능합니다.',now()),
       (1,'천재지변/감염병으로 인한 예약 최소는 어떻게 하나요?','천재지변(기상악화), 법정 감염병 등 불가항력적인 사유로 제휴점 이용이 불가할 경우 고객행복센터로 예약내역 및 증빙서류(결항확인서, e-티켓, 진단확인서 등)를 보내주시면 확인 후 예약취소 가능 여부를 확인해 드립니다.',now()),
       (2,'현금영수증 발급받고 싶어요','결제 단계에서 현금영수증을 신청하면 자동으로 발행되지만,
신청을 누락했거나 발행받지 못한 경우라면 영수증 확인 후 국세청에서 자진발급분을 등록해 주시길 바랍니다.',now()),
       (2,'영수증/거래내역서 발급받고 싶어요','예약 정보와 결제 정보가 기재되어 있는 영수증 또는 거래내역서는 아래의 경로를 통하여 발급받으실 수 있습니다.',now()),
       (4,'상품을 결제했는데 이용 횟수가 올라가지않아요','실 결제금액 5만원 이상의 상품을 구매하고 이용 완료까지 하셔야 이용 횟수가 올라가요.더불어, 이용 횟수 반영까지 최대 3일 정도 소요될 수 있어요. ',now());

--event_tb 더미생성
insert into event_tb(admin_id,name,start_date,end_date,created_at,state)
values (1,'국내숙소 쿠폰팩','2024-04-04','2024-04-15',now(),'Disable'),
       (2,'위클리 오픈런','2024-05-04','2024-06-15',now(),'Disable'),
       (2,'베스트 호텔딜','2024-02-04','2024-04-15',now(),'Disable'),
       (4,'인기 호텔 최대 5만원할인','2024-04-04','2024-04-20',now(),'Enable'),
       (4,'피크닉여행 블랙어때','2024-03-15','2024-04-27',now(),'Enable');

--question_tb 더미생성
insert into question_tb(user_id,title,content,answer,created_at)
values (1,'취소 수수료에 대한 질문','안녕하세요, 숙소 예약 후에 긴급 상황이 발생하여 예약을 취소해야 할 경우에 대비해서 취소 수수료에 대해 알고 싶습니다','취소 수수료는 예약한 숙소나 예약 플랫폼에 따라 다를 수 있습니다. 보통 예약 취소는 체크인 날짜 이전에 이루어져야 하며, 취소 수수료는 예약 조건에 따라 달라집니다. 자세한 정보는 숙소 예약 시스템을 통해 확인해 주시기 바랍니다.',now()),
       (2,'예약 변경에 대한 질문','예약을 변경하는 경우에 추가 비용이 발생하는지 궁금합니다.','예약 변경에 따른 추가 비용은 예약한 숙소나 예약 플랫폼에 따라 다를 수 있습니다. 일부 숙소는 변경에 따른 추가 비용이 발생할 수 있지만, 다른 숙소는 변경에 따른 비용 없이 변경을 허용할 수도 있습니다. 예약 변경 시 발생할 수 있는 비용에 대해 자세한 내용은 예약한 숙소나 예약 플랫폼에서 확인해 주세요.',now()),
       (2,'숙소 시설 추가 정보에 대한 질문','예약 후에 숙소 시설에 대한 추가 정보를 얻을 수 있는 방법이 있나요?','예약 후에 숙소 시설에 대한 추가 정보를 얻으려면 숙소 예약 시스템을 통해 숙소 관리자나 호스트에게 직접 문의하거나, 숙소의 웹사이트 또는 예약 플랫폼의 숙소 페이지에서 제공하는 상세 정보를 확인할 수 있습니다. 또한 숙소 예약 애플리케이션에서 고객 서비스에 문의하여 필요한 정보를 얻을 수도 있습니다.',now()),
       (3,'애완동물 동반 여부에 대한 질문','숙소 예약 시 애완동물을 동반할 수 있는지 알고 싶습니다.','숙소에 연락 후 당일예약취소 및 시간이 가능한지 물어보시고 진행하시면 됩니다.',now()),
       (3,'예약취소를 하고싶은데 어떻게해요?','제가 실수로 예약을 잘못해서 그런데 당일 취소를 하고싶은데 예약취소가 안돼요','숙소 예약 시 애완동물 동반 가능 여부는 숙소마다 다를 수 있습니다. 일부 숙소는 애완동물을 환영하고 있지만, 다른 숙소는 애완동물을 허용하지 않을 수도 있습니다. 애완동물 동반 가능 여부는 숙소 예약 시스템이나 숙소의 웹사이트에서 확인할 수 있습니다. 또한 예약 전에 숙소 관리자나 호스트에게 직접 문의하여 애완동물 동반 여부를 확인할 수도 있습니다.',now());

insert into question_tb(company_id,title,content,answer,created_at)
values (1,'온라인 예약 시스템에 관한 문의','안녕하세요, 서울 호텔의 예약 시스템을 관리하는 데 어떤 도구를 사용하고 계신가요? 예약 관리 및 예약 상태를 관리하는 데 있어서 가장 효율적인 방법이 무엇인지 궁금합니다.','예약 시스템은 현재 예약을 관리하기 위해 자체적인 온라인 예약 플랫폼을 사용하고 있습니다. 이 플랫폼은 예약 관리, 예약 상태 업데이트 및 객실 가용성 확인을 위한 간편한 인터페이스를 제공합니다. 또한 고객이 예약을 쉽게 할 수 있도록 웹사이트와 애플리케이션을 지속적으로 개선하고 있습니다.',now()),
       (1,'온라인 마케팅 전략에 대한 문의','온라인 마케팅 전략에 대해 알고 싶습니다. 예약 유도 및 숙박 시설 홍보를 위해 사용하는 주요 디지털 마케팅 채널이 무엇이며, 효과적인 마케팅 전략에 대해 조언을 받고 싶습니다.',' 주요 마케팅 채널로는 구글 광고, 페이스북 및 인스타그램 광고, 그리고 여행 예약 플랫폼을 활용하고 있습니다. 이 외에도 이메일 마케팅, 블로그 글 작성, 소셜 미디어 활동 등을 통해 숙박 시설을 홍보하고 있습니다. 효과적인 마케팅을 위해 고객 세그먼트화, 타겟팅 광고, 그리고 정기적인 마케팅 캠페인 분석을 통해 마케팅 전략을 지속적으로 개선하고 있습니다.',now()),
       (1,'고객 서비스 향상을 위한 제안','안녕하세요, 부산 리조트의 고객 서비스를 개선하기 위한 제안이 있습니다. 고객이 예약을 하거나 숙박 중에 발생하는 문제를 신속하게 처리할 수 있는 방법이 있는지 궁금합니다. 또한 고객 만족도를 높이기 위한 추가적인 서비스나 혜택을 제공할 계획이 있으신지도 궁금합니다.','고객이 예약을 하거나 숙박 중에 발생하는 문제를 신속하게 처리하기 위해 24시간 고객 서비스 팀을 운영하고 있으며, 고객의 문의나 요청에 빠르게 응대하고 있습니다. 또한 고객 만족도를 높이기 위해 추가적인 서비스와 혜택을 제공하고 있으며, 예를 들어 정기적인 고객 만족도 조사를 통해 피드백을 수집하고 개선점을 발견하여 서비스를 개선하고 있습니다.',now());
-- reservation 더미 생성
insert into reservation_tb(user_id, room_id, check_in_date, check_out_date, created_at)
values
    -- 5월 24일 이전 예약
    ('1', '1', '2023-12-31', '2024-01-01', now()),
    ('1', '1', '2024-01-01', '2024-01-02', now()),
    ('1', '2', '2024-02-01', '2024-02-02', now()),
    ('2', '3', '2024-02-03', '2024-02-05', now()),
    ('2', '4', '2024-02-29', '2024-03-02', now()),
    ('3', '5', '2024-03-17', '2024-03-21', now()),
    ('3', '6', '2024-04-23', '2025-04-24', now()),
    -- 5월 24일 이후 예약
    ('1', '1', '2024-05-24', '2024-05-25', now()),
    ('1', '2', '2024-05-25', '2024-05-30', now()),
    ('2', '3', '2024-05-31', '2024-06-02', now()),
    ('2', '4', '2024-06-10', '2024-06-11', now()),
    ('3', '5', '2024-06-20', '2024-05-25', now()),
    ('3', '6', '2024-06-23', '2025-06-27', now());


-- scrap 더미 생성
insert into scrap_tb(user_id, stay_id, created_at)
values ('1', '1', now()),
       ('1', '2', now()),
       ('2', '2', now()),
       ('2', '3', now()),
       ('3', '3', now());

-- review 더미 생성
insert into review_tb(user_id, room_id, score, content, is_delete, created_at)
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

-- report 더미 생성
-- user 가 user review 신고
insert into report_tb(review_id, user_id, result, created_at)
values ('1', '2', 'PROCEEDING', now()),
       ('2', '3', 'PROCEEDING', now()),
       ('3', '1', 'PROCEEDING', now());
-- company 가 user review 신고
insert into report_tb(review_id, company_id, result, created_at)
values ('1', '1', 'PROCEEDING', now()),
       ('2', '2', 'PROCEEDING', now()),
       ('3', '3', 'PROCEEDING', now());
-- user 가 company review 신고
insert into report_tb(review_comment_id, user_id, result, created_at)
values ('1', '1', 'PROCEEDING', now()),
       ('2', '2', 'PROCEEDING', now()),
       ('3', '3', 'PROCEEDING', now());
-- company 가 company review 신고
insert into report_tb(review_comment_id, company_id, result, created_at)
values ('1', '2', 'PROCEEDING', now()),
       ('2', '3', 'PROCEEDING', now()),
       ('3', '1', 'PROCEEDING', now());