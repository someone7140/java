package beginnerContest423;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CLockAllDoors {

    static Integer n;
    static Integer r;
    static List<Integer> lList;
    static Integer alreadyLockFrontIndex;
    static Integer alreadyLockBackIndex;

    static Integer zenshin(Integer startIndex, List<Integer> targetList, boolean isLock) {
        var result = 0;
        for (int i = startIndex; i < n; i++) {
            if (i >= alreadyLockBackIndex) {
                break;
            }
            if (targetList.get(i) == 1) {
                result = result + (isLock ? 2 : 1);
            } else {
                result = result + (isLock ? 1 : 0);
            }
        }
        return result;
    }

    static Integer gyaku(Integer startIndex, List<Integer> targetList, boolean isLock) {
        var result = 0;
        for (int i = startIndex; i >= 0; i--) {
            if (i <= alreadyLockFrontIndex) {
                break;
            }
            if (targetList.get(i) == 1) {
                result = result + (isLock ? 2 : 1);
            } else {
                result = result + (isLock ? 1 : 0);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        n = sc.nextInt();
        r = sc.nextInt();
        sc.nextLine();
        lList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());

        // 前からすでに鍵がかかってる場所
        alreadyLockFrontIndex = -1;
        while (alreadyLockFrontIndex < n - 1) {
            if ((alreadyLockFrontIndex + 1) == r) {
                break;
            }
            if (lList.get(alreadyLockFrontIndex + 1) == 1) {
                alreadyLockFrontIndex = alreadyLockFrontIndex + 1;
            } else {
                break;
            }
        }
        if (alreadyLockFrontIndex == n - 1) {
            System.out.println(0);
            return;
        }

        // 後ろからすでに鍵がかかってる場所
        alreadyLockBackIndex = n;
        while (alreadyLockBackIndex > 0) {
            if (lList.get(alreadyLockBackIndex - 1) == 1) {
                alreadyLockBackIndex = alreadyLockBackIndex - 1;
                if (alreadyLockBackIndex == r) {
                    break;
                }
            } else {
                break;
            }
        }
        if (alreadyLockBackIndex == 0) {
            System.out.println(0);
            return;
        }

        if (r == 0) {
            System.out.println(zenshin(0, lList, true));
            return;
        }

        if (r == n) {
            System.out.println(gyaku(n - 1, lList, true));
            return;
        }

        var result1 = 0;
        var lList2 = new ArrayList<>(lList);
        result1 = result1 + gyaku(r - 1, lList2, false);
        for (int i = r - 1; i >= 0; i--) {
            if (i <= alreadyLockFrontIndex) {
                break;
            }
            lList2.set(i, 0);
        }
        result1 = result1 + zenshin(alreadyLockFrontIndex + 1, lList2, true);


        var result2 = 0;
        result2 = result2 + zenshin(r, lList, false);
        for (int i = r; i < n; i++) {
            if (i >= alreadyLockBackIndex) {
                break;
            }
            lList.set(i, 0);
        }
        result2 = result2 + gyaku(alreadyLockBackIndex - 1, lList, true);

        System.out.println(result1 < result2 ? result1 : result2);
    }
}
