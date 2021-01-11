package beginnerContest187;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class C1SAT {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String result = "satisfiable";

        sc.nextLine();
        Map<String, String> s1Map = new HashMap<>();
        Map<String, String> s2Map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            if (result.equals("satisfiable")) {
                if (s.startsWith("!")) {
                    String sDeleteFirst = s.substring(1);
                    String searchResult = s1Map.get(sDeleteFirst);
                    if (searchResult != null) {
                        result = sDeleteFirst;
                    } else {
                        s2Map.put(sDeleteFirst, "dummy");
                    }
                } else {
                    String searchResult = s2Map.get(s);
                    if (searchResult != null) {
                        result = s;
                        break;
                    } else {
                        s1Map.put(s, "dummy");
                    }
                }
            }
        }
        System.out.println(result);
    }
}
