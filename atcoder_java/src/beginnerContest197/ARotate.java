package beginnerContest197;

import java.util.Scanner;

public class ARotate {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(String.valueOf(s.charAt(1)) + String.valueOf(s.charAt(2)) + String.valueOf(s.charAt(0)));
    }
}
