package beginnerContest181;

import java.util.Scanner;

public class DHachi {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String nStr = sc.nextLine();
        int[] nCountArray = getCountArray(nStr);
        int tempHachi = 0;
        boolean result = false;
        for(int i = 1;tempHachi <  1000000; i++ ) {
            tempHachi = 8 * i;
            int[] tempHachiArray = getCountArray(String.valueOf(tempHachi));
            if(judgeArray(nCountArray, tempHachiArray)) {
                result = true;
                break;
            }
        }
        if (result) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static int[] getCountArray(String nStr) {
        int [] countArray = new int[10];
        for (int i = 0; i < 10; i++) {
            countArray[i] = 0;
        }
        char[] charArray = nStr.toCharArray();
        for(int i = 0; i < charArray.length; i++) {
            int temp = Integer.parseInt(String.valueOf(charArray[i]));
            countArray[temp]++;
        }
        return countArray;
    }

    private static boolean judgeArray(int[] arrayA, int[] arrayB) {
        for (int i = 0; i < arrayA.length; i++) {
            if (arrayA[i] != arrayB[i]) {
                return false;
            }
        }
        return true;
    }
}
