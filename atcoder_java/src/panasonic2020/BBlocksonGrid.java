package panasonic2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BBlocksonGrid {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int min = 99999999;
        List<Integer> aList = new ArrayList();
        for (int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                int a = sc.nextInt();
                if (a <= min) {
                    min = a;
                }
                aList.add(a);
            }
        }
        long result = 0;
        for (int a : aList) {
            result = result + (a - min);
        }
        System.out.println(result);
    }
}
