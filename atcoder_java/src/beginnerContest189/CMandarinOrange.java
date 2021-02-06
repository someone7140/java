package beginnerContest189;

import java.util.*;

public class CMandarinOrange {
    static List<Long> resultList = new ArrayList<>();
    static int n = 0;
    static long aArray[] = new long[1];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        aArray = new long[n];
        for (int i = 0; i < n; i++) {
            aArray[i] = sc.nextLong();
        }
        calc(aArray, aArray[0], 1, aArray[0]);
        System.out.println(resultList.stream().max(Long::compareTo).get());
    }

    private static void calc(long[] aTempArray, long eatCount, int index, long sum) {
        long eat = aTempArray[index];
        if (index == n - 1) {
            if (eatCount > eat) {
                resultList.add(sum);
                calc(aArray, eat, 0, 0);
            } else if(eatCount == eat) {
                resultList.add(sum + eat);
            } else {
                resultList.add(sum + eatCount);
                resultList.add(eat);
            }
        } else {
            if (eatCount > eat) {
                resultList.add(sum);
                calc(aArray, eat, 0, 0);
            } else if(eatCount == eat) {
                calc(aTempArray, eat, index + 1, sum + eat);
            } else {
                resultList.add(sum + eatCount);
                calc(aTempArray, eatCount, index + 1, sum + eatCount);
                calc(aTempArray, eat, index + 1, eat);
            }
        }
    }
}
