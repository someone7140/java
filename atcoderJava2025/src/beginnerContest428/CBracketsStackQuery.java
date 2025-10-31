package beginnerContest428;

import java.util.*;

public class CBracketsStackQuery {
    public static void main(String[] args) {
        class Brackets {
            public boolean isOk;
            public String moji;
            public int startCount;
            public int amariEndCount;

            public Brackets(boolean isOk, String moji, int startCount, int amariEndCount) {
                this.isOk = isOk;
                this.moji = moji;
                this.startCount = startCount;
                this.amariEndCount = amariEndCount;
            }
        }

        var sc = new Scanner(System.in);
        var q = sc.nextInt();
        sc.nextLine();
        var queryResults = new Brackets[q];
        var nowIndex = -1;

        var results = new ArrayList<String>();
        for (int i = 0; i < q; i++) {
            var queries = sc.nextLine().split(" ");
            var command = Integer.parseInt(queries[0]);

            if (command == 2) {
                // 今のindexの状態をnullにする
                queryResults[nowIndex] = null;
                nowIndex = nowIndex - 1;
                if (nowIndex < 0) {
                    results.add("Yes");
                } else {
                    results.add(queryResults[nowIndex].isOk ? "Yes" : "No");
                }
            } else {
                var moji = queries[1];
                nowIndex = nowIndex + 1;
                if (nowIndex == 0) {
                    results.add("No");
                    queryResults[nowIndex] = new Brackets(false, moji, moji.equals("(") ? 1 : 0, moji.equals(")") ? 1 : 0);
                } else {
                    var before = queryResults[nowIndex - 1];
                    if (moji.equals("(")) {
                        results.add("No");
                        queryResults[nowIndex] = new Brackets(false, moji, before.startCount + 1, before.amariEndCount);
                    } else {
                        if (before.startCount == 0) {
                            results.add("No");
                            queryResults[nowIndex] = new Brackets(false, moji, 0, before.amariEndCount + 1);
                        } else if (before.startCount == 1) {
                            var isOk = before.amariEndCount == 0 ? true : false;
                            results.add(isOk ? "Yes" : "No");
                            queryResults[nowIndex] = new Brackets(isOk, moji, 0, before.amariEndCount);
                        } else {
                            results.add("No");
                            queryResults[nowIndex] = new Brackets(false, moji, before.startCount - 1, before.amariEndCount);
                        }
                    }
                }
            }

        }

        System.out.println(String.join("\n", results));
    }
}
