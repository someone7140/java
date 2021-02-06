package beginnerContest189;

import java.util.Scanner;

public class ASlot {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String c = sc.nextLine();
        String result = "Won";
        String temp = null;
        char[] cArray = c.toCharArray();
        for(char cChar : cArray) {
            String cString = String.valueOf(cChar);
            if(temp != null) {
                if(!cString.equals(temp)) {
                    result = "Lost";
                }
            }
            temp = cString;
        }
        System.out.println(result);
    }
}
