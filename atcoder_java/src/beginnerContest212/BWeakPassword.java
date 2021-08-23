package beginnerContest212;

import java.util.Scanner;

public class BWeakPassword {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        char[] xChar = x.toCharArray();
        int x1 = 0;
        int x2 = 0;
        int x3 = 0;
        int x4 = 0;
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                x1 = Integer.parseInt(String.valueOf(xChar[i]));
            }
            if (i == 1) {
                x2 = Integer.parseInt(String.valueOf(xChar[i]));
            }
            if (i == 2) {
                x3 = Integer.parseInt(String.valueOf(xChar[i]));
            }
            if (i == 3) {
                x4 = Integer.parseInt(String.valueOf(xChar[i]));
            }
        }

        if (x1 == x2 && x2 == x3 && x3 == x4) {
            System.out.println("Weak");
        } else {
            int prebiousX = -1;
            for (int i = 0; i < 4; i++) {
                int tempX = Integer.parseInt(String.valueOf(xChar[i]));
                if (i == 0) {
                    prebiousX = tempX;
                } else {
                    if (prebiousX == 9 && tempX == 0) {
                        // 何もしない
                    } else {
                        if ((prebiousX + 1) == tempX) {
                            // 何もしない
                        } else {
                            System.out.println("Strong");
                            return;
                        }
                    }
                    prebiousX = tempX;
                }
            }
            System.out.println("Weak");
        }
    }
}
