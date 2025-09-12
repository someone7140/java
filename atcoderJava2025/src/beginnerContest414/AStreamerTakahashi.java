package beginnerContest414;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AStreamerTakahashi {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var l = sc.nextInt();
        var r = sc.nextInt();
        sc.nextLine();

        var result = 0;
        for (int i = 0; i < n; i++) {
            var xy = Arrays.stream(sc.nextLine().split(" "))
                    .map(a -> Integer.parseInt(a)).collect(Collectors.toList());
            if (xy.get(0) <= l && xy.get(1) >= r) {
                result = result + 1;
            }
        }

        System.out.println(result);
    }
}
