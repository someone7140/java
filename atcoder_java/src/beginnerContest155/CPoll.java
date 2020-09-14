package beginnerContest155;

import java.util.*;
import java.util.stream.Collectors;

public class CPoll {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Map wordMap = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            if (wordMap.get(s) == null) {
                wordMap.put(s, 1);
            } else {
                wordMap.put(s, (int)wordMap.get(s) + 1);
            }
        }
        // entrySetのListに変換する
        List<Map.Entry<String, Integer>> entryList = new ArrayList(wordMap.entrySet());
        // Mapを降順にする
        List<Map.Entry<String, Integer>> sortedEntryList = entryList.stream().sorted(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }
        }).collect(Collectors.toList());
        int maxCount = sortedEntryList.get(0).getValue();
        List<String> resultList = new ArrayList();
        for(Map.Entry<String, Integer> entry : sortedEntryList) {
            if (entry.getValue() == maxCount) {
                resultList.add(entry.getKey());
            } else {
                break;
            }
        }
        resultList.stream().sorted().forEach(r ->
            System.out.println(r)
        );
    }
}
