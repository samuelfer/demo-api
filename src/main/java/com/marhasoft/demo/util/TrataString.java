package com.marhasoft.demo.util;

public class TrataString {

    public static String substituiCaracteres(String value) {
        return value.replaceAll("[^0-9]", "");
    }
}
