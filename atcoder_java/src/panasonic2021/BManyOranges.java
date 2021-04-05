package panasonic2021;

import java.util.Scanner;

public class BManyOranges {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int w = sc.nextInt() * 1000;

        int max = 0;
        int min = 0;

        int start = w / b;
        int end = w / a + 1;

        boolean findFlag = false;
        // 最小から回す
        for (int i = start; i <= end; i++) {
            if (findFlag) {
               break;
            }
            for (int j = b; j >= a; j--) {
                int sum = j * i;
                if (sum == w) {
                    if (min == 0 || i < min) {
                        min = i;
                    }
                    if (max == 0 || i > max) {
                        max = i;
                    }
                    findFlag = true;
                    break;
                }
                for (int l = j - 1; l >= a; l--) {
                    boolean loopFlag = true;
                    for (int aCount = i - 1; aCount >= 0; aCount--) {
                        int sum2 = l * aCount + j * (i - aCount);
                        if (sum2 == w) {
                            if (min == 0 || i < min) {
                                min = i;
                            }
                            if (max == 0 || i > max) {
                                max = i;
                            }
                            if (aCount == i - 1) {
                                loopFlag = false;
                            }
                            findFlag = true;
                            break;
                        }
                        if (sum2 > w) {
                            if (aCount == i - 1) {
                                loopFlag = false;
                            }
                            break;
                        }
                    }
                    if (!loopFlag) {
                        break;
                    }
                }
            }
        }
        findFlag = false;
        // 最大から回す
        for (int i = end; i >= start; i--) {
            if (findFlag) {
                break;
            }
            for (int j = a; j <= b; j++) {
                int sum = j * i;
                if (sum == w) {
                    if (min == 0 || i < min) {
                        min = i;
                    }
                    if (max == 0 || i > max) {
                        max = i;
                    }
                    findFlag = true;
                    break;
                }
                if (sum > w) {
                    break;
                }
                for (int l = j + 1; l <= b; l++) {
                    boolean loopFlag = true;
                    for (int aCount = i - 1; aCount >= 0; aCount--) {
                        int sum2 = j * aCount + l * (i - aCount);
                        if (sum2 == w) {
                            if (min == 0 || i < min) {
                                min = i;
                            }
                            if (max == 0 || i > max) {
                                max = i;
                            }
                            if (aCount == i - 1) {
                                loopFlag = false;
                            }
                            findFlag = true;
                            break;
                        }
                        if (sum2 > w) {
                            if (aCount == i - 1) {
                                loopFlag = false;
                            }
                            break;
                        }
                    }
                    if (!loopFlag) {
                        break;
                    }
                }
            }
        }

        if (max == 0 && min == 0) {
            System.out.println("UNSATISFIABLE");
            return;
        }
        if (max == 0) {
            System.out.println(min + " " + min);
            return;
        }
        if (min == 0) {
            System.out.println(max + " " + max);
            return;
        }
        System.out.println(min + " " + max);
    }
}
