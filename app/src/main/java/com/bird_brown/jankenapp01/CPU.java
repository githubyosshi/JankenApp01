package com.bird_brown.jankenapp01;

public class CPU {
    public static int getChoice() {
        //「０」「１」「２」の整数値の乱数を取得
        int choiceNo = (int)(Math.random() * 3);

        return choiceNo;
    }
}
