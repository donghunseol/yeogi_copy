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
       ('com3@nate.com', '1234', '박스테이회사', '723-45-23123', '서울특별시', '01077776666', '박회사', 'ACTIVE', now(), 0),
       ('com4@nate.com', '1234', '해외숙소A회사', '723-46-23123', '대만', '01077776666', 'A회사', 'ACTIVE', now(), 0),
       ('com5@nate.com', '1234', '해외숙소B회사', '729-47-23593', '베트남', '01077776666', 'B회사', 'ACTIVE', now(), 0),
       ('com6@nate.com', '1234', '해외숙소C회사', '793-48-93123', '말레이시아', '01077776666', 'C회사', 'ACTIVE', now(), 0);
-- admin 더미 생성
insert into admin_tb(name, password, created_at)
values ('admin1', '1234', now()),
       ('admin2', '1234', now()),
       ('admin3', '1234', now());

-- stay 더미 생성
insert into stay_tb(company_id, name, category, address, intro, information, created_at, state)
values (1, '호텔 블루 하버', '호텔', '부산시 해운대구 좌동순환로 99 (좌동)', '해운대 최고의 오션뷰를 자랑하는 숙소!', '객실 내 취사 금지', now(), 'TRUE'),
       (2, '호텔 오아시스', '호텔', '경상남도 창원시 원이대로 998 (상남동)', '창원시 최고의 반쪽 숙소!', '객실 내 취사 금지', now(), 'TRUE'),
       (3, '호텔 미라클랜드', '호텔', '서울시 중구 장충동2가 300', '서울시 중구의 중구파 숙소!', '객실 내 취사 금지', now(), 'TRUE'),
       (4, '프리미어 캐빈-오사카', '해외', '오사카 신사이바시', '텐진역에서 5분거리에 위치한 숙소', '객실 내 취사 금지', now(), 'TRUE'),
       (5, '오리엔탈 익스프레스', '해외', '278 Vo Nguyen Giap Street, My An Ward, Ngu Hanh Son District, Da Nang', '모든여행지가 가까운 숙소',
        '객실 내 취사 금지', now(), 'TRUE'),
       (6, '소테츠 그랜드 프레사', '해외', '36 - 38 Lam Hoanh, 프억 미, 다낭, 베트남, 550000', '신상숙소 내.외간 청결', '객실 내 취사 금지', now(),
        'TRUE'),
       (4, '난바 오리엔탈', '해외', '3-4-24 Watanabedouri, Chuo-ku, 텐진, 후쿠오카 ', '여행지의 모든 것들을 가까이서 즐기는 숙소', '객실 내 취사 금지', now(),
        'TRUE'),
       (5, '캠핑 체험 랜드', '캠핑', '강원도 평창군 봉평면 대정로 253', '자연 속에서 즐기는 힐링 캠핑', '야외 캠핑 체험 프로그램 제공', NOW(), 'TRUE'),
       (4, '산속 캠핑존', '캠핑', '전라남도 순천시 구룡포길 80-14', '산 속 조용한 캠핑 장소', '산림욕과 등산로 제공', NOW(), 'TRUE'),
       (2, '숲속 캠핑 풀빌라', '캠핑', '충청북도 제천시 양평면 목재로 148', '시원한 숲 속 풀빌라', '피크닉과 바베큐 시설 제공', NOW(), 'TRUE'),
       (1, '강가 캠핑장', '캠핑', '경기도 양평군 강상면 행목길 29-21', '강가에 위치한 편안한 캠핑장', '낚시 및 수상 스포츠 활동 제공', NOW(), 'TRUE'),
       (6, '별빛 캠핑장', '캠핑', '경기도 가평군 설악면 금강로 1266', '별빛 아래 펼쳐지는 캠핑장', '야외 영화 상영 및 관광 버스 투어 제공', NOW(), 'TRUE'),
       (2, '모텔 더 포레스트', '모텔', '서울특별시 강남구 도곡로 156', '강남의 편안한 모텔', '24시간 룸 서비스 제공', NOW(), 'TRUE'),
       (5, '시티 모텔', '모텔', '서울특별시 마포구 와우산로 71', '마포의 시티 뷰를 감상하는 모텔', '근처 관광명소 안내 서비스 제공', NOW(), 'TRUE'),
       (4, '블랙모텔', '모텔', '대전광역시 중구 중앙로 123', '도심에서 조용한 휴식을 취하는 모텔', '실내 레스토랑 및 라운지 제공', NOW(), 'TRUE'),
       (3, '모텔 신세계', '모텔', '경기도 수원시 팔달구 권광로 190', '수원의 중심에서 편안한 휴식을 취하는 모텔', '바디 마사지 서비스 제공', NOW(), 'TRUE'),
       (1, '카페모텔', '모텔', '경상북도 경주시 보문로 376', '경주의 아늑한 모텔과 카페를 즐기세요', '무료 와이파이 및 모닝 콜 서비스 제공', NOW(), 'TRUE'),
       (5, '별빛 펜션', '펜션', '강원도 강릉시 경강로 3104', '강릉의 아름다운 풍경을 감상하는 펜션', '바베큐 파티 및 산책로 제공', NOW(), 'TRUE'),
       (6, '호수 펜션', '펜션', '전라남도 순천시 화양면 호수로 55', '호수에 인접한 조용한 펜션', '수영장 및 낚시 서비스 제공', NOW(), 'TRUE'),
       (2, '바다 푸른 펜션', '펜션', '경상남도 통영시 죽림면 세마해안로 176-8', '통영의 바다를 품은 펜션', '바다 수영 및 스쿠버 다이빙 체험 제공', NOW(), 'TRUE'),
       (2, '하늘과 바람과 별과 펜션', '펜션', '제주특별자치도 서귀포시 안덕면 상창로 25', '제주의 맑은 하늘 아래서 힐링하는 펜션', '자전거 대여 및 야외 수영장 제공', NOW(),
        'TRUE'),
       (3, '산속 푸른 펜션', '펜션', '충청북도 충주시 산척면 매곡로 66', '산 속에서 바라보는 푸른 펜션', '등산로 및 자연체험 프로그램 제공', NOW(), 'TRUE'),
       (1, '럭셔리 빌라', '홈&빌라', '서울특별시 용산구 우사단로 54', '용산의 프라이빗한 럭셔리 빌라', '헬스장 및 사우나 시설 제공', NOW(), 'TRUE'),
       (1, '골목길 빌라', '홈&빌라', '서울특별시 마포구 와우산로 147', '마포의 아늑한 골목길 빌라', '카페 및 작은 공연장 제공', NOW(), 'TRUE'),
       (3, '연못빌라', '홈&빌라', '경기도 가평군 청평면 청평로 311', '연못을 따라 펼쳐지는 힐링 빌라', '낚시 및 보트 서비스 제공', NOW(), 'TRUE'),
       (5, '수영장 빌라', '홈&빌라', '강원도 춘천시 국원로 228', '시원한 수영장이 있는 편안한 빌라', '수영 및 바베큐 파티 서비스 제공', NOW(), 'TRUE'),
       (6, '산속 빌라', '홈&빌라', '경상북도 안동시 하회로 1길 7', '자연 속에서 즐기는 휴식을 위한 빌라', '산책로 및 템플스테이 프로그램 제공', NOW(), 'TRUE'),
       (1, '친구랑 편한 게스트하우스', '게스트하우스', '서울특별시 마포구 와우산로 109', '마포에서 편안한 하룻밤을 보내세요', '공용 주방 및 라운지 제공', NOW(), 'TRUE'),
       (2, '꿈나무 게스트하우스', '게스트하우스', '서울특별시 서대문구 연희로 4길 22', '연희동의 아늑한 게스트하우스', '투어 및 가이드 서비스 제공', NOW(), 'TRUE'),
       (5, '무지개 게스트하우스', '게스트하우스', '경기도 수원시 팔달구 경수대로 1035', '수원의 다양한 문화를 경험하세요', '자전거 대여 및 문화 이벤트 안내 제공', NOW(),
        'TRUE'),
       (3, '쉐어하우스', '게스트하우스', '경상남도 창원시 성산구 대방로 89', '창원에서 즐거운 쉐어하우스 경험', '공용 욕실 및 라운지 제공', NOW(), 'TRUE'),
       (4, '시골풍경 게스트하우스', '게스트하우스', '전라남도 담양군 금성면 비로산길 59-49', '담양의 시골 풍경을 즐기는 게스트하우스', '산책로 및 체험 프로그램 제공', NOW(),
        'TRUE'),
       (3, '자연 속 캠핑장', '캠핑', '강원도 인제군 북면 평대리 109-7', '자연 속에서 휴식을 취하는 캠핑장', '숲속 캠프파이어 및 가족놀이터 제공', NOW(), 'TRUE'),
       (5, '호수 캠핑리조트', '캠핑', '전라북도 정읍시 북면 태조로 472-20', '호수를 바라보며 즐기는 캠핑 리조트', '워터스포츠 및 낚시체험 제공', NOW(), 'TRUE'),
       (6, '별빛 텐트 캠핑장', '캠핑', '경기도 포천시 일동면 광릉수목원로 188', '별 아래서 자연을 느끼는 캠핑장', '텐트 대여 및 캠프파이어 서비스 제공', NOW(), 'TRUE'),
       (1, '강변 캠핑존', '캠핑', '경기도 양평군 강하면 양평로 434', '강변에서 느끼는 특별한 캠핑 경험', '바베큐 파티 및 수상 스포츠 제공', NOW(), 'TRUE'),
       (2, '여름별캠핑장', '캠핑', '충청남도 예산군 대술면 여름별로 123', '여름에 더욱 빛나는 캠핑장', '바닷가 풍경 및 해수욕 제공', NOW(), 'TRUE'),
       (3, '신라 모텔', '모텔', '서울특별시 서초구 서초대로 412', '서초에서 편안한 하룻밤을 보내세요', '모던한 디자인 및 무료 와이파이 제공', NOW(), 'TRUE'),
       (4, '반짝모텔', '모텔', '부산광역시 해운대구 해운대로 320', '해운대의 반짝이는 모텔', '해변 근처 위치 및 룸 서비스 제공', NOW(), 'TRUE'),
       (2, '쉬고모텔', '모텔', '대구광역시 수성구 무학로 107', '수성구의 편안한 쉼터', '친절한 직원 및 조식 서비스 제공', NOW(), 'TRUE'),
       (6, '행복한모텔', '모텔', '대전광역시 서구 관저동로 87', '대전에서 행복한 하룻밤을 보내세요', '실내 미니바 및 에어컨 제공', NOW(), 'TRUE'),
       (2, '청춘모텔', '모텔', '광주광역시 동구 충장로 3', '동구에서 젊음을 느끼는 모텔', '주변 관광명소 안내 및 무료 주차장 제공', NOW(), 'TRUE'),
       (6, '느린마을 펜션', '펜션', '강원도 화천군 사내면 영춘로 8-24', '자연 속에서 느린 휴식을 취하는 펜션', '공용 바베큐 시설 및 피크닉 액티비티 제공', NOW(), 'TRUE'),
       (6, '푸른 호반의 펜션', '펜션', '전라북도 김제시 요촌면 김제영월로 139-12', '김제의 아름다운 호반을 감상하는 펜션', '수영장 및 자전거 대여 서비스 제공', NOW(),
        'TRUE'),
       (5, '수원의 펜션', '펜션', '경기도 수원시 영통구 영통로 97', '수원의 편안한 휴식을 제공하는 펜션', '실내 미니바 및 무료 주차장 제공', NOW(), 'TRUE'),
       (2, '산속 힐링 펜션', '펜션', '경기도 양평군 서종면 하산골길 137', '산 속에서 힐링하는 펜션', '산책로 및 자전거 대여 서비스 제공', NOW(), 'TRUE'),
       (3, '호수의 휴식 펜션', '펜션', '충청남도 보령시 미산면 청소로 36', '보령의 아름다운 호수를 바라보는 휴식 펜션', '바베큐 파티 및 낚시체험 제공', NOW(), 'TRUE'),
       (2, '여름의 빌라', '홈&빌라', '제주특별자치도 서귀포시 성산읍 시흥하동로 409', '성산일출봉 근처에서 여름을 보내는 빌라', '바다 전망 및 테라스 디자인 제공', NOW(),
        'TRUE'),
       (6, '평온한 빌라', '홈&빌라', '경기도 용인시 처인구 이동면 양지로 301', '양지의 평온한 휴식을 즐기는 빌라', '실내 난방 및 바베큐 시설 제공', NOW(), 'TRUE'),
       (1, '가을의 빌라', '홈&빌라', '강원도 춘천시 서면 서동천로 32', '서면의 가을을 느끼는 휴식의 빌라', '난방 시설 및 주변 산책로 제공', NOW(), 'TRUE'),
       (1, '행복한 홈스테이', '홈&빌라', '전라남도 완도군 완도읍 완도로 100', '완도의 행복을 느끼는 홈스테이', '가정적인 분위기와 가족식 밥상 제공', NOW(), 'TRUE'),
       (3, '바다 뷰 빌라', '홈&빌라', '부산광역시 해운대구 우동 우동해안로 298', '해운대의 멋진 바다를 바라보는 빌라', '넓은 테라스 및 해수욕장 접근성 제공', NOW(), 'TRUE'),
       (1, '디자인 게스트하우스', '게스트하우스', '서울특별시 강남구 역삼로 123', '강남의 아름다운 디자인을 즐기는 게스트하우스', '주변 쇼핑 명소 안내 및 공항 셔틀 제공', NOW(),
        'TRUE'),
       (2, '하버 게스트하우스', '게스트하우스', '인천광역시 중구 해안남로 34', '인천 항구를 바라보는 게스트하우스', '공용 주방 및 바베큐 편의 시설 제공', NOW(), 'TRUE'),
       (3, '여유로운 게스트하우스', '게스트하우스', '대구광역시 중구 동성로 38', '중구에서 여유로운 휴식을 취하는 게스트하우스', '마사지 및 피트니스 센터 이용 가능', NOW(),
        'TRUE'),
       (6, '편안한 게스트하우스', '게스트하우스', '광주광역시 서구 서문대로 817', '서구에서 편안한 하룻밤을 보내는 게스트하우스', '주변 관광명소 안내 및 무료 주차장 제공', NOW(),
        'TRUE'),
       (5, '행복한 게스트하우스', '게스트하우스', '경기도 수원시 장안구 경수대로 924', '장안구에서 행복한 하룻밤을 보내는 게스트하우스', '조식 서비스 및 자전거 대여 제공', NOW(),
        'TRUE');

-- option 더미 생성
insert into option_tb(stay_id, name, icon_name, created_at)
values (1, '피트니스', 'dumbbell', now()),
       (1, '침대', 'bad', now()),
       (1, '미니바', 'glassEmpty', now()),
       (1, '와이파이', 'wifi', now()),
       (1, '수영장', 'waterLadder', now()),
       (2, '침대', 'bed', now()),
       (2, '와이파이', 'wifi', now()),
       (2, '피트니스', 'dumbbell', now()),
       (3, '침대', 'bed', now()),
       (3, '와이파이', 'wifi', now()),
       (4, '피트니스', 'dumbbell', now()),
       (4, '침대', 'bed', now()),
       (4, '와이파이', 'wifi', now()),
       (5, '피트니스', 'dumbbell', now()),
       (5, '침대', 'bed', now()),
       (5, '와이파이', 'wifi', now()),
       (6, '피트니스', 'dumbbell', now()),
       (6, '침대', 'bed', now()),
       (6, '와이파이', 'wifi', now());


-- room 더미 생성
insert into room_tb(stay_id, name, tier, room_number, price, special_price, special_state, image_name, image_path,
                    created_at)
values (1, '스위트룸', 'Deluxe', 'A101', 150000, 130000, 'APPLIED', 'room1.jpg', '/images/room1.jpg', now()),
       (1, '스탠다드룸', 'Standard', 'A102', 100000, 90000, 'APPLIED', 'room1.jpg', '/images/room1.jpg', now()),
       (2, '더블룸', 'Deluxe', '101', 120000, 100000, 'APPLIED', 'room1.jpg', '/images/room1.jpg', now()),
       (2, '싱글룸', 'Standard', '102', 80000, 70000, 'NOT_APPLIED', 'room1.jpg', '/images/room1.jpg', now()),
       (3, '프리미엄룸', 'Premium', '샛별동', 200000, 180000, 'APPLIED', 'room1.jpg', '/images/room1.jpg', now()),
       (3, '패밀리룸', 'Family', '햇빛동', 180000, 150000, 'APPLIED', 'room1.jpg', '/images/room1.jpg', now()),
       (4, '프리미엄룸', 'Premium', '샛별동', 200000, 180000, 'APPLIED', 'room1.jpg', '/images/room1.jpg', now()),
       (5, '패밀리룸', 'Family', '햇빛동', 180000, 150000, 'APPLIED', 'room1.jpg', '/images/room1.jpg', now()),
       (5, '프리미엄룸', 'Premium', '샛별동', 200000, 180000, 'APPLIED', 'room1.jpg', '/images/room1.jpg', now()),
       (6, '패밀리룸', 'Family', '햇빛동', 180000, 150000, 'APPLIED', 'room1.jpg', '/images/room1.jpg', now());

-- room_information 더미 생성
insert into room_information_tb(room_id, check_in, check_out, min_person, max_person, more_info, created_at)
values (1, '15:00:00', '11:00:00', 2, 3, '조식 제공', now()),
       (2, '15:00:00', '11:00:00', 2, 3, '조식 제공', now()),
       (3, '15:00:00', '13:00:00', 2, 3, '조식 제공', now()),
       (4, '16:00:00', '13:00:00', 1, 2, '조식 제공', now()),
       (5, '14:00:00', '12:00:00', 2, 4, '조식, 중식 제공', now()),
       (6, '14:00:00', '12:00:00', 2, 10, '조식 제공, 애견 동반 가능', now());

-- admin_tb 더미 생성
insert into admin_tb(name, password, created_at)
values ('admin1@naver.com', '1234', now()),
       ('admin2@daum.com', '1234', now()),
       ('admin3@gmail.com', '1234', now()),
       ('admin4@naver.com', '1234', now()),
       ('admin5@daum.com', '1234', now());

--faq_tb 더미생성
insert into faq_tb(admin_id, content, reply, created_at)
values (1, '예약을 취소하고 싶어요', '예약취소는  앱/웹 > 내정보 > 예약/구매내역에서 직접 가능합니다.', now()),
       (1, '천재지변/감염병으로 인한 예약 최소는 어떻게 하나요?',
        '천재지변(기상악화), 법정 감염병 등 불가항력적인 사유로 제휴점 이용이 불가할 경우 고객행복센터로 예약내역 및 증빙서류(결항확인서, e-티켓, 진단확인서 등)를 보내주시면 확인 후 예약취소 가능 여부를 확인해 드립니다.',
        now()),
       (2, '현금영수증 발급받고 싶어요', '결제 단계에서 현금영수증을 신청하면 자동으로 발행되지만,
신청을 누락했거나 발행받지 못한 경우라면 영수증 확인 후 국세청에서 자진발급분을 등록해 주시길 바랍니다.', now()),
       (2, '영수증/거래내역서 발급받고 싶어요', '예약 정보와 결제 정보가 기재되어 있는 영수증 또는 거래내역서는 아래의 경로를 통하여 발급받으실 수 있습니다.', now()),
       (4, '상품을 결제했는데 이용 횟수가 올라가지않아요',
        '실 결제금액 5만원 이상의 상품을 구매하고 이용 완료까지 하셔야 이용 횟수가 올라가요.더불어, 이용 횟수 반영까지 최대 3일 정도 소요될 수 있어요. ', now());

--event_tb 더미생성
insert into event_tb(admin_id, name, start_date, end_date, created_at, state)
values (1, '국내숙소 쿠폰팩', '2024-04-04', '2024-04-15', now(), 'Disable'),
       (2, '위클리 오픈런', '2024-05-04', '2024-06-15', now(), 'Disable'),
       (2, '베스트 호텔딜', '2024-02-04', '2024-04-15', now(), 'Disable'),
       (4, '인기 호텔 최대 5만원할인', '2024-04-04', '2024-04-20', now(), 'Enable'),
       (4, '피크닉여행 블랙어때', '2024-03-15', '2024-04-27', now(), 'Enable');

--question_tb 더미생성 --답변이 완료된 문의사항(유저)
insert into question_tb(user_id, title, content, answer, state, created_at)
values (1, '취소 수수료에 대한 질문', '안녕하세요, 숙소 예약 후에 긴급 상황이 발생하여 예약을 취소해야 할 경우에 대비해서 취소 수수료에 대해 알고 싶습니다',
        '취소 수수료는 예약한 숙소나 예약 플랫폼에 따라 다를 수 있습니다. 보통 예약 취소는 체크인 날짜 이전에 이루어져야 하며, 취소 수수료는 예약 조건에 따라 달라집니다. 자세한 정보는 숙소 예약 시스템을 통해 확인해 주시기 바랍니다.',
           'COMPLETION',
           now()),
       (2, '예약 변경에 대한 질문', '예약을 변경하는 경우에 추가 비용이 발생하는지 궁금합니다.',
        '예약 변경에 따른 추가 비용은 예약한 숙소나 예약 플랫폼에 따라 다를 수 있습니다. 일부 숙소는 변경에 따른 추가 비용이 발생할 수 있지만, 다른 숙소는 변경에 따른 비용 없이 변경을 허용할 수도 있습니다. 예약 변경 시 발생할 수 있는 비용에 대해 자세한 내용은 예약한 숙소나 예약 플랫폼에서 확인해 주세요.',
        'COMPLETION',
        now()),
       (2, '숙소 시설 추가 정보에 대한 질문', '예약 후에 숙소 시설에 대한 추가 정보를 얻을 수 있는 방법이 있나요?',
        '예약 후에 숙소 시설에 대한 추가 정보를 얻으려면 숙소 예약 시스템을 통해 숙소 관리자나 호스트에게 직접 문의하거나, 숙소의 웹사이트 또는 예약 플랫폼의 숙소 페이지에서 제공하는 상세 정보를 확인할 수 있습니다. 또한 숙소 예약 애플리케이션에서 고객 서비스에 문의하여 필요한 정보를 얻을 수도 있습니다.',
        'COMPLETION',
        now()),
       (3, '애완동물 동반 여부에 대한 질문', '숙소 예약 시 애완동물을 동반할 수 있는지 알고 싶습니다.', '숙소에 연락 후 당일예약취소 및 시간이 가능한지 물어보시고 진행하시면 됩니다.',
        'COMPLETION',
        now());


--question_tb 더미생성 --답변이 완료되지않은 문의사항(유저)
insert into question_tb(user_id, title, content, state, created_at)
values
    (2, '숙소 예약 변경에 대한 질문', '숙소 예약 후에 예약 일자를 변경할 수 있는 방법이 있나요?', 'WAIT', now()),
    (3, '결제 방법에 대한 질문', '결제 시 사용 가능한 카드 종류에 대해 알고 싶습니다.', 'WAIT', now()),
    (3, '환불 절차에 대한 질문', '예약 취소 시 환불 절차에 대해 설명해 주실 수 있나요?', 'WAIT', now());

--question_tb 더미생성 --답변이 완료된 문의사항(회사)
insert into question_tb(company_id, title, content, answer, state,created_at)
values (1, '온라인 예약 시스템에 관한 문의',
        '안녕하세요, 서울 호텔의 예약 시스템을 관리하는 데 어떤 도구를 사용하고 계신가요? 예약 관리 및 예약 상태를 관리하는 데 있어서 가장 효율적인 방법이 무엇인지 궁금합니다.',
        '예약 시스템은 현재 예약을 관리하기 위해 자체적인 온라인 예약 플랫폼을 사용하고 있습니다. 이 플랫폼은 예약 관리, 예약 상태 업데이트 및 객실 가용성 확인을 위한 간편한 인터페이스를 제공합니다. 또한 고객이 예약을 쉽게 할 수 있도록 웹사이트와 애플리케이션을 지속적으로 개선하고 있습니다.',
        'COMPLETION',
        now()),
       (1, '온라인 마케팅 전략에 대한 문의',
        '온라인 마케팅 전략에 대해 알고 싶습니다. 예약 유도 및 숙박 시설 홍보를 위해 사용하는 주요 디지털 마케팅 채널이 무엇이며, 효과적인 마케팅 전략에 대해 조언을 받고 싶습니다.',
        ' 주요 마케팅 채널로는 구글 광고, 페이스북 및 인스타그램 광고, 그리고 여행 예약 플랫폼을 활용하고 있습니다. 이 외에도 이메일 마케팅, 블로그 글 작성, 소셜 미디어 활동 등을 통해 숙박 시설을 홍보하고 있습니다. 효과적인 마케팅을 위해 고객 세그먼트화, 타겟팅 광고, 그리고 정기적인 마케팅 캠페인 분석을 통해 마케팅 전략을 지속적으로 개선하고 있습니다.',
        'COMPLETION',
        now()),
       (1, '고객 서비스 향상을 위한 제안',
        '안녕하세요, 부산 리조트의 고객 서비스를 개선하기 위한 제안이 있습니다. 고객이 예약을 하거나 숙박 중에 발생하는 문제를 신속하게 처리할 수 있는 방법이 있는지 궁금합니다. 또한 고객 만족도를 높이기 위한 추가적인 서비스나 혜택을 제공할 계획이 있으신지도 궁금합니다.',
        '고객이 예약을 하거나 숙박 중에 발생하는 문제를 신속하게 처리하기 위해 24시간 고객 서비스 팀을 운영하고 있으며, 고객의 문의나 요청에 빠르게 응대하고 있습니다. 또한 고객 만족도를 높이기 위해 추가적인 서비스와 혜택을 제공하고 있으며, 예를 들어 정기적인 고객 만족도 조사를 통해 피드백을 수집하고 개선점을 발견하여 서비스를 개선하고 있습니다.',
        'COMPLETION',
        now());

--question_tb 더미생성 --답변이 완료되지않은 문의사항(회사)
insert into question_tb(company_id, title, content, state, created_at)
values
    (2, '숙소 시설에 대한 문의', '숙소 예약 후에 추가로 시설을 요청할 수 있는 방법이 있나요?', 'WAIT', now()),
    (3, '객실 청소 주기에 대한 질문', '객실 청소 주기가 어떻게 되는지 알고 싶습니다.', 'WAIT', now()),
    (3, '결제 오류에 대한 문의', '결제 시 오류가 발생하여 예약이 완료되지 않습니다. 도움을 요청합니다.', 'WAIT', now());


-- reservation 더미 생성
insert into reservation_tb(user_id, room_id, reservation_name, reservation_tel, check_in_date, check_out_date,
                           created_at)
values
    -- 5월 24일 이전 예약
    (1, 1, '홍길동', '01012344321', '2023-12-31', '2024-01-01', now()),
    (1, 1, '홍길동', '01012344321', '2024-01-01', '2024-01-02', now()),
    (1, 2, '홍길동', '01012344321', '2024-02-01', '2024-02-02', now()),
    (2, 3, '임꺽정', '01056788765', '2024-02-03', '2024-02-05', now()),
    (2, 4, '임꺽정', '01056788765', '2024-02-29', '2024-03-02', now()),
    (3, 5, '이순신', '01087654321', '2024-03-17', '2024-03-21', now()),
    (3, 6, '이순신', '01087654321', '2024-04-23', '2025-04-24', now()),
    -- 5월 24일 이후 예약
    (1, 1, '홍길동', '01012344321', '2024-05-24', '2024-05-25', now()),
    (1, 2, '홍길동', '01012344321', '2024-05-25', '2024-05-30', now()),
    (2, 3, '임꺽정', '01056788765', '2024-05-31', '2024-06-02', now()),
    (2, 4, '임꺽정', '01056788765', '2024-06-10', '2024-06-11', now()),
    (3, 5, '이순신', '01087654321', '2024-06-20', '2024-06-25', now()),
    (3, 6, '이순신', '01087654321', '2024-06-23', '2025-06-27', now());


-- pay 더미 생성
insert into pay_tb(reservation_id, amount, way, state, created_at)
values
    -- 5월 24일 이전 결제
    (1, 150000, 'Credit Card', 'COMPLETION', now()),
    (2, 150000, 'Credit Card', 'COMPLETION', now()),
    (3, 100000, 'Kakao Pay', 'COMPLETION', now()),
    (4, 120000, 'Bank Transfer', 'COMPLETION', now()),
    (5, 80000, 'Kakao Pay', 'COMPLETION', now()),
    (6, 200000, 'Naver Pay', 'COMPLETION', now()),
    (7, 180000, 'Naver Pay', 'COMPLETION', now()),

    -- 5월 24일 이후 결제
    -- On Site Payment 는 현장 결제
    (8, 0, 'On Site Payment', 'PROCESSING', now()),
    (9, 0, 'On Site Payment', 'PROCESSING', now()),
    (10, 120000, 'Kakao Pay', 'REFUND', now()),
    (11, 80000, 'Kakao Pay', 'REFUND', now()),
    (12, 200000, 'Kakao Pay', 'COMPLETION', now()),
    (13, 180000, 'Naver Pay', 'COMPLETION', now());


-- scrap 더미 생성
insert into scrap_tb(user_id, stay_id, created_at)
values (1, 1, now()),
       (1, 2, now()),
       (2, 2, now()),
       (2, 3, now()),
       (3, 3, now());

-- review 더미 생성
insert into review_tb(user_id, stay_id, score, content, is_delete, created_at)
values (1, 1, 5, '정말 좋았어요!', 'FLAWLESS', now()),
       (1, 2, 4, '괜찮은 편이에요.', 'FLAWLESS', now()),
       (2, 3, 3, '보통이에요.', 'FLAWLESS', now()),
       (2, 3, 5, '다시 오고 싶어요!', 'FLAWLESS', now()),
       (3, 3, 4, '좋았습니다!', 'FLAWLESS', now());

-- review 대댓글 더미생성
insert into review_tb(user_id, stay_id, content, is_delete, created_at, parent_id)
values (1, 1, '고객님 그런식으로 말씀하시면 안되죠 신고할거에요!!', 'FLAWLESS', now(), 1);

-- review_comment 더미 생성
-- insert into review_comment_tb (review_id, company_id, content, is_delete, created_at)
-- values (1, 1, '감사합니다!', 'FLAWLESS', now()),
--        (2, 1, '조금 더 노력하겠습니다!', 'FLAWLESS', now()),
--        (3, 2, '보통이면 최고가 되도록 노력하겠습니다!', 'FLAWLESS', now()),
--        (4, 2, '감사합니다 최선을 다하겠습니다!', 'FLAWLESS', now()),
--        (5, 3, '고마워요!', 'FLAWLESS', now());

-- report 더미 생성
-- user 가 user review 신고
-- insert into report_tb(review_id, user_id, result, created_at)
-- values (1, 2, 'PROCEEDING', now()),
--        (2, 3, 'PROCEEDING', now()),
--        (3, 1, 'PROCEEDING', now());
-- -- company 가 user review 신고
-- insert into report_tb(review_id, company_id, result, created_at)
-- values (1, 1, 'PROCEEDING', now()),
--        (2, 2, 'PROCEEDING', now()),
--        (3, 3, 'PROCEEDING', now());

-- user 가 stay review 신고
-- insert into report_tb(review_comment_id, user_id, result, created_at)
-- values (1, 1, 'PROCEEDING', now()),
--        (2, 2, 'PROCEEDING', now()),
--        (3, 3, 'PROCEEDING', now());

-- stay 가 user review 신고
-- insert into report_tb(review_comment_id, stay_id, result, created_at)
-- values (1, 2, 'PROCEEDING', now()),
--        (2, 3, 'PROCEEDING', now()),
--        (3, 1, 'PROCEEDING', now());

-- stayImage 더미 생성
insert into stay_image_tb(stay_id, name, path, created_at)
values -- 호텔
       (1, 'hotel1.png', '/images/hotel/hotel1.png', now()),
       (1, 'room1.jpg', '/images/room1.jpg', now()),
       (1, 'room2.jpg', '/images/room2.jpg', now()),
       (2, 'hotel2.png', '/images/hotel/hotel2.png', now()),
       (2, 'room1.jpg', '/images/room1.jpg', now()),
       (2, 'caption.jpg', '/images/caption.jpg', now()),
       (3, 'hotel3.png', '/images/hotel/hotel3.png', now()),
       (3, 'room2.jpg', '/images/room2.jpg', now()),
       -- 해외
       (4, 'overseas1.png', '/images/overseas/overseas1.png', now()),
       (5, 'overseas2.png', '/images/overseas/overseas2.png', now()),
       (6, 'overseas3.png', '/images/overseas/overseas3.png', now()),
       (6, 'overseas4.png', '/images/overseas/overseas4.png', now()),
       (7, 'overseas5.png', '/images/overseas/overseas5.png', now()),
       (7, 'overseas6.png', '/images/overseas/overseas6.png', now()),
       --캠핑
       (8, 'camping1.png', '/images/camping/camping1.png', now()),
       (8, 'camping2.png', '/images/camping/camping2.png', now()),
       (9, 'camping3.png', '/images/camping/camping3.png', now()),
       (9, 'camping4.png', '/images/camping/camping4.png', now()),
       (10, 'camping5.png', '/images/camping/camping5.png', now()),
       (11, 'camping6.png', '/images/camping/camping6.png', now()),
       -- 모텔
       (12, 'motel1.png', '/images/motel/motel1.png', now()),
       (13, 'motel2.png', '/images/motel/motel2.png', now()),
       (14, 'motel3.png', '/images/motel/motel3.png', now()),
       (14, 'motel4.png', '/images/motel/motel4png', now()),
       (15, 'motel5.png', '/images/motel/motel5.png', now()),
       (16, 'motel6.png', '/images/motel/motel6.png', now()),
       -- 펜션
       (17, 'pension1.png', '/images/pension/pension1.png', now()),
       (17, 'pension2.png', '/images/pension/pension2.png', now()),
       (18, 'pension3.png', '/images/pension/pension3.png', now()),
       (18, 'pension5.png', '/images/pension/pension5.png', now()),
       (19, 'pension4.png', '/images/pension/pension4.png', now()),
       (19, 'pension6.png', '/images/pension/pension6.png', now()),
       (20, 'pension5.png', '/images/pension/pension5.png', now()),
       (21, 'pension6.png', '/images/pension/pension6.png', now()),
       -- 홈앤빌라
       (22, 'homeAndVilla1.png', '/images/home_and_villa/homeAndVilla1.png', now()),
       (23, 'homeAndVilla2.png', '/images/home_and_villa/homeAndVilla2.png', now()),
       (24, 'homeAndVilla3.png', '/images/home_and_villa/homeAndVilla3.png', now()),
       (25, 'homeAndVilla4.png', '/images/home_and_villa/homeAndVilla4.png', now()),
       (26, 'homeAndVilla5.png', '/images/home_and_villa/homeAndVilla5.png', now()),
       -- 게스트 하우스
       (27, 'guestHouse1.png', '/images/guest_house/guestHouse1.png', now()),
       (28, 'guestHouse2.png', '/images/guest_house/guestHouse2.png', now()),
       (28, 'guestHouse3.png', '/images/guest_house/guestHouse3.png', now()),
       (29, 'guestHouse4.png', '/images/guest_house/guestHouse4.png', now()),
       (30, 'guestHouse5.png', '/images/guest_house/guestHouse5.png', now()),
       (31, 'guestHouse6.png', '/images/guest_house/guestHouse6.png', now()),
       -- 캠핑
       (32, 'camping1.png', '/images/camping/camping1.png', now()),
       (33, 'camping2.png', '/images/camping/camping2.png', now()),
       (34, 'camping3.png', '/images/camping/camping3.png', now()),
       (35, 'camping4.png', '/images/camping/camping4.png', now()),
       (35, 'camping5.png', '/images/camping/camping5.png', now()),
       (36, 'camping6.png', '/images/camping/camping6.png', now()),
       (36, 'camping1.png', '/images/camping/camping1.png', now()),
       -- 모텔
       (37, 'motel1.png', '/images/motel/motel1.png', now()),
       (38, 'motel2.png', '/images/motel/motel2.png', now()),
       (39, 'motel3.png', '/images/motel/motel3.png', now()),
       (40, 'motel4.png', '/images/motel/motel4.png', now()),
       (41, 'motel5.png', '/images/motel/motel5.png', now()),
       -- 펜션
       (42, 'pension2.png', '/images/pension/pension2.png', now()),
       (42, 'pension1.png', '/images/pension/pension1.png', now()),
       (42, 'pension3.png', '/images/pension/pension3.png', now()),
       (43, 'pension4.png', '/images/pension/pension4.png', now()),
       (43, 'pension5.png', '/images/pension/pension5.png', now()),
       (44, 'pension6.png', '/images/pension/pension6.png', now()),
       (44, 'pension1.png', '/images/pension/pension1.png', now()),
       (45, 'pension2.png', '/images/pension/pension2.png', now()),
       (45, 'pension3.png', '/images/pension/pension3.png', now()),
       (46, 'pension4.png', '/images/pension/pension4.png', now()),
       -- 홈앤빌라
       (47, 'homeAndVilla1.png', '/images/home_and_villa/homeAndVilla1.png', now()),
       (48, 'homeAndVilla2.png', '/images/home_and_villa/homeAndVilla2.png', now()),
       (49, 'homeAndVilla3.png', '/images/home_and_villa/homeAndVilla3.png', now()),
       (50, 'homeAndVilla4.png', '/images/home_and_villa/homeAndVilla4.png', now()),
       (50, 'homeAndVilla5.png', '/images/home_and_villa/homeAndVilla5.png', now()),
       (51, 'homeAndVilla6.png', '/images/home_and_villa/homeAndVilla6.png', now()),
       (51, 'homeAndVilla1.png', '/images/home_and_villa/homeAndVilla1.png', now()),
       -- 게스트 하우스
       (52, 'guestHouse1.png', '/images/guest_house/guestHouse1.png', now()),
       (52, 'guestHouse2.png', '/images/guest_house/guestHouse2.png', now()),
       (53, 'guestHouse3.png', '/images/guest_house/guestHouse3.png', now()),
       (53, 'guestHouse4.png', '/images/guest_house/guestHouse4.png', now()),
       (53, 'guestHouse5.png', '/images/guest_house/guestHouse5.png', now()),
       (54, 'guestHouse6.png', '/images/guest_house/guestHouse6.png', now()),
       (54, 'guestHouse1.png', '/images/guest_house/guestHouse1.png', now()),
       (55, 'guestHouse2.png', '/images/guest_house/guestHouse2.png', now()),
       (55, 'guestHouse3.png', '/images/guest_house/guestHouse3.png', now()),
       (56, 'guestHouse4.png', '/images/guest_house/guestHouse4.png', now()),
       (56, 'guestHouse5.png', '/images/guest_house/guestHouse5.png', now()),
       (57, 'guestHouse6.png', '/images/guest_house/guestHouse6.png', now());
