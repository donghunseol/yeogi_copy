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

    @Test
    public void getAddress_test(){
        // given
        double[] latAndLon = new double[]{37.5665851, 126.9782038};

        // when
        String result = GpsUtil.getAddress(latAndLon[0], latAndLon[1]);

        // eye
        System.out.println("getAddress_test result : " + result);

        // then


    }
}
