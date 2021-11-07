package beginnerContest224;

import java.util.Scanner;

public class ATires {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        if (s.endsWith("ist")) {
            System.out.println("ist");
        } else {
            System.out.println("er");
        }
    }
}
