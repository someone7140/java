package ZONe2021;

import java.util.*;

public class CMADTEAM {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = 0;
        int[] maxResultArray = new int[5];

        int[][] abcdeArray = new int[3][5];

        for (int i = 0; i < n; i++) {
            int[] abcdeTemp = new int[5];
            abcdeTemp[0] = sc.nextInt();
            abcdeTemp[1] = sc.nextInt();
            abcdeTemp[2] = sc.nextInt();
            abcdeTemp[3] = sc.nextInt();
            abcdeTemp[4] = sc.nextInt();
            if (i <= 2) {
                abcdeArray[i] = abcdeTemp;
                if (i == 2) {
                    maxResultArray = getMaxResultArray(abcdeArray[0], abcdeArray[1], abcdeArray[2]);
                    result = getResult(maxResultArray);
                }

            } else {
                boolean reCalc = false;
                for (int k = 0; k < 5; k++) {
                    if (maxResultArray[k] < abcdeTemp[k]) {
                        reCalc = true;
                        break;
                    }
                }
                if (reCalc) {
                    int[][] tempArray = abcdeArray;
                    int[] tempMaxResultArray2 = getMaxResultArray(abcdeTemp, abcdeArray[1], abcdeArray[2]);
                    int[] tempMaxResultArray3 = getMaxResultArray(abcdeArray[0], abcdeTemp, abcdeArray[2]);
                    int[] tempMaxResultArray4 = getMaxResultArray(abcdeArray[0], abcdeArray[1], abcdeTemp);

                    int min1 = getResult(tempMaxResultArray2);
                    int min2 = getResult(tempMaxResultArray3);
                    int min3 = getResult(tempMaxResultArray4);

                    if (result < min1) {
                        result = min1;
                        maxResultArray = tempMaxResultArray2;
                        tempArray[0] = abcdeTemp;
                        tempArray[1] = abcdeArray[1];
                        tempArray[2] = abcdeArray[2];
                    }
                    if (result < min2) {
                        result = min2;
                        maxResultArray = tempMaxResultArray3;
                        tempArray[0] = abcdeArray[0];
                        tempArray[1] = abcdeTemp;
                        tempArray[2] = abcdeArray[2];
                    }
                    if (result < min3) {
                        result = min3;
                        maxResultArray = tempMaxResultArray3;
                        tempArray[0] = abcdeArray[0];
                        tempArray[1] = abcdeArray[1];
                        tempArray[2] = abcdeTemp;
                    }
                    abcdeArray = tempArray;
                }
            }
        }
        System.out.println(result);
    }

    private static int[] getMaxResultArray(int[] abcdeArray1, int[] abcdeArray2, int[] abcdeArray3) {
        int [] abcdeTemp = new int[5];
        abcdeTemp[0] = getMax(abcdeArray1[0], abcdeArray2[0], abcdeArray3[0]);
        abcdeTemp[1] = getMax(abcdeArray1[1], abcdeArray2[1], abcdeArray3[1]);
        abcdeTemp[2] = getMax(abcdeArray1[2], abcdeArray2[2], abcdeArray3[2]);
        abcdeTemp[3] = getMax(abcdeArray1[3], abcdeArray2[3], abcdeArray3[3]);
        abcdeTemp[4] = getMax(abcdeArray1[4], abcdeArray2[4], abcdeArray3[4]);
        return abcdeTemp;
    }

    private static int getResult(int[] abcdeArray) {
        return Arrays.stream(abcdeArray).min().getAsInt();
    }

    public static int getMax(Integer... vals) {
        return Collections.max(Arrays.asList(vals));
    }

}
