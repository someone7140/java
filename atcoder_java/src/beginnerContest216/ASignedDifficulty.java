package beginnerContest216;

import java.util.Scanner;

public class ASignedDifficulty {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String xyStr = sc.nextLine();
        Integer y = Integer.parseInt(xyStr.substring(xyStr.length() - 1, xyStr.length()));
        Integer x = (int)Double.parseDouble(xyStr);
        if (y >= 0 && y <= 2) {
            System.out.println(x + "-");
        } else if(y >= 3 && y <= 6) {
            System.out.println(x);
        } else {
            System.out.println(x + "+");
        }
    }
}

