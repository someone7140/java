package kajima2020;

import java.util.Scanner;

public class BMany110 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String t = sc.nextLine();
        long s = 10000000000L;
        long result = 0;
        String temp = t;
        if (t.equals("0")) {
            result = s;
        } else if (t.equals("1")) {
            result = s * 2L;
        } else if (t.equals("11") || t.equals("10")) {
            result = s;
        } else if(t.equals("01")) {
            result = s - 1;
        } else if (n >= 3) {
            String judge = "";
            boolean isCalc = true;
            int len = temp.length();
            if (t.startsWith("110")) {
                // 3文字ずつ切り落としていく
                while (len >= 3) {
                    judge = temp.substring(0, 3);
                    if (judge.equals("110")) {
                        temp = temp.substring(3, len);
                        len = temp.length();
                    } else {
                        isCalc = false;
                        break;
                    }
                }
                if (
                        isCalc &&
                                ((len == 1 && "1".equals(temp)) ||
                                (len == 2 && "11".equals(temp)))
                ) {
                    long tempResult1 = n / 3;
                    long tempResult2 = n % 3;
                    result = s - (tempResult1 - 1) - (tempResult2 > 0 ? 1 : 0);
                }
            } else if (t.startsWith("101")) {
                // 3文字ずつ切り落としていく
                while (len >= 3) {
                    judge = temp.substring(0, 3);
                    if (judge.equals("101")) {
                        temp = temp.substring(3, len);
                        len = temp.length();
                    } else {
                        isCalc = false;
                        break;
                    }
                }
                if (
                        isCalc &&
                                ((len == 1 && "1".equals(temp)) ||
                                (len == 2 && "10".equals(temp)))
                ) {
                    long tempResult1 = n / 3;
                    result = (s - 1) - (tempResult1 - 1);
                }
            } else if(t.startsWith("011")) {
                // 3文字ずつ切り落としていく
                while (len >= 3) {
                    judge = temp.substring(0, 3);
                    if (judge.equals("011")) {
                        temp = temp.substring(3, len);
                        len = temp.length();
                    } else {
                        isCalc = false;
                        break;
                    }
                }
                if (
                        isCalc &&
                                ((len == 1 && "0".equals(temp)) ||
                                (len == 2 && "01".equals(temp)))
                ) {
                    long tempResult1 = n / 3;
                    long tempResult2 = n % 3;
                    result = (s - 1) - (tempResult1 - 1) - (tempResult2 == 2 ? 1 : 0);
                }
            }
        }
        System.out.println(result);
    }

}
