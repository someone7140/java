package beginnerContest429;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BN1 {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var m = sc.nextInt();
        sc.nextLine();
        var sum = 0;
        var aList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());

        for (int i = 0; i < n; i++) {
            sum = sum + aList.get(i);
        }

        var result = "No";
        for (int i = 0; i < n; i++) {
            if ((sum - aList.get(i)) == m) {
                result = "Yes";
                break;
            }
        }
        System.out.println(result);
    }
}
