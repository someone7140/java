package regularContest104;

import java.util.Scanner;

public class APlusMinus {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int x = (a + b) / 2;
        int y = a - x;
        System.out.println(x + " " + y);
    }
}
