package com.mo.java8.cap7;

/**
 * @author zhuzhuhong
 * @description 第七章
 * @create 2018-05-21 9:30
 **/

public class Java8Cap7 {

    public static void main(String[] args) {

    }

    public int countWords(String s) {
        int count = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    count++;
                }
                lastSpace = false;
            }
        }
        return count;
    }

}
