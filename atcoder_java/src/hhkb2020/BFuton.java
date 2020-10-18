package hhkb2020;

import java.util.Scanner;

public class BFuton {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        sc.nextLine();
        String[][] sArray= new String[h][w];
        for(int i = 0; i < h; i++) {
            String s = sc.nextLine();
            String[] sStringArray = new String[w];
            char[] sCharArray = s.toCharArray();
            for(int j = 0; j < w; j++) {
                sStringArray[j] = String.valueOf(sCharArray[j]);
            }
            sArray[i] = sStringArray;
        }
        long result = 0L;
        for(int i = 0; i < h; i++) {
            int count = 0;
            String[] sStringArray = sArray[i];
            String belowSArray[] = i != h - 1 ? sArray[i + 1] : null;
            for(int j = 0; j < w; j++) {
                String s = sStringArray[j];
                // 横の判定
                if(s.equals(".")) {
                    count++;
                    if (count == 2) {
                        result++;
                        count--;
                    }
                } else {
                    count = 0;
                }
                // 縦の判定
                if (belowSArray != null) {
                    String belowS = belowSArray[j];
                    if (s.equals(".") && s.equals(belowS)) {
                        result++;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
