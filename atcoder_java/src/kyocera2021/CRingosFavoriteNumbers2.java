package kyocera2021;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CRingosFavoriteNumbers2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long result = 0;
        Map<Long, Integer> aMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long temp = sc.nextLong();
            if (i != 0) {
                for (Map.Entry<Long, Integer> entry : aMap.entrySet()) {
                    
                }
            } else {
                aMap.put(temp, 1);
            }

        }
        System.out.println(result);
    }
}
