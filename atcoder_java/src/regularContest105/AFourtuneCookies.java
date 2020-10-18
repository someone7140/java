package regularContest105;

import java.util.Scanner;

public class AFourtuneCookies {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long abcd[] = new long[4];
        abcd[0] = sc.nextLong();
        abcd[1] = sc.nextLong();
        abcd[2] = sc.nextLong();
        abcd[3] = sc.nextLong();
        String result = "No";
        long allSum = abcd[0] + abcd[1] + abcd[2] + abcd[3];

        for (int i = 0; i < 4; i++) {
            // 2個のケース
            long eatSumInitial = abcd[i];
            for (int j = i + 1; j < 4; j ++) {
                long eatSumSecond = eatSumInitial;
                eatSumSecond += abcd[j];
                if (eatSumSecond == (allSum - eatSumSecond)) {
                    result = "Yes";
                    break;
                }
                // 3個のケース
                for (int l = j + 1; l < 4; l++) {
                    long eatSumThird = eatSumSecond;
                    eatSumThird += abcd[ l];
                    if (eatSumThird == (allSum - eatSumThird)) {
                        result = "Yes";
                        break;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
