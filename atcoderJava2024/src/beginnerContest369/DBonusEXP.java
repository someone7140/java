package beginnerContest369;

import java.util.Arrays;
import java.util.Scanner;

public class DBonusEXP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Long[] aArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Long.parseLong(a)).toArray(Long[]::new);

        long tempTaosuGuusuuSaidai = 0;
        long tempTaosuKisuuSaidai = 0;
        long tempNotTaosuGuusuuSaidai = 0;
        long tempNotTaosuKisuuSaidai = 0;
        long tempKisuuSaidai = 0;
        long tempGuusuuSaidai = 0;

        for (int i = 0; i < n; i++) {
            var a = aArray[i];
            if (i == 0) {
                tempTaosuKisuuSaidai = a;
            } else {
                // 現時点での最大
                tempKisuuSaidai = tempTaosuKisuuSaidai > tempNotTaosuKisuuSaidai ? tempTaosuKisuuSaidai : tempNotTaosuKisuuSaidai;
                tempGuusuuSaidai = tempTaosuGuusuuSaidai > tempNotTaosuGuusuuSaidai ? tempTaosuGuusuuSaidai : tempNotTaosuGuusuuSaidai;

                // 倒さないケース
                tempNotTaosuKisuuSaidai = tempKisuuSaidai;
                tempNotTaosuGuusuuSaidai = tempGuusuuSaidai;
                // 倒すケース
                // 今回が偶数回
                tempTaosuGuusuuSaidai = tempKisuuSaidai + a * 2;
                // 今回が奇数回
                // 倒すケース
                tempTaosuKisuuSaidai = tempGuusuuSaidai + a;

            }

            if (i == n - 1) {
                tempKisuuSaidai = tempTaosuKisuuSaidai > tempNotTaosuGuusuuSaidai ? tempTaosuKisuuSaidai : tempNotTaosuGuusuuSaidai;
                tempGuusuuSaidai = tempTaosuGuusuuSaidai > tempNotTaosuKisuuSaidai ? tempTaosuGuusuuSaidai : tempNotTaosuKisuuSaidai;
            }
        }

        System.out.println(tempKisuuSaidai > tempGuusuuSaidai ? tempKisuuSaidai : tempGuusuuSaidai);
    }
}
