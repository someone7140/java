package beginnerContest317;

import java.util.*;

public class BMissingNo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String aStr = sc.nextLine();
        Integer[] aArray = Arrays.stream(Arrays.stream(aStr.split(" "))
                .map(aMoji -> Integer.parseInt(aMoji)).toArray(Integer[]::new)).sorted().toArray(Integer[]::new);

        int before = aArray[0] - 1;
        for (int a : aArray) {
            if (a - before != 1) {
                break;
            }
            before = a;
        }

        System.out.println(before + 1);
    }
}
