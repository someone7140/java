package beginnerContest222;

import java.util.*;

public class CSwissSystemTournament {
    static List<Map.Entry<Integer, Integer>> rankMap = new ArrayList<>();
    static Map<Integer, String[]> jankenMap = new HashMap<>();
    static int n;
    static int peopleCount;
    static int m;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        peopleCount = 2 * n;
        m = sc.nextInt();
        sc.nextLine();

        Map<Integer, Integer> tempMap = new HashMap<>();
        for (int i = 1; i <= peopleCount; i++) {
            tempMap.put(i, 0);
        }
        rankMap = new ArrayList<>(tempMap.entrySet());

        for (int i = 1; i <= peopleCount; i++) {
            String janken = sc.nextLine();
            char[] jankenArray = janken.toCharArray();
            String[] jankenStrs = new String[m];
            for (int j = 0; j < m; j++) {
                jankenStrs[j] = String.valueOf(jankenArray[j]);
            }
            jankenMap.put(i, jankenStrs);
        }

        for (int i = 0; i < m; i++) {
            rankMap = getJankenResult(i);
        }

        rankMap.stream().forEach(p -> {
            System.out.println(p.getKey());
        });
    }

    private static List<Map.Entry<Integer, Integer>> getJankenResult(int gameCount) {
        Map<Integer, Integer> resultMap = new HashMap<>();
        Map.Entry<Integer, Integer>[] listEntries = rankMap.toArray(Map.Entry[]::new);
        for (int i = 0; i < peopleCount; i = i + 2) {
            Map.Entry<Integer, Integer> personA = listEntries[i];
            Map.Entry<Integer, Integer> personB = listEntries[i + 1];
            String jankenA = jankenMap.get(personA.getKey())[gameCount];
            String jankenB = jankenMap.get(personB.getKey())[gameCount];
            if (jankenA.equals(jankenB)) {
                resultMap.put(personA.getKey(), personA.getValue());
                resultMap.put(personB.getKey(), personB.getValue());
            } else if (jankenA.equals("G")){
                if (jankenB.equals("P")) {
                    resultMap.put(personA.getKey(), personA.getValue());
                    resultMap.put(personB.getKey(), personB.getValue() + 1);
                } else {
                    resultMap.put(personA.getKey(), personA.getValue() + 1);
                    resultMap.put(personB.getKey(), personB.getValue());
                }
            } else if (jankenA.equals("C")) {
                if (jankenB.equals("G")) {
                    resultMap.put(personA.getKey(), personA.getValue());
                    resultMap.put(personB.getKey(), personB.getValue() + 1);
                } else {
                    resultMap.put(personA.getKey(), personA.getValue() + 1);
                    resultMap.put(personB.getKey(), personB.getValue());
                }
            } else if (jankenA.equals("P")) {
                if (jankenB.equals("C")) {
                    resultMap.put(personA.getKey(), personA.getValue());
                    resultMap.put(personB.getKey(), personB.getValue() + 1);
                } else {
                    resultMap.put(personA.getKey(), personA.getValue() + 1);
                    resultMap.put(personB.getKey(), personB.getValue());
                }
            }
        }

        return sortRank(resultMap);
    }

    private static List<Map.Entry<Integer, Integer>> sortRank(Map<Integer, Integer> inputRankMap) {
        List<Map.Entry<Integer, Integer>> listEntries = new ArrayList<>(inputRankMap.entrySet());
        Collections.sort(listEntries, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> obj1, Map.Entry<Integer, Integer> obj2) {
                if (obj1.getValue().equals(obj2.getValue())) {
                    return obj1.getKey().compareTo(obj2.getKey());
                }
                return obj2.getValue().compareTo(obj1.getValue());
            }
        });
        return listEntries;
    }
}
