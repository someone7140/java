package beginnerContest221;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Btypo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();

        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        List<Integer> differentIndex = new ArrayList<>();
        List<String> sDifferent = new ArrayList<>();
        List<String> tDifferent = new ArrayList<>();

        for(int i = 0; i < s.length(); i++) {
            String sTemp = String.valueOf(sCharArray[i]);
            String tTemp = String.valueOf(tCharArray[i]);

            if(!sTemp.equals(tTemp)) {
                sDifferent.add(sTemp);
                tDifferent.add(tTemp);
                differentIndex.add(i);
            }
        }

        if (differentIndex.size() == 0) {
            System.out.println("Yes");
        } else if (differentIndex.size() == 2 &&
                differentIndex.get(1) - differentIndex.get(0) == 1) {
            if (sDifferent.get(1).equals(tDifferent.get(0)) &&
                    sDifferent.get(0).equals(tDifferent.get(1))) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } else {
            System.out.println("No");
        }
    }
}
