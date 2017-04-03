package com.ywc.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yanweichen on 2017/4/3.
 */
public class MAIN {

    public static void main(String[] args) {
        String version = "1.0.0";
        String[] split = version.split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : split) {
            stringBuilder.append(s);
        }
        Integer number = Integer.valueOf(stringBuilder.toString());
        number++;
        String strNumber;
        if(number < 10){
            strNumber = String.format("%03d", number);
        } else {
            strNumber = String.valueOf(number);
        }
        char[] chars = strNumber.toCharArray();
        List<String> stringList = new ArrayList<>();
        for (char aChar : chars) {
            stringList.add(String.valueOf(aChar));
        }
        String formatVersion = String.join(".", stringList);
        System.out.println(formatVersion);
    }

}
