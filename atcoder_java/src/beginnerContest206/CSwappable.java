package beginnerContest206;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CSwappable {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, Integer> choufukuMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            Integer aValue = choufukuMap.get(a);
            if (aValue == null) {
                choufukuMap.put(a, 1);
            } else {
                choufukuMap.put(a, aValue + 1);
            }
        }
        long result = 0;
        result = nCr(n, 2);
        for (Map.Entry<Integer, Integer> choufuku : choufukuMap.entrySet()) {
            int count = choufuku.getValue();
            if (count >= 2) {
                result = result - nCr(count, 2);
            }
        }
        System.out.println(result);
    }

    static long nCr(long n, long r)
    {
        if (r == 0) {
            return 1;
        }

        return (n - r + 1) * nCr(n, r - 1) / r;
    }
}
