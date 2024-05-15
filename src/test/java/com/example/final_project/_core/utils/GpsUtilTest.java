package com.example.final_project._core.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GpsUtilTest {

    @Test
    public void getLatLng_test(){
        // given
        String address = "서울특별시 중구 세종대로 110";

        // when
        double[] result = GpsUtil.getLatLng(address);

        // eye
        System.out.println("getLatLng_test result : " + Arrays.toString(result));

        // then


    }
}
