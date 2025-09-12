package beginnerContest415;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AUnsupportedType {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();
        var aSet = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toSet());
        var x = Integer.parseInt(sc.nextLine());

        System.out.println(aSet.contains(x) ? "Yes" : "No");
    }
}
