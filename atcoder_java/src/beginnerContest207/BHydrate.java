package beginnerContest207;

import java.util.Scanner;

public class BHydrate {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();
        double d = sc.nextDouble();
        double e = 0;
        long result = 1;
        if (d == 0) {
            System.out.println(0);
        } else {
            a = a + b;
            e = e + c;
            double tempResult = a / e;
            if (tempResult > d) {
                result++;
                a = a + b;
                e = e + c;
                double tempResult2 = a / e;
                if (tempResult2 >= tempResult) {
                    System.out.println(-1);
                } else {
                    while (tempResult2 > d) {
                        result++;
                        a = a + b;
                        e = e + c;
                        tempResult = tempResult2;
                        tempResult2 = a / e;
                        if (tempResult2 >= tempResult) {
                            result = -1;
                            break;
                        }
                    }
                    System.out.println(result);
                }
            } else {
                System.out.println(1);
            }
        }
    }
}
