package com.example.bijoya.main.Utils;

/**
 * Created by Bijoya on 24-Jan-18.
 */

public class StringManipulation {

    public static String expandUsername(String username){
        return username.replace(".", " ");
    }

    public static String condenseUsername(String username){
        return username.replace(" " , ".");
    }
}
