package beginnerContest211;

import java.util.Scanner;

public class ABloodPressure {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        System.out.println((a - b) / 3 + b);
    }
}

