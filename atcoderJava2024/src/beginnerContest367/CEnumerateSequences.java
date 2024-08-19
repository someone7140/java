package beginnerContest367;

import java.util.*;
import java.util.stream.Collectors;

public class CEnumerateSequences {
    static List<String> resultsList = new ArrayList<>();
    static Set<String> resultCheckSet = new HashSet<>();
    static List<Integer> rList = new ArrayList<>();
    static int n = 0;
    static int k = 0;

    static void addResult(ArrayList<Integer> addList) {
        var result = String.join(" ", addList.stream().map(a -> String.valueOf(a)).toList());
        if (!resultCheckSet.contains(result)) {
            resultsList.add(result);
            resultCheckSet.add(result);
        }
    }

    static void judgeFunc(int index, ArrayList<Integer> nowList, int inputSum) {
        if (index >= n) {
            return;
        }

        var rVal = rList.get(index);
        var beforeVal = nowList.get(index);
        var tempSum = inputSum;
        for (int i = beforeVal; i <= rVal; i++) {
            if (i != beforeVal) {
                tempSum = tempSum + 1;
            }
            nowList.set(index, i);
            if (index == n - 1) {
                if(tempSum % k == 0) {
                    addResult(nowList);
                }
            } else {
                judgeFunc(index + 1, nowList, tempSum);
            }
        }
        nowList.set(index, beforeVal);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        sc.nextLine();
        rList = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).collect(Collectors.toList());
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tempList.add(1);
        }

        for (int i = n - 1; i >= 0; i--) {
            int sum = n;
            int beforeVal = tempList.get(i);
            judgeFunc(i, tempList, sum);
            tempList.set(i, beforeVal);
        }

        System.out.println(String.join("\n", resultsList));
    }
}
