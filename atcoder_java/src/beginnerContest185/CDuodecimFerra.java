package beginnerContest185;

import java.util.Scanner;

public class CDuodecimFerra {
    static long result = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        divide(l, 12);
        System.out.println(result);

    }

    private static void divide(int length, int remain) {
        // lengthとremainが同数
        if (length == remain || remain == 1) {
            System.out.println("length:" + length + "、remain:" + remain);
            result++;
        } else {
            // 切る
            divide(length - 1, remain - 1);
            // 切らない
            divide(length - 1, remain);
        }

    }
}
