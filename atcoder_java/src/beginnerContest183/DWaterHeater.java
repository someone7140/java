package beginnerContest183;

import java.util.Scanner;

public class DWaterHeater {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long w = sc.nextLong();

        long [] stArray = new long[200001];

        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            long p = sc.nextLong();
            stArray[s] = stArray[s] + p;
            stArray[t] = stArray[t] - p;
        }
        String result = "Yes";
        long pSum = 0;
        for (int i = 0; i < stArray.length; i++) {
            pSum = pSum + stArray[i];
            if (pSum > w) {
                result = "No";
                break;
            }
        }
        System.out.println(result);
    }
}
