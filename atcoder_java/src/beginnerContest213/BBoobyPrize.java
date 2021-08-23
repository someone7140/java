package beginnerContest213;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BBoobyPrize {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> aList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            aList.add(sc.nextInt());
        }
        List<Integer> aListSorted = aList.stream().sorted().collect(Collectors.toList());
        int targetValue = aListSorted.get(n - 2);
        System.out.println(aList.indexOf(targetValue) + 1);
    }
}
