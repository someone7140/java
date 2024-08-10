package beginnerContest366;

import java.util.*;

public class DCuboidSumQuery {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int rowCount = n * n;
        sc.nextLine();

        List<String> resultList = new ArrayList<>();
        Integer[][] aArrayArray = new Integer[rowCount][n];

        for (int i = 0; i < rowCount; i++) {
            var aArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);
            aArrayArray[i] = aArray;
        }

        var q = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < q; i++) {
            Set<String> qSet = new HashSet<>();
            var query = Arrays.stream(sc.nextLine().split(" ")).toArray(String[]::new);
            var temp = "";
            for (int j1 = 0; j1 < 2; j1++) {
                var temp1 = temp + query[j1];
                for (int j2 = 0; j2 < 2; j2++) {
                    var temp2 = temp1 + query[j2 + 2];
                    for (int j3 = 0; j3 < 2; j3++) {
                        var temp3 = temp2 + query[j3 + 4];
                        qSet.add(temp3);
                    }
                }
            }
            var tempResult = 0;
            for (String qStr : qSet) {
                var i1 = Integer.parseInt(String.valueOf(qStr.charAt(0)));
                var i2 = Integer.parseInt(String.valueOf(qStr.charAt(1)));
                var i3 = Integer.parseInt(String.valueOf(qStr.charAt(2)));

                var rowIndex = (i1 - 1) * n + i2 - 1;
                var columnIndex = i3 - 1;
                tempResult = tempResult + aArrayArray[rowIndex][columnIndex];
            }
            resultList.add(String.valueOf(tempResult));
        }

        System.out.println(String.join("\n", resultList));
    }
}
