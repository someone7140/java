package beginnerContest224;

import java.util.*;

public class CTriangle {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] xyArray = new int[n][2];

        for (int i = 0; i < n; i++) {
            int [] xyTempArray = new int[2];
            xyTempArray[0] = sc.nextInt();
            xyTempArray[1] = sc.nextInt();
            xyArray[i] = xyTempArray;
        }

        long result = (long)n * (long)(n - 1) * (long)(n - 2) / 6L;

        Arrays.sort(xyArray, new Comparator<int[]>() {
            public int compare(int[] xy1, int[] xy2) {
                return xy1[0] - xy2[0];
            }
        });
        int before = -1000000002;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int temp = xyArray[i][0];
            if (temp != before || i == n - 1) {
                if (count > 2) {
                    result = result - ((long)count * (long)(count - 1) * (long)(count - 2) / 6L);
                }
                count = 1;
            } else {
                count++;
            }
        }

        Arrays.sort(xyArray, new Comparator<int[]>() {
            public int compare(int[] xy1, int[] xy2) {
                return xy1[1] - xy2[1];
            }
        });
        before = -1000000002;
        count = 0;
        for (int i = 0; i < n; i++) {
            int temp = xyArray[i][1];
            if (temp != before || i == n - 1) {
                if (count > 2) {
                    result = result - ((long)count * (long)(count - 1) * (long)(count - 2) / 6L);
                }
                count = 1;
            } else {
                count++;
            }
        }

        Set<Double> halfSet = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
            int xy1[] = xyArray[i];
            for (int j = i + 1; j < n; j++) {
                int xy2[] = xyArray[j];
                if (xy1[0] != xy2[0] && xy1[1] != xy2[1]) {
                    int xSabun = xy1[0] - xy2[0];
                    int ySabun = xy1[1] - xy2[1];

                    if(xSabun != 0 && ySabun != 0) {
                        double katamuki = (double)ySabun / (double)xSabun;
                        if (halfSet.contains(katamuki)) {
                            result = result - 1;
                        } else {
                            halfSet.add(katamuki);
                        }
                    }
                }

            }
        }

        System.out.println(result);
    }

    static int lcm (int a, int b) {
        int temp;
        long c = a;
        c *= b;
        while((temp = a%b)!=0) {
            a = b;
            b = temp;
        }
        return (int)(c/b);
    }
}
