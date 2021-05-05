package beginnerContest199;

import java.util.Scanner;

public class CIPFL {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sLength = 2 * n;
        sc.nextLine();
        String s = sc.nextLine();
        char[] sCharArray = s.toCharArray();
        String[] resultArray1 = new String[n];
        String[] resultArray2 = new String[n];
        boolean before1Flag = true;
        for (int i = 0; i < n; i++) {
            resultArray1[i] = String.valueOf(sCharArray[i]);
        }
        for (int i = 0; i < n; i++) {
            resultArray2[i] = String.valueOf(sCharArray[i + n]);
        }
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int t = sc.nextInt();
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            if (t == 2) {
                if (before1Flag) {
                    before1Flag = false;
                } else {
                    before1Flag = true;
                }
            }
            if (t == 1) {
                if (before1Flag) {
                    if (a <= n - 1 && b <= n - 1) {
                        String strA = resultArray1[a];
                        String strB = resultArray1[b];
                        resultArray1[a] = strB;
                        resultArray1[b] = strA;
                    } else if (a <= n - 1 && b > n - 1) {
                        String strA = resultArray1[a];
                        String strB = resultArray2[b - n];
                        resultArray1[a] = strB;
                        resultArray2[b - n] = strA;
                    } else {
                        String strA = resultArray2[a - n];
                        String strB = resultArray2[b - n];
                        resultArray2[a - n] = strB;
                        resultArray2[b - n] = strA;
                    }
                } else {
                    if (a <= n - 1 && b <= n - 1) {
                        String strA = resultArray2[a];
                        String strB = resultArray2[b];
                        resultArray2[a] = strB;
                        resultArray2[b] = strA;
                    } else if (a <= n - 1 && b > n - 1) {
                        String strA = resultArray2[a];
                        String strB = resultArray1[b - n];
                        resultArray2[a] = strB;
                        resultArray1[b - n] = strA;
                    } else {
                        String strA = resultArray1[a - n];
                        String strB = resultArray1[b - n];
                        resultArray1[a - n] = strB;
                        resultArray1[b - n] = strA;
                    }
                }
            }
        }
        if (before1Flag) {
            System.out.println(String.join("", resultArray1) + String.join("", resultArray2));
        } else {
            System.out.println(String.join("", resultArray2) + String.join("", resultArray1));
        }
    }

}
