package beginnerContest355;

import java.util.Arrays;
import java.util.Scanner;

public class BPiano2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        String aStr = sc.nextLine();
        Integer[] aArray = Arrays.stream(aStr.split(" ")).map(a -> Integer.parseInt(a)).sorted().toArray(Integer[]::new);
        String bStr = sc.nextLine();
        Integer[] bArray = Arrays.stream(bStr.split(" ")).map(b -> Integer.parseInt(b)).sorted().toArray(Integer[]::new);
        Integer[] allItems = new Integer[n + m];
        System.arraycopy(aArray, 0, allItems, 0, aArray.length);
        System.arraycopy(bArray, 0, allItems, aArray.length, bArray.length);
        allItems = Arrays.stream(allItems).sorted().toArray(Integer[]::new);

        int aIndex = 0;
        String result = "No";
        for (int i = 0; i < n + m - 1; i++) {
            while (aIndex < n - 2 && aArray[aIndex] < allItems[i]) {
                aIndex = aIndex + 1;
            }

            if  (allItems[i] == aArray[aIndex]) {
                if (aIndex + 1 < n && allItems[i + 1] == aArray[aIndex + 1]) {
                    result = "Yes";
                    break;
                }
            }

        }

        System.out.println(result);
    }
}
