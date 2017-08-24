package com.stayrascal.cloud.config;

public class FindOdd {

    public static void main(String[] args) {
        System.out.println(FindOdd.Tickets(new int[]{25, 25, 50, 50, 50, 50, 50}));
    }

    public static String Tickets(int[] peopleInLine) {
        int aCount = 0;
        int bCount = 0;
        String flag = "YES";
        for (int people : peopleInLine) {
            if (people == 25) {
                aCount++;
            }
            if (people == 50) {
                if (aCount > 0) {
                    bCount++;
                    aCount--;
                } else {
                    flag = "NO";
                    break;
                }
            }
            if (people == 100) {
                if (bCount > 0 && aCount > 0) {
                    aCount--;
                    bCount--;
                } else if (aCount > 3) {
                    aCount = aCount - 3;
                } else {
                    flag = "NO";
                    break;
                }
            }
        }
        return flag;
    }

    public static String tickets(int[] peopleInLine) {
        int i, sum = 0, change = 0;
        String a = "";
        for (i = 0; i < peopleInLine.length; i++) {

            sum += 25;
            change = (peopleInLine[i] - 25);
            sum -= change;
            System.out.println("change is: " + change);
            System.out.println("sum is: " + sum);
            if (sum < change) {
                a = "NO";
            } else a = "YES";
        }
        return a;
    }
}
