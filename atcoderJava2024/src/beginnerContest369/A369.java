package beginnerContest369;

import java.util.Scanner;

public class A369 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        int sabun = Math.abs(a - b);
        if (sabun == 0) {
            System.out.println(1);
        } else if(sabun % 2 != 0){
            System.out.println(2);
        } else{
            System.out.println(3);
        }
    }
}
