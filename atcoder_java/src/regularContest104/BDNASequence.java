package regularContest104;

import java.util.Scanner;

public class BDNASequence {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.nextLine().trim();
        int tCount = 0;
        int aCount = 0;
        int cCount = 0;
        int gCount = 0;
        for(char x: s.toCharArray()){
            if (String.valueOf(x).equals("T")){
                tCount++;
            } else if (String.valueOf(x).equals("A")) {
                aCount++;
            } else if (String.valueOf(x).equals("C")) {
                cCount++;
            } else {
                gCount++;
            }
        }

        long result = 0L;
        for (int i = n - 1 ; i > 0; i--) {
            if (i < n - 1) {
                String deleteS = String.valueOf(s.charAt(i + 1));
                if (deleteS.equals("T")){
                    tCount--;
                } else if (deleteS.equals("A")) {
                    aCount--;
                } else if (deleteS.equals("C")) {
                    cCount--;
                } else {
                    gCount--;
                }
            }
            int tempTCount = tCount;
            int tempACount = aCount;
            int tempCCount = cCount;
            int tempGCount = gCount;
            for (int j = 0; j < i; j++) {
                if (j != 0) {
                    String deleteS2 = String.valueOf(s.charAt(j - 1));
                    if (deleteS2.equals("T")){
                        tempTCount--;
                    } else if (deleteS2.equals("A")) {
                        tempACount--;
                    } else if (deleteS2.equals("C")) {
                        tempCCount--;
                    } else {
                        tempGCount--;
                    }
                }
                if (tempTCount == tempACount && tempCCount == tempGCount) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

}
