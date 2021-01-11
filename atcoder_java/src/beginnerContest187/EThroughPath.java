package beginnerContest187;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EThroughPath {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Map<Integer, Integer[]> aMap = new HashMap<>();
        Map<Integer, Integer[]> bMap = new HashMap<>();
        long[] resultArray = new long[n];

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            Integer[] aArray = aMap.get(a);
            if (aArray == null) {
                Integer aryb[] = {b};
                aMap.put(a, aryb);
            } else {
                aMap.put(a, concat(aArray, b));
            }

            Integer[] bArray = bMap.get(b);
            if (bArray == null) {
                Integer arya[] = {a};
                aMap.put(a, arya);
            } else {
                aMap.put(a, concat(bArray, a));
            }
            resultArray[i] = i + 1;
        }
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {


        }


    }

    @SuppressWarnings("unchecked")
    public static <T> T[] concat(final T[] array1, final T... array2) {
        final Class<?> type1 = array1.getClass().getComponentType();
        final T[] joinedArray = (T[]) Array.newInstance(type1, array1.length + array2.length);
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }
}
