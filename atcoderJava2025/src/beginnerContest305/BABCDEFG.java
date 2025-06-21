package beginnerContest305;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class BABCDEFG {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var pqList = Arrays.stream(sc.nextLine().split(" ")).toArray(String[]::new);

        var pInt = Character.getNumericValue(pqList[0].toCharArray()[0]);
        var qInt = Character.getNumericValue(pqList[1].toCharArray()[0]);

        // Mapに番号と次の経路の値を詰める
        var alphabetIntMap = new HashMap<Integer, Integer>();
        alphabetIntMap.put(10, 3); // A
        alphabetIntMap.put(11, 1); // B
        alphabetIntMap.put(12, 4); // C
        alphabetIntMap.put(13, 1); // D
        alphabetIntMap.put(14, 5); // E
        alphabetIntMap.put(15, 9); // F

        var result =0;
        var start = pInt > qInt ? qInt : pInt;
        var end = pInt > qInt ? pInt : qInt;
        for (int i = start; i < end; i++) {
            result = result + alphabetIntMap.get(i);
        }

        System.out.println(result);
    }
}
