package beginnerContest222;

import java.util.Scanner;

public class BFailingGrade {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int result = 0;

        for (int i = 0; i < n; i++) {
            int temp = sc.nextInt();
            if (temp < p) {
                result++;
            }
        }
        System.out.println(result);
    }
}
