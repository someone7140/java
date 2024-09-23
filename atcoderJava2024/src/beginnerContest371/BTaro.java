package beginnerContest371;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class BTaro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        var taroSet = new HashSet<String>();
        var resultList = new ArrayList<String>();

        for (int i = 0; i < m; i++) {
            var abArray = Arrays.stream(sc.nextLine().split(" ")).toArray(String[]::new);
            if (!taroSet.contains(abArray[0]) && abArray[1].equals("M")) {
                resultList.add("Yes");
                taroSet.add(abArray[0]);
            } else {
                resultList.add("No");
            }
        }

        System.out.println(String.join("\n", resultList));
    }
}
