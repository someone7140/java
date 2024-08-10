package beginnerContest366;

import java.util.*;

public class CBallsandBagQuery {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        sc.nextLine();

        List<String> resultList = new ArrayList<>();
        Map<String, Integer> qMap = new HashMap<>();
        var categoryCount = 0;

        for (int i = 0; i < q; i++) {
            var query = Arrays.stream(sc.nextLine().split(" ")).toArray(String[]::new);
            if (query[0].equals("1")) {
                var count = qMap.getOrDefault(query[1], 0);
                if (count == 0) {
                    categoryCount = categoryCount + 1;
                }
                qMap.put(query[1], count + 1);
            } else if (query[0].equals("2")) {
                var count = qMap.getOrDefault(query[1], 0);
                if (count > 0) {
                    count = count - 1;
                    if (count == 0) {
                        categoryCount = categoryCount - 1;
                    }
                    qMap.put(query[1], count);
                }
            } else {
                resultList.add(String.valueOf(categoryCount));
            }
        }

        System.out.println(String.join("\n", resultList));
    }
}
