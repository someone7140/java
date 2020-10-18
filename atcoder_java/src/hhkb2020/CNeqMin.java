package hhkb2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CNeqMin {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> resultList = new ArrayList<>();
        int[] pArray= new int[n];
        // 最小値
        boolean[] minList = new boolean[200002];
        for(int i = 0; i <= 200001; i++) {
            minList[i] = true;
        }
        int min = 0;
        for(int i = 0; i < n; i++) {
            int p = sc.nextInt();
            minList[p] = false;
            if (p == min) {
                for (int j = min; j < n + 2; j++) {
                    if (minList[j]) {
                        min = j;
                        break;
                    }
                }
            }
            resultList.add(min);
        }

        for (Integer result : resultList) {
            System.out.println(result);
        }
    }
}
