package experiment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListTest {

    public static void main(String[] args){
        // 破壊的な結合
        List<Integer> aList = new ArrayList<>();
        aList.add(1);
        aList.add(2);
        List<Integer> bList = new ArrayList<>();
        bList.add(3);
        bList.add(4);
        aList.addAll(bList);
        System.out.println(aList); // [1, 2, 3, 4]
        System.out.println(bList); // [3, 4]

        // 非破壊的な結合
        List<Integer> cList = Arrays.asList(1, 2);
        List<Integer> dList = Arrays.asList(3, 4);
        // Arrays.asListの初期化で破壊的な結合を行うとexception
        // cList.addAll(dList);
        // 新しいオブジェクトを生成
        List<Integer> eList = Stream.concat(cList.stream(), dList.stream())
                .collect(Collectors.toList());
        System.out.println(cList); // [1, 2]
        System.out.println(dList); // [3, 4]
        System.out.println(eList); // [1, 2, 3, 4]
    }

}
