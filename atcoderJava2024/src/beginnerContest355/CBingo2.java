package beginnerContest355;

import java.util.*;

public class CBingo2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        sc.nextLine();
        String aStr = sc.nextLine();
        Integer[] aArray = Arrays.stream(aStr.split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);
        int result = -1;

        Map<Integer, Integer> tateMap = new HashMap<>();
        Map<Integer, Integer> yokoMap = new HashMap<>();
        int naname1 = 0;
        int naname2 = 0;

        Set<Integer> naname2Items = new HashSet();
        for (int i = 0; i < n; i++) {
            naname2Items.add(n + (n - 1) * i);
        }

        Set<Integer> naname1Items = new HashSet();
        for (int i = 0; i < n; i++) {
            naname1Items.add(1 + (n + 1) * i);
        }

        for (int i = 0; i < t; i++) {
            int a = aArray[i];
            int tate = a / n;
            int amari = a % n;
            int yoko = amari - 1;
            if (amari == 0) {
                tate = tate - 1;
                yoko = t - 1;
            }

            if (tateMap.containsKey(tate)) {
                tateMap.put(tate, tateMap.get(tate) + 1);
            } else {
                tateMap.put(tate, 1);
            }
            if (yokoMap.containsKey(yoko)) {
                yokoMap.put(yoko, yokoMap.get(yoko) + 1);
            } else {
                yokoMap.put(yoko, 1);
            }

            if (naname1Items.contains(a)) {
                naname1 = naname1 + 1;
            }
            if (naname2Items.contains(a)) {
                naname2 = naname2 + 1;
            }

            int tateResult = tateMap.get(tate);
            int yokoResult = yokoMap.get(yoko);

            if (tateResult >= n || yokoResult >= n || naname1 >= n || naname2 >= n) {
                result = i + 1;
                break;
            }
        }

        System.out.println(result);
    }
}
