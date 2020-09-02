package beginnerContest134;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;

public class CExceptionHandling {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> aList = new ArrayList();
        for (int i = 1; i <= n; i++) {
            aList.add(sc.nextInt());
        }
        List<Integer> sortedList = aList.stream().sorted(reverseOrder()).collect(Collectors.toList());
        int max = sortedList.get(0);

        for (int i = 0; i < n; i++) {
            int tempA = aList.get(i);
            if (tempA == max) {
                System.out.println(sortedList.get(1));
            } else {
                System.out.println(max);
            }
        }
    }
}
