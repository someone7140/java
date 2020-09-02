package beginnerContest134;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BGoldenApple {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
        int result = 0;
        List<Integer> nList = new ArrayList();

        for (int i = 1; i <= n; i++) {
            nList.add(i);
        }

        int tempResult = d + 1;

        while(true) {
            final int min = tempResult - d;
            final int max = tempResult + d;

            result = result + 1;
            nList = nList.stream().filter(n2 -> min > n2 || n2 > max).collect(Collectors.toList());

            if (nList.isEmpty()) {
                break;
            } else {
                tempResult = nList.get(0) + d > n ? nList.get(0) : nList.get(0) + d;
            }
        }
        System.out.println(result);
    }
}
