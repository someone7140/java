package sompo2021;

import java.util.Scanner;

public class AStar {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int amari = x % 100;
        System.out.println(100 - amari);
    }
}
