package beginnerContest208;

import java.util.*;

public class CFairCandyDistribution {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        long syou = k / n;
        long amari = k % n;
        int[] aArray = new int[n];
        Map<Integer, Long> aMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            aArray[i] = a;
            aMap.put(a, syou);
        }
        int[] sortedAArray = Arrays.stream(aArray).sorted().toArray();
        for (int j = 0;  j < amari; j++) {
            int key = sortedAArray[j];
            Long value = aMap.get(key);
            aMap.put(key, value + 1);
        }
        for (int i = 0; i < n; i++) {
            int key = aArray[i];
            Long value = aMap.get(key);
            System.out.println(value);
        }
    }
}
