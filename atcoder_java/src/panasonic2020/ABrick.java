package panasonic2020;

import java.util.Scanner;

public class ABrick {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        System.out.println(n / w);
    }
}
