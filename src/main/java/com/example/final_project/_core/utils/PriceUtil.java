package com.example.final_project._core.utils;

public class PriceUtil {

    public static String addCommasToPrice(Integer price) {
        String priceString = String.valueOf(price); // price의 타입을 String으로 바꾼 뒤, priceString에 저장
        StringBuilder formattedPrice = new StringBuilder(); // StringBuilder는 문자열을 연결, 삽입, 삭제 및 수정할 때 사용하는 클래스

        int length = priceString.length(); // String으로 바꾼 price의 길이를 length에 저장
        int commaPosition = length % 3; // ,의 위치를 계산 (ex. 10000원이면 5%3=2)

        if (commaPosition == 0) { // 만약 price가 세자리 이하라면
            commaPosition = 3;
        }

        for (int i = 0; i < length; i++) {
            char digit = priceString.charAt(i); // i번째 자릿수를 digit에 저장
            formattedPrice.append(digit); // formattedPrice 뒤에 digit을 추가

            if (i == commaPosition - 1 && i != length - 1) { // 현재 자릿수가 ,를 추가 해야하는 위치에서 한 자리 전이거나 현재 자릿수가 마지막 자릿수라면
                formattedPrice.append(","); // formattedPrice 뒤에 ,를 추가
                commaPosition += 3; // 다음 쉼표를 추가할 위치를 결정하기 위해 3만큼 증가
            }
        }

        return formattedPrice.toString(); // formattedPrice를 String으로 변환
    }
}
