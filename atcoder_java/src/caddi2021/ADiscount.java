package caddi2021;

import java.util.Scanner;

public class ADiscount {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();

        double result = (a - b) / a * 100;
        System.out.println(result);
    }
}
