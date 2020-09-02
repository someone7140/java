package beginnerContest173;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BJudgeStatusSummary {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map countMap = new HashMap<String, Integer>();
        countMap.put("AC", 0);
        countMap.put("WA", 0);
        countMap.put("TLE", 0);
        countMap.put("RE", 0);

        for (int i = 1; i <= n; i++) {
            String s = sc.next();
            if ("AC".equals(s)) {
                countMap.put("AC", Integer.parseInt(countMap.get("AC").toString()) + 1);
            } else if ("WA".equals(s)) {
                countMap.put("WA", Integer.parseInt(countMap.get("WA").toString()) + 1);
            } else if ("TLE".equals(s)) {
                countMap.put("TLE", Integer.parseInt(countMap.get("TLE").toString()) + 1);
            } else {
                countMap.put("RE", Integer.parseInt(countMap.get("RE").toString()) + 1);
            }
        }

        System.out.println("AC x " + countMap.get("AC"));
        System.out.println("WA x " + countMap.get("WA"));
        System.out.println("TLE x " + countMap.get("TLE"));
        System.out.println("RE x " + countMap.get("RE"));
    }
}
