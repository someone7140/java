package aising2021;

import java.util.*;

public class CMadeUp {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String aStr = sc.nextLine();
        Integer[] aArray = Arrays.stream(aStr.split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);
        String bStr = sc.nextLine();
        Integer[] bArray = Arrays.stream(bStr.split(" ")).map(b -> Integer.parseInt(b)).toArray(Integer[]::new);
        String cStr = sc.nextLine();
        Integer[] cArray = Arrays.stream(cStr.split(" ")).map(c -> Integer.parseInt(c)).toArray(Integer[]::new);
        Map<Integer, Integer> bcMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int b = bArray[cArray[i] - 1];
            Integer bTemp = bcMap.get(b);
            if (bTemp == null) {
                bcMap.put(b, 1);
            } else {
                bcMap.put(b, bTemp + 1);
            }
        }
        long result = 0;
        for (int i = 0; i < n; i++) {
            int a = aArray[i];
            Integer count = bcMap.get(a);
            if (count != null) {
                result = result + count;
            }
        }
        System.out.println(result);
    }
}
