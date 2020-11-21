package beginnerContest183;

import java.util.Scanner;

public class AReLU {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        if(x >= 0) {
            System.out.println(x);
        } else {
            System.out.println(0);
        }
    }
}
