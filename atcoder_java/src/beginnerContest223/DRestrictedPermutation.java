package beginnerContest223;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class DRestrictedPermutation {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] junjoArray = new int[n];
        int[][] abArray = new int[n][2];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int[] ab = {a, b};
            abArray[i] = ab;
        }
        Arrays.sort(abArray, new Comparator<int[]>() {
            public int compare(int[] ab1, int[] ab2) {
                if (ab1[0] == ab2[0]) {
                    return ab1[1] - ab2[1];
                } else {
                    return ab1[0] - ab2[0];
                }
            }
        });

        int junjo = 1;
        for (int i = 0; i < m; i++) {
            int[] ab = abArray[i];
            int aIndex = ab[0] - 1;
            int bIndex = ab[1] - 1;

            if (junjoArray[aIndex] < 1 && junjoArray[bIndex] < 1) {
                if (aIndex < bIndex) {
                    junjoArray[aIndex] = junjo;
                    junjoArray[bIndex] = junjo + 1;
                } else {
                    junjoArray[bIndex] = junjo;
                    junjoArray[aIndex] = junjo + 1;
                }
                junjo = junjo + 2;
            } else if(junjoArray[aIndex] < 1) {

            } else {
                System.out.println();
            }

        }

        int[] result = new int[n];


        System.out.println(result);
    }
}
