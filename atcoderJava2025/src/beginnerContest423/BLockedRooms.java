package beginnerContest423;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BLockedRooms {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();
        var lList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());

        // 前から
        while (true) {
            if (lList.size() == 0 || lList.get(0) == 1) {
                break;
            } else {
                lList.remove(0);
            }
        }

        if (lList.size() <= 1) {
            System.out.println(0);
            return;
        }

        // 後ろから
        while (true) {
            var listSize = lList.size();
            if (listSize == 0 || lList.get(listSize - 1) == 1) {
                break;
            } else {
                lList.remove(listSize - 1);
            }
        }

        var listSize = lList.size();
        if (listSize <= 1) {
            System.out.println(0);
            return;
        }

        System.out.println(listSize - 1);
    }
}
