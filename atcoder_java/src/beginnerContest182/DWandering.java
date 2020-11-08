package beginnerContest182;

import java.util.Scanner;

public class DWandering {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long sum = 0;
        long ruisekiWa = 0;
        long ruisekiWaMax = 0;
        long result = 0;
        for(int i = 0; i < n; i++) {
            long a = sc.nextLong();
            ruisekiWa = ruisekiWa + a;
            if(ruisekiWa > ruisekiWaMax) {
                ruisekiWaMax = ruisekiWa;
            }
            long tempResult = sum + ruisekiWaMax;
            if (result < tempResult) {
                result = tempResult;
            }
            sum = sum + ruisekiWa;
        }
        System.out.println(result);
    }

}
