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

    public static void main(String[] args) {
        String address = "서울특별시 중구 세종대로 110";
        try {
            String jsonResponse = getGeocodingJsonResponse(address);
            double[] latLng = parseLatLngFromJson(jsonResponse);
            System.out.println("Latitude: " + latLng[0]);
            System.out.println("Longitude: " + latLng[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 응답 JSON 로그
    private static String getGeocodingJsonResponse(String address) throws Exception {
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        String urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress + "&key=" + API_KEY;
        System.out.println("Request URL: " + urlString); // 요청 URL 로그 출력

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
        StringBuilder jsonResponse = new StringBuilder();
        int read;
        char[] buffer = new char[1024];
        while ((read = reader.read(buffer)) != -1) {
            jsonResponse.append(buffer, 0, read);
        }
        reader.close();

        System.out.println("Response JSON: " + jsonResponse.toString()); // 응답 JSON 로그 출력

        return jsonResponse.toString();
    }

    private static double[] parseLatLngFromJson(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonArray results = jsonObject.getAsJsonArray("results");
        if (results.size() > 0) {
            JsonObject location = results.get(0).getAsJsonObject().getAsJsonObject("geometry").getAsJsonObject("location");
            double lat = location.get("lat").getAsDouble();
            double lng = location.get("lng").getAsDouble();
            return new double[]{lat, lng};
        } else {
            System.err.println("No results found in the JSON response."); // 결과 없음 로그 출력
            throw new RuntimeException("No results found");
        }
    }
}
