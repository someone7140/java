package beginnerContest355;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AWhoAtetheCake {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int result = -1;
        List<Integer> list = Arrays.asList(1, 2, 3);

        if (a != b) {
            result = list.stream().filter(l -> l != a && l != b).findFirst().get();
        }

        System.out.println(result);
    }
}
