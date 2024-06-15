package beginnerContest357;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class D88888888 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long n = sc.nextLong();
        long wari = 998244353;
        long result = 0;

        if (n < 10) {
            String strN = n.toString();
            String nKetugou = "";
            for (int i = 0; i < n; i++) {
                nKetugou = nKetugou + strN;
            }
            System.out.println(Long.parseLong(nKetugou) % wari);
            return;
        }

        System.out.println("temp");
    }
}
