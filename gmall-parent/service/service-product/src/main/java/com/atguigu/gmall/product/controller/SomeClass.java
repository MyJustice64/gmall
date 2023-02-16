package com.atguigu.gmall.product.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: PZY
 * @date: 2023-02-06 14:22
 * @description:
 **/
public class SomeClass {
    public static int indexOf(String source, String str) {
        if (null == source || source.length() < 1 || null == str || str.length() < 1) {
            return -1;
        }
        if (str.length() > source.length()) {
            return -1;
        }
        char[] srcArray = source.toCharArray();
        char[] dstArray = str.toCharArray();
        int srcLen = srcArray.length;
        int dstLen = dstArray.length;
        for (int i = 0; i < srcLen; i++) {
            boolean find = false;
            if (srcArray[i] == dstArray[0] && (i + dstLen <= srcLen)) {
                int equalCount = 0;
                for (int j = 0; j < dstLen; j++) {
                    if (srcArray[i + j] == dstArray[j]) {
                        equalCount++;
                    }
                }
                if (equalCount == dstLen) {
                    find = true;
                }
            }
            if (find) {
                return i;
            }
        }
        return -1;
    }


    public static int strStr(String haystack, String needle) {
        char[] strs1 = haystack.toCharArray();
        char[] strs2 = needle.toCharArray();
        for (int i = 0; i < strs1.length; i++) {
            if (strs2[0] == strs1[i]) {
                return i;
            }
        }
        return -1;
    }


    public static Integer startChar(String str,String specialChar){
        Matcher matcher= Pattern.compile(specialChar).matcher(str);
        if(matcher.find()){
            System.out.println(matcher.start());
            Integer start = matcher.start();
            return start;
        }
        return null;
    }


//    public static void main(String[] args) {
//        System.out.println(indexOf("abcabdef", "abd"));
//    }

    public static void main(String[] args) {
        System.out.println(strStr("abcabdef", "abd"));
    }

//    public static void main(String[] args) {
//        String str = "abcabdef";
//        Integer start = startChar(str,"}");
//        System.out.println(str.substring(start+1,str.length()));
//    }
}

