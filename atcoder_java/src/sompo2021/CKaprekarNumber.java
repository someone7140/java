package sompo2021;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CKaprekarNumber {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int k = sc.nextInt();
        long tempN = n;
        int loopCount = k;
        long[] tempResults = new long[loopCount + 1];
        tempResults[0] = n;
        for (int i = 1; i <= loopCount; i++) {
            char[] charArray = String.valueOf(tempN).toCharArray();
            Integer[] nArray = new Integer[charArray.length];
            for (int j =0; j < charArray.length; j++) {
                nArray[j] = Character.getNumericValue(charArray[j]);
            }
            String[] maxStrs = Arrays.stream(nArray)
                    .sorted(Comparator.reverseOrder())
                    .map(t -> String.valueOf(t))
                    .toArray(String[]::new);
            long max = Long.parseLong(String.join("", maxStrs));
            String[] minStrs = Arrays.stream(nArray)
                    .sorted()
                    .map(t -> String.valueOf(t))
                    .toArray(String[]::new);
            long min = Long.parseLong(String.join("", minStrs));
            tempResults[i] = max - min;
            tempN = max - min;
        }
        System.out.println(tempResults[k]);
    }

}
