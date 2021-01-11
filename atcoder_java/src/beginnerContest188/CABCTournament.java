package beginnerContest188;

import java.util.Scanner;

public class CABCTournament {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int aLength = (int)Math.pow(2, n);
        int aArray[] = new int[aLength];
        for(int i = 0; i < aLength; i++) {
            aArray[i] = sc.nextInt();
        }
        int maxIndex1 = -1;
        int max1 = -1;
        for(int i = 0; i < aLength / 2; i++) {
            if (max1 < aArray[i]) {
                maxIndex1 = i;
                max1 = aArray[i];
            }
        }
        int maxIndex2 = -1;
        int max2 = -1;
        for(int i = aLength / 2; i < aLength; i++) {
            if (max2 < aArray[i]) {
                maxIndex2 = i;
                max2 = aArray[i];
            }
        }
        if (max1 < max2) {
            System.out.println(maxIndex1 + 1);
        } else {
            System.out.println(maxIndex2 + 1);
        }
    }
}
