package beginnerContest413;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AContentTooLarge {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var m = sc.nextInt();
        sc.nextLine();
        var aList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());

        var sum = 0;
        for (var a : aList) {
            sum = sum + a;
        }

        System.out.println(sum <= m ? "Yes" : "No");
    }
}
