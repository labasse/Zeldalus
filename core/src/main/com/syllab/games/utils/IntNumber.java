package com.syllab.games.utils;

public class IntNumber {
    public static boolean isOdd (int integer) { return (integer&1) != 0; }
    public static boolean isEven(int integer) { return (integer&1) == 0; }
    public static int opposite(int val, int bit) { return bit!=0 ? -val : val; }
}
