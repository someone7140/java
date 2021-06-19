package mynavi2021;

import java.util.*;

public class CSecretNumber {
    static List<Integer> maruIndexList = new ArrayList<>();
    static List<Integer> batsuIndexList = new ArrayList<>();
    static List<Integer> hatenaIndexList = new ArrayList<>();
    static long result = 0;
    static int arieru = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] sChars = s.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            String temp = String.valueOf(sChars[i]);
            if (temp.equals("o")) {
                maruIndexList.add(i);
            } else if (temp.equals("x")) {
                batsuIndexList.add(i);
            } else {
                hatenaIndexList.add(i);
            }
        }
        boolean[] totta = new boolean[10];
        Arrays.fill(totta, false);
        arieru = maruIndexList.size() + batsuIndexList.size();
        dp(0, totta, 1);
        System.out.println(result);
    }

    static void dp(int resultIndex, boolean[] totta, long plus) {
        if (resultIndex == 4) {
            boolean plusFlag = true;
            for (int maruindex :  maruIndexList) {
                if(!totta[maruindex]) {
                    plusFlag = false;
                    break;
                }
            }
            if (plusFlag) {
                result = result + plus;
            }
        } else {
            for (int maruindex :  maruIndexList) {
                boolean[] tottaTemp = totta.clone();
                tottaTemp[maruindex] = true;
                dp(resultIndex + 1, tottaTemp, plus);
            }
            for (int hatenaindex :  hatenaIndexList) {
                boolean[] tottaTemp = totta.clone();
                tottaTemp[hatenaindex] = true;
                dp(resultIndex + 1, tottaTemp, plus);
            }
        }

    }
}
