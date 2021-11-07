package beginnerContest223;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CDoukasen {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BigDecimal nokoriKyori = new BigDecimal("0");
        nokoriKyori.setScale(16);
        int[][] abArray = new int[n][2];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int[] ab = {a, b};
            abArray[i] = ab;

            nokoriKyori = nokoriKyori.add(new BigDecimal(String.valueOf(a)));
        }
        BigDecimal result = new BigDecimal("0");
        result.setScale(16);

        int beforeIndex = 0;
        int afterIndex = n - 1;
        BigDecimal beforeNokori = new BigDecimal(String.valueOf(abArray[beforeIndex][0]));
        beforeNokori.setScale(20);
        BigDecimal afterNokori = new BigDecimal(String.valueOf(abArray[afterIndex][0]));
        afterNokori.setScale(20);
        while (beforeIndex != afterIndex) {
            int[] before = abArray[beforeIndex];
            int[] after = abArray[afterIndex];
            BigDecimal beforeNokoriByou = beforeNokori.divide(new BigDecimal(String.valueOf(before[1])), 20, RoundingMode.HALF_EVEN);
            beforeNokoriByou.setScale(20);
            BigDecimal afterNokoriByou = afterNokori.divide(new BigDecimal(String.valueOf(after[1])), 20, RoundingMode.HALF_EVEN);
            afterNokoriByou.setScale(20);
            if (beforeNokoriByou.compareTo(afterNokoriByou) > 0) {
                BigDecimal beforeSusumi = afterNokoriByou.multiply(new BigDecimal(String.valueOf(before[1])));
                beforeSusumi.setScale(20);
                beforeNokori = beforeNokori.subtract(beforeSusumi);
                result = result.add(beforeSusumi);
                nokoriKyori = nokoriKyori.subtract(beforeSusumi);
                nokoriKyori = nokoriKyori.subtract(afterNokori);

                afterIndex = afterIndex - 1;
                afterNokori = new BigDecimal(String.valueOf(abArray[afterIndex][0]));
            } else {
                BigDecimal afterSusumi = beforeNokoriByou.multiply(new BigDecimal(String.valueOf(after[1])));
                afterSusumi.setScale(20);
                afterNokori = afterNokori.subtract(afterSusumi);
                result = result.add(beforeNokori);
                nokoriKyori = nokoriKyori.subtract(beforeNokori);
                nokoriKyori = nokoriKyori.subtract(afterSusumi);

                beforeIndex = beforeIndex + 1;
                beforeNokori = new BigDecimal(String.valueOf(abArray[beforeIndex][0]));
            }
        }

        if (nokoriKyori.compareTo(new BigDecimal("0")) > 0) {
            result = result.add(nokoriKyori.divide(new BigDecimal("2"), 20, RoundingMode.HALF_EVEN));
        }
        System.out.println(result);
    }
}
