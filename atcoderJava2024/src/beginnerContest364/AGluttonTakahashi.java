package beginnerContest364;

import java.util.Scanner;

public class AGluttonTakahashi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        String before = "";
        var count = 0;
        for (int i = 0; i < n; i++) {
            var temp = sc.nextLine();
            count = count + 1;
            if (temp.equals(before) && temp.equals("sweet")) {
                break;
            }
            before = temp;
        }

        System.out.println(count == n ? "Yes" : "No");
    }
}
