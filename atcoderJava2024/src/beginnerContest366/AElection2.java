package beginnerContest366;

import java.util.Scanner;

public class AElection2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int a = sc.nextInt();
        int zan = n - (t + a);
        if (t > a) {
            var newA = a + zan;
            if (t > newA) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } else if (a > t){
            var newT = t + zan;
            if (a > newT) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } else {
            System.out.println("No");
        }
    }
}
