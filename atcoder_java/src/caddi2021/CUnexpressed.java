package caddi2021;

import java.util.HashMap;
import java.util.Scanner;

public class CUnexpressed {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long sqrt = (long)Math.sqrt(n) + 1;
        HashMap<Long, Integer> ruijouMap = new HashMap<>();
        for (long i = 2; i <= sqrt; i++) {
            if (ruijouMap.get(i) == null) {
                long temp = i;
                while (temp <= n) {
                    temp = temp * i;
                    if (temp <= n) {
                        ruijouMap.put(temp, 1);
                    }
                }
            }
        }
        System.out.println(n - ruijouMap.size());
    }
}
