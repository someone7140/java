package beginnerContest146;

import java.util.Scanner;

public class BROTN {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        String result = "";
        char cArray[] = s.toCharArray();
        for(int i = 0; i < cArray.length; i++) {
            int tempInt = (int)cArray[i] + n;
            if (tempInt > 90) {
                tempInt = tempInt - 26;
            }
            result = result + (char)tempInt;

        }
        System.out.println(result);
    }
}
