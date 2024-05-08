package com.example.final_project._core.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class ImageUtil {

    // Base64 인코딩된 이미지 데이터를 디코딩하고 파일로 저장하는 메서드 (return 저장된 이미지 파일의 경로)
    public static String saveImage(String encodeData, String imageName, String directory) {
        try {
            // 베이스 64로 들어오는 문자열을 바이트로 디코딩하기
            // 데이터 전달
            byte[] decodedBytes = Base64.getDecoder().decode(encodeData);
            // 이미지 파일 넣기
            String imgFilename = UUID.randomUUID() + "_" + imageName; // 이미지 파일 오리지널 이름
            // 파일 저장 위치 설정
            Path imgPath = Paths.get("./src/main/resources/static/images/" + imgFilename);
            // 이미지 데이터를 파일로 저장하기
            Files.write(imgPath, decodedBytes);
            // 이미지 저장 경로
            return imgPath.toString();
        } catch (IOException e) {
            // 파일 저장 중 오류 발생 시 처리
            throw new RuntimeException(e);
        }
    }

    // Base64 인코딩된 이미지 데이터를 디코딩하여 바이트 배열로 반환하는 메서드 (디코딩된 이미지의 바이트 배열)
    public static byte[] decodeImageData(String encodedData) {
        return Base64.getDecoder().decode(encodedData);
    }

    // Base64 인코딩된 이미지 데이터를 디코딩하고 파일로 저장하는 메서드 (저장된 이미지 파일의 경로를 반환)
    public static String saveImage(String encodedData, String imageName) {
        return saveImage(encodedData, imageName, "./src/main/resources/static/images/");
    }
}
