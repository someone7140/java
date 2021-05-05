package beginnerContest198;

import java.util.Arrays;
import java.util.Scanner;

public class DSendMoreMoney {
    static boolean judgeResult = false;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String n1 = sc.nextLine();
        String n2 = sc.nextLine();
        String n3 = sc.nextLine();

        char[] n1CharArray = n1.toCharArray();
        char[] n2CharArray = n2.toCharArray();
        char[] n3CharArray = n3.toCharArray();

        int n1CharIndex = (int)n1CharArray[0] - 97;
        int n2CharIndex = (int)n2CharArray[0] - 97;
        int n3CharIndex = (int)n3CharArray[0] - 97;

        int[] alphabetArray = new int[26];
        Arrays.fill(alphabetArray, -1);

        alphabetArray[n1CharIndex] = 1;
        alphabetArray[n2CharIndex] = 1;
        alphabetArray[n3CharIndex] = 1;

        int[] n1IntIndex = new int[n1CharArray.length];
        int[] n2IntIndex = new int[n2CharArray.length];
        int[] n3IntIndex = new int[n3CharArray.length];

        for(int i = 1; i < n1CharArray.length; i++) {
            n1IntIndex[i] = (int)n1CharArray[i] - 97;
            alphabetArray[n1IntIndex[i]] = 0;
        }
        for(int i = 1; i < n2CharArray.length; i++) {
            n2IntIndex[i] = (int)n2CharArray[i] - 97;
            alphabetArray[n2IntIndex[i]] = 0;
        }
        for(int i = 1; i < n3CharArray.length; i++) {
            n3IntIndex[i] = (int)n3CharArray[i] - 97;
            alphabetArray[n3IntIndex[i]] = 0;
        }
        calc(n1IntIndex, n2IntIndex, n3IntIndex, alphabetArray, 0);

    }
    private static void calc(int[] n1IntIndex, int[] n2IntIndex, int[] n3IntIndex, int[] alphabetArray, int startIndex) {
        if (!judgeResult) {
            boolean result = judge(n1IntIndex, n2IntIndex, n3IntIndex, alphabetArray);
            if (!result) {
                for (int i = startIndex; i < alphabetArray.length; i++) {
                    if (alphabetArray[i] != -1) {
                        calc(n1IntIndex, n2IntIndex, n3IntIndex, alphabetArray, startIndex++);
                        if (alphabetArray[startIndex] <= 9) {
                            alphabetArray[startIndex]++;
                            calc(n1IntIndex, n2IntIndex, n3IntIndex, alphabetArray, startIndex);
                        }
                    }
                }
            } else {
                judgeResult = true;
            }

        }
    }

    private static boolean judge(int[] n1IntIndex, int[] n2IntIndex, int[] n3IntIndex, int[] alphabetArray) {
        String n1Str = "";
        for(int i = 0; i < n1IntIndex.length; i++) {
            n1Str += String.valueOf(alphabetArray[n1IntIndex[i]]);
        }
        String n2Str = "";
        for(int i = 0; i < n2IntIndex.length; i++) {
            n2Str += String.valueOf(alphabetArray[n2IntIndex[i]]);
        }
        String n3Str = "";
        for(int i = 0; i < n3IntIndex.length; i++) {
            n3Str += String.valueOf(alphabetArray[n3IntIndex[i]]);
        }
        boolean result = (Integer.parseInt(n1Str) + Integer.parseInt(n2Str)) == Integer.parseInt(n3Str);
        if (result) {
            System.out.println(n1Str);
            System.out.println(n2Str);
            System.out.println(n3Str);
        }
        return result;
    }

}
