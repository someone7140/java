package beginnerContest414;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BStringTooLong {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();

        var result = "";
        for (int i = 0; i < n; i++) {
            var cl = Arrays.stream(sc.nextLine().split(" ")).collect(Collectors.toList());
            var c = cl.get(0);
            var l = Long.parseLong(cl.get(1));
            if (l > 100) {
                result = "Too Long";
                break;
            }

            for (long j = 0; j < l; j++) {
                result = result + c;
            }

            if (result.length() > 100) {
                result = "Too Long";
                break;
            }
        }

        System.out.println(result);
    }
}
