package beginnerContest369;

import java.util.Arrays;
import java.util.Scanner;

public class CCountArithmeticSubarrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Integer[] aArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);
        long result = n;
        var leftIndex = 0;
        var rightIndex = 0;
        long tempResult = 0;
        int tempSabun = 0;
        Integer tempSabun2 = null;
        boolean switchLeft = false;

        while (leftIndex < n - 1) {
            if (leftIndex == rightIndex) {
                if (rightIndex == n - 1) {
                    break;
                } else {
                    tempResult = 0;
                    rightIndex = rightIndex + 1;
                    switchLeft = false;
                    tempSabun2 = null;
                }
            } else if (rightIndex - leftIndex == 1) {
                if(!switchLeft) {
                    tempResult = 1;
                    result = result + 1;
                }
                if (rightIndex == n - 1) {
                    break;
                } else {
                    tempSabun = aArray[rightIndex] - aArray[leftIndex];
                    switchLeft = false;
                    tempSabun2 = null;
                    rightIndex = rightIndex + 1;
                }
            } else {
                // 差分が等しい
                var tempSabun3 = aArray[rightIndex] - aArray[rightIndex - 1];
                if (tempSabun3 == tempSabun) {
                    tempSabun2 = tempSabun3;
                    if(!switchLeft) {
                        tempResult = tempResult + 1;
                        result = result + tempResult;
                    }
                    if (rightIndex == n - 1) {
                        break;
                    } else {
                        rightIndex = rightIndex + 1;
                        switchLeft = false;
                        tempSabun2 = null;
                    }
                } else {
                    tempResult = tempResult - 1;
                    leftIndex = leftIndex + 1;
                    if (tempSabun2 != null) {
                        switchLeft = true;
                    }
                }
            }
        }

        System.out.println(result);
    }
}
