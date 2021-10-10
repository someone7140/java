package beginnerContest222;

import java.util.Scanner;

public class AFourDigits {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        if (n.length() == 3) {
            System.out.println("0" + n);
        } else if (n.length() == 2) {
            System.out.println("00" + n);
        } else if (n.length() == 1) {
            System.out.println("000" + n);
        } else {
            System.out.println(n);
        }
    }
}
