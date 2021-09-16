package beginnerContest218;

import java.util.Scanner;

public class AWeatherForecast {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt() - 1;
        sc.nextLine();
        String s = sc.nextLine();
        char[] sChars = s.toCharArray();
        String result = String.valueOf(sChars[n]).equals("o") ? "Yes" : "No";
        System.out.println(result);

    }
}
