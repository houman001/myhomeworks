package com.test.problems;

public class BitXorTest {
    private static String getBits(int number) {
        StringBuffer sb = new StringBuffer();
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            int bitValue = number >>> i;
            System.out.println("Shifted " + i + " to right: " + Integer.toBinaryString(bitValue));
            sb.append(bitValue % 2 == 0 ? 0 : 1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int one = 1;
        System.out.println("1: " + one + " (" + getBits(one) + ") (" + Integer.toBinaryString(one) + ")");
        int twoToThe31st = 1 << 31;
        System.out.println("2^31: " + twoToThe31st + " (" + getBits(twoToThe31st) + ") (" +
                           Integer.toBinaryString(twoToThe31st) + ")");
        int xored = one ^ twoToThe31st;
        System.out.println("1 XOR 2^31: " + xored + " (" + getBits(xored) + ") (" + Integer.toBinaryString(xored) +
                           ")");
    }
}
