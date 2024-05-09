package com.example.final_project._core.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class ImageUtil {
    // form에서 사용자가 이미지를 등록한 뒤 사용하는 ImageUtil

    // Base64 인코딩된 이미지 데이터를 디코딩하고 파일로 저장하는 메서드 (저장된 이미지 파일의 경로를 return 해 준다.)
    public static String saveImage(String encodeData, String imageName, String directory) {
        try {
            // 베이스 64로 들어오는 문자열을 바이트로 디코딩하기
            // 데이터 전달
            byte[] decodedBytes = Base64.getDecoder().decode(encodeData);
            // 이미지 파일 넣기
            String imgFilename = UUID.randomUUID() + "_" + imageName; // 여기에서 imageName은 사용자가 지정한 이미지 파일 이름. 파일 이름은 겹칠 수도 있기에 UUID의 랜덤값을 사용하여 고유한 파일 이름을 생성
            // 파일 저장 위치 설정
            Path imgPath = Paths.get("./src/main/resources/static/images/" + imgFilename); // 경로를 직접 적었다.(-> 매개변수의 directory를 사용하지 않아도 가능해진다.)
            // 이미지 데이터를 파일로 저장하기
            Files.write(imgPath, decodedBytes); // 지정한 위치(imgPath)에 decodedBytes를 저장
            // 이미지 저장 경로
            return imgPath.toString(); // 저장된 이미지의 파일 경로를 return
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
