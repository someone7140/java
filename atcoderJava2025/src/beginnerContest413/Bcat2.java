package beginnerContest413;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Bcat2 {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();

        var sList = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            sList.add(sc.nextLine());
        }

        var sSet = new HashSet<String>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    sSet.add(sList.get(i) + sList.get(j));
                }
            }
        }

        System.out.println(sSet.size());
    }
}
