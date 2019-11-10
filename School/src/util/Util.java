package util;

import java.util.Scanner;

public class Util {

    public static int getPositiveInteger() {
        Scanner input = new Scanner(System.in);
        String errorMessage = "Not valid value - Please input a positive integer ";

        while (true) {
            while (!input.hasNextInt()) {
                System.out.println(errorMessage);
                input.next();
            }
            int number = input.nextInt();
            if (number >= 0) {
                return number;
            } else {
                System.out.println(errorMessage);
            }
        }
    }

    public static int getPositiveInteger(int a, int b) {
        Scanner input = new Scanner(System.in);
        String errorMessage = "Not valid value " + "\n Insert value on range " + a + "-" + b;

        while (true) {
            while (!input.hasNextInt()) {
                System.out.println(errorMessage);
                input.next();
            }
            int number = input.nextInt();
            if (number >= a & number <= b) {
                return number;
            } else {
                System.out.println(errorMessage);
            }
        }
    }

    public static String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length() - 1);
    }
}
