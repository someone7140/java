package beginnerContest205;

import java.util.Scanner;

public class CPOW {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();
        String result = "";
        long zetttaiA = Math.abs(a);
        long zetttaiB = Math.abs(b);
        boolean aMinusFlag = a < 0;
        boolean bMinusFlag = b < 0;
        boolean cGuusuu = c % 2 == 0;
        if (cGuusuu) {
            if (zetttaiA == zetttaiB) {
                result = "=";
            } else if (zetttaiA > zetttaiB) {
                result = ">";
            } else {
                result = "<";
            }
        } else {
            if (!aMinusFlag && !bMinusFlag) {
                if (zetttaiA == zetttaiB) {
                    result = "=";
                } else if (zetttaiA > zetttaiB) {
                    result = ">";
                } else {
                    result = "<";
                }
            } else if (aMinusFlag && bMinusFlag) {
                if (zetttaiA == zetttaiB) {
                    result = "=";
                } else if (zetttaiA > zetttaiB) {
                    result = "<";
                } else {
                    result = ">";
                }
            } else {
                if (aMinusFlag && !bMinusFlag) {
                    result = "<";
                }
                if (!aMinusFlag && bMinusFlag) {
                    result = ">";
                }
            }
        }

        System.out.println(result);
    }
}
