package com.example.final_project._core.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GpsUtil {
    private static final String API_KEY = "AIzaSyDgfzwEZrw33k81XYAO4wEGAxVbnEzKsgc"; // 발급 받은 API 키

    public static double[] getLatLng(String address){
        try {
            String jsonResponse = getGeocodingJsonResponse(address);
            double[] latLng = parseLatLngFromJson(jsonResponse);
            System.out.println("Latitude: " + latLng[0]); // 위도
            System.out.println("Longitude: " + latLng[1]); // 경도
            return latLng;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 주소에 대한 Geocoding API 요청을 한 뒤, 응답 받은 JSON 데이터 반환
    private static String getGeocodingJsonResponse(String address) throws Exception {
        String encodedAddress = URLEncoder.encode(address, "UTF-8"); // 매개변수로 받은 address를 UTF-8형식으로 인코딩
        String urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress + "&key=" + API_KEY; // Geocoding API에 요청 보낼 URL 생성
        System.out.println("Request URL: " + urlString);

        URL url = new URL(urlString); // 생성된 url 문자열을 기반으로 URL 객체 생성
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // url을 통해 HTTP 연결
        conn.setRequestMethod("GET"); // HTTP 요청 방법을 GET으로 설정

        InputStreamReader reader = new InputStreamReader(conn.getInputStream()); // HttpURLConnection에서 얻은 입력스트림을 얻을 수 있는 InputStreamReader 객체 생성(서버로 부터 응답 받을 때 사용)
        StringBuilder jsonResponse = new StringBuilder(); // 응답을 저장할 StringBuilder 객체 생성(나중에 응답을 문자열로 변환하여 저장할 때 사용)

        // 응답 읽기
        int read; // 읽은 데이터의 길이를 저장할 변수
        char[] buffer = new char[1024]; // 읽은 데이터를 임시로 저장할 버퍼 생성
        while ((read = reader.read(buffer)) != -1) { // read에 읽은 데이터의 길이 저장. EOF(-1)가 아닐 때까지 데이터 읽기
            jsonResponse.append(buffer, 0, read); // 응답을 읽어서 jsonResponse에 추가(buffer 배열의 0부터 read까지의 부분을 추가)
        }
        reader.close(); // 읽기 끝나고 연결 닫기

        System.out.println("Response JSON: " + jsonResponse.toString());

        return jsonResponse.toString(); // 받은 JSON 응답을 문자열 형태로 반환
    }

    // 응답받은 json에서 위도,경도만 추출한 뒤 배열에 담아서 반환
    private static double[] parseLatLngFromJson(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject(); // json응답 문자열을 파싱 후 JsonObject로 변환(Gson 라이브러리의 JsonParser 클래스 사용)
        JsonArray results = jsonObject.getAsJsonArray("results"); // JSON 응답에서 results 키에 해당하는 배열을 가져와서 저장
        if (results.size() > 0) { // 요청한 주소에 대한 결과가 존재하는 경우에만 실행
            JsonObject location = results.get(0).getAsJsonObject().getAsJsonObject("geometry").getAsJsonObject("location"); // geometry 객체 안에 있는 location 객체를 찾아서 저장
            double lat = location.get("lat").getAsDouble(); // location 객체에서 lat 키에 해당하는 값을 가져와 double로 변환 후 저장
            double lng = location.get("lng").getAsDouble(); // location 객체에서 lng 키에 해당하는 값을 가져와 double로 변환 후 저장
            return new double[]{lat, lng}; // 배열로 만들어서 반환
        } else {
            System.err.println("No results found in the JSON response."); // 내용을 오류 출력으로 표시
            throw new RuntimeException("No results found");
        }
    }
}
