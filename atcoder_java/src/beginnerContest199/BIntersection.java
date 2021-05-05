package beginnerContest199;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BIntersection {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] aArray = new int[n];

        List<Integer> resultList = new ArrayList<>();
        for (int i  = 1; i <= 1000; i++) {
            resultList.add(i);
        }

        for (int i  = 0; i < n; i++) {
            aArray[i] = sc.nextInt();
        }
        for (int i  = 0; i < n; i++) {
            int b = sc.nextInt();
            int finalI = i;
            resultList = resultList
                    .stream()
                    .filter(r -> r >= aArray[finalI] & r <= b)
                    .collect(Collectors.toList());
        }
        System.out.println(resultList.size());
    }
}
