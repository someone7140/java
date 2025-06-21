package beginnerContest368;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BDecrease2maxelements {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();

        var aList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());
        Collections.sort(aList, Collections.reverseOrder());

        var result = 0;
        while (true) {
            if (aList.get(1) == 0) {
                break;
            }
            result = result +aList.get(1);
            aList.set(0, aList.get(0) - aList.get(1));
            aList.set(1, 0);

            Collections.sort(aList, Collections.reverseOrder());

        }

        System.out.println(result);
    }
}
