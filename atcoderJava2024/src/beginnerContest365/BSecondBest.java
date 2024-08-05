package beginnerContest365;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class BSecondBest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Integer[] aArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);

        HashMap<Integer, Integer> aMap = new HashMap();
        for (int i = 0; i < n; i++) {
            aMap.put(aArray[i], i + 1);
        }
        var aArraySorted = Arrays.stream(aArray).sorted().toArray(Integer[]::new);
        var secondValue = aArraySorted[n - 2];

        System.out.println(aMap.get(secondValue));
    }
}
