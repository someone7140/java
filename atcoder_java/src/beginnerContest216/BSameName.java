package beginnerContest216;

import java.util.Scanner;

public class BSameName {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] stArray = new String[n];
        for (int i =0; i < n; i++) {
            stArray[i] = sc.nextLine();
        }
        String result = "No";
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (stArray[i].equals(stArray[j])) {
                    result = "Yes";
                    break;
                }
            }
        }

        System.out.println(result);

    }
}
