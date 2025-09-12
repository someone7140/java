package beginnerContest418;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CFlush {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var q = sc.nextInt();
        sc.nextLine();
        var aList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());
        Collections.sort(aList);
        var aSumList = new ArrayList<Integer>();
        var bList = new ArrayList<Integer>();
        var sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + aList.get(i);
            aSumList.add(sum);
        }
        var maxCount = aList.get(n - 1);
        for (int i = 0; i <= maxCount; i++) {
            if (i <= 1) {
                bList.add(i);
            } else if (i < n - 1){
                bList.add(aSumList.get(i - 1));
            } else {
                bList.add(sum - (maxCount - i));
            }
        }

        var resultList = new ArrayList<String>();
        for (int i = 0; i < q; i++) {
            var qCount = Integer.parseInt(sc.nextLine());
            if (qCount > maxCount) {
                resultList.add("-1");
            } else {
                resultList.add(String.valueOf(bList.get(qCount)));
            }
        }

        System.out.println(String.join("\n", resultList));
    }
}
