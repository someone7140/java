package regularContest106;

import java.util.Scanner;

public class A106 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n  = sc.nextLong();
        String result = "-1";
        boolean initialFlg = true;
        long a = 1;
        long b = 1;
        while (true) {
            if (!result.equals("-1")) {
                break;
            } else {
                if (initialFlg) {
                    initialFlg = false;
                }
                else if (a > b) {
                    b++;
                } else {
                    a++;
                }
            }
            long tempResult = ruiJou(3, a) + ruiJou(5, b);
            if (tempResult == n) {
                result = a + " " + b;
                break;
            } else if (tempResult > n) {
                break;
            } else {
                // Aを動かす
                long tempA = a;
                long tempB = b;
                long tempResult2 = 0;
                while (tempResult2 <= n) {
                    tempA++;
                    tempResult2 = ruiJou(3, tempA) + ruiJou(5, tempB);
                    if (tempResult2 == n) {
                        result = tempA + " " + tempB;
                        break;
                    }
                }
                // Bを動かす
                tempA = a;
                tempB = b;
                tempResult2 = 0;
                while (tempResult2 <= n) {
                    tempB++;
                    tempResult2 = ruiJou(3, tempA) + ruiJou(5, tempB);
                    if (tempResult2 == n) {
                        result = tempA + " " + tempB;
                        break;
                    }
                }
            }
        }
        System.out.println(result);
    }

    private static long ruiJou(long rui, long count) {
        long result2 = rui;
        for(int i = 1; i < count ; i++) {
            result2= result2 * rui;
        }
        return result2;
    }
}
