package beginnerContest417;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CDistanceIndicators {

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();
        var aList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());

        class AElem {
            public int minIndex;
            public int maxIndex;
            public List<Integer> indexElem;

            public AElem(int minIndex, int maxIndex, List<Integer> indexElem) {
                this.minIndex = minIndex;
                this.maxIndex = maxIndex;
                this.indexElem = indexElem;
            }
        }

        var aListOnlyValue = new ArrayList<Integer>();
        var aElemMap = new HashMap<Integer, AElem>();

        for (int i = 0; i < n; i++) {
            var a = aList.get(i);
            var elemVal = aElemMap.get(a);
            if (elemVal == null) {
                var index = i + 1;
                aListOnlyValue.add(a);
                aElemMap.put(a, new AElem(
                        index, index, Arrays.asList(index)
                ));
            } else {
                var index = i + 1;
                elemVal.maxIndex = index;
                elemVal.indexElem = Stream.concat(elemVal.indexElem.stream(), Arrays.asList(index).stream()).toList();
                aElemMap.put(a, elemVal);
            }
        }
        aListOnlyValue.sort(Comparator.naturalOrder());
        var aListOnlyValueSize = aListOnlyValue.size();
        long result = 0;

        for (int i = 0; i < aListOnlyValueSize - 1; i++) {
            var aKey = aListOnlyValue.get(i);
            var aElem = aElemMap.get(aKey);
            // まずは自身の中の配列をチェック
            if (aElem.minIndex != aElem.maxIndex) {
                var targetOwn = aKey * 2;
                var ownIndexSize = aElem.indexElem.size();
                for (int j = 0; j < ownIndexSize - 1; j++) {
                    for (int k = j + 1; k < ownIndexSize; k++) {
                        var sabun = aElem.indexElem.get(k) - aElem.indexElem.get(j);
                        if (sabun == targetOwn) {
                            result = result + 1;
                            break;
                        }
                        if (sabun > targetOwn) {
                            break;
                        }
                    }
                }
            }

            // 次に他の値と比較
            for (int i2 = i + 1; i2 < aListOnlyValueSize; i2++) {
                var aKey2 = aListOnlyValue.get(i2);
                if (aKey + aKey2 > n - 1) {
                    break;
                } else {
                    var targetVal = aKey + aKey2;
                    var aElem2 = aElemMap.get(aKey2);
                    var a1IndexSize = aElem.indexElem.size();
                    var a2IndexSize = aElem2.indexElem.size();
                    for (int j = 0; j < a1IndexSize; j++) {
                        for (int k = 0; k < a2IndexSize; k++) {
                            var sabun = Math.abs(aElem.indexElem.get(j) - aElem2.indexElem.get(k));
                            if (sabun == targetVal) {
                                result = result + 1;
                                break;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }
}
