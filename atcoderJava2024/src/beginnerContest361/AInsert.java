package beginnerContest361;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AInsert {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();
        sc.nextLine();
        String[] AArray = sc.nextLine().split(" ");
        List<String> results = new ArrayList<>();

        int index = 0;
        for (String a : AArray) {
            results.add(a);
            if (index + 1 == k) {
                results.add(String.valueOf(x));
            }

            index = index + 1;
        }

        System.out.println(String.join(" ", results));

    }
}
