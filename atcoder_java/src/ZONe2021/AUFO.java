package ZONe2021;

import java.util.Scanner;

public class AUFO {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        int result = 0;
        int index = 0;
        while (true) {
            int tempResult = s.indexOf("ZONe", index);
            if (tempResult == -1) {
                break;
            } else {
                result++;
                index += tempResult + 1;
            }
        }
        System.out.println(result);
    }
}
