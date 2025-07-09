package beginnerContest413;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DMakeGeometricSequence {

    // 最大公約数
    static int calcGcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return calcGcd(b, a % b);
        }
    }

    public static void main(String[] args) {

        class AElem {
            public int originalValue;
            public int absValue;
            public String fugou;

            public AElem(int originalValue, int absValue, String fugou) {
                this.originalValue = originalValue;
                this.absValue = absValue;
                this.fugou = fugou;
            }
        }

        class TouhiResult {
            public int minAbsValue;
            public int maxAbsValue;
            public boolean isSameFugou;

            public TouhiResult(int minAbsValue, int maxAbsValue, boolean isSameFugou) {
                this.minAbsValue = minAbsValue;
                this.maxAbsValue = maxAbsValue;
                this.isSameFugou = isSameFugou;
            }
        }

        var sc = new Scanner(System.in);
        var t = sc.nextInt();
        sc.nextLine();

        var resultList = new ArrayList<String>();

        for (int i = 0; i < t; i++) {
            var n = Integer.parseInt(sc.nextLine());
            var aList = Arrays.stream(sc.nextLine().split(" "))
                    .map(a -> {
                        var originalValue = Integer.parseInt(a);
                        var absValue = Math.abs(originalValue);
                        return new AElem(originalValue, absValue, originalValue < 0 ? "minus" : "plus");
                    }).collect(Collectors.toList());
            aList.sort(Comparator.comparingInt(a -> a.absValue));

            TouhiResult touhiResult = null;
            var tempResult = "Yes";
            Integer maxKouyaku = null;
            for (int j = 0; j < n - 1; j++) {
                var a1 = aList.get(j);
                var a2 = aList.get(j + 1);

                if (j == 0) {
                    // 最大公約数
                    maxKouyaku = calcGcd(a1.absValue, a2.absValue);

                    var minAbsValue = a1.absValue / maxKouyaku;
                    var maxAbsValue = a2.absValue / maxKouyaku;
                    var isSameFugou = a1.fugou.equals(a2.fugou);
                    touhiResult = new TouhiResult(minAbsValue, maxAbsValue, isSameFugou);
                } else {
                    maxKouyaku = (maxKouyaku * touhiResult.maxAbsValue) / touhiResult.minAbsValue;
                    var minAbsValue = a1.absValue / maxKouyaku;
                    var maxAbsValue = a2.absValue / maxKouyaku;
                    var isSameFugou = a1.fugou.equals(a2.fugou);
                    if(touhiResult.minAbsValue != minAbsValue ||
                            touhiResult.maxAbsValue != maxAbsValue ||
                            touhiResult.isSameFugou != isSameFugou
                    ) {
                        tempResult = "No";
                        break;
                    }

                }

            }
            resultList.add(tempResult);
        }

        System.out.println(String.join("\n", resultList));
    }
}
