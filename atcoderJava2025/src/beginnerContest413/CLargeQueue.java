package beginnerContest413;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CLargeQueue {

    public static void main(String[] args) {

        class InsertElem {
            public long value;
            public long count;

            public InsertElem(long value, long count) {
                this.value = value;
                this.count = count;
            }
        }

        var sc = new Scanner(System.in);
        var q = sc.nextInt();
        sc.nextLine();

        var resultList = new ArrayList<String>();
        var elemList = new ArrayList<InsertElem>();

        for (int i = 0; i < q; i++) {
            var queries = sc.nextLine().split(" ");
            if (queries[0].equals("1")) {
                var count = Long.parseLong(queries[1]);
                var value = Long.parseLong(queries[2]);
                var elem = new InsertElem(value, count);
                elemList.add(elem);
            }
            if (queries[0].equals("2")) {
                var tempCount = Long.parseLong(queries[1]);
                var tempResult = 0L;
                while (tempCount > 0) {
                    // 初めの要素
                    var firstElem = elemList.get(0);
                    if (firstElem.count <= tempCount) {
                        tempResult = tempResult + firstElem.count * firstElem.value;
                        tempCount = tempCount - firstElem.count;
                        elemList.remove(0);
                    } else {
                        tempResult = tempResult + tempCount * firstElem.value;
                        elemList.set(0, new InsertElem(firstElem.value, firstElem.count - tempCount));
                        tempCount = 0;
                    }
                }
                resultList.add(String.valueOf(tempResult));
            }
        }

        System.out.println(String.join("\n", resultList));
    }
}
