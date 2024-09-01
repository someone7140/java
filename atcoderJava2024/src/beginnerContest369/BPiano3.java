package beginnerContest369;

import java.util.Arrays;
import java.util.Scanner;

public class BPiano3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int initialL = -1;
        int initialR = -1;
        int result = 0;

        for (int i = 0; i < n; i++) {
            var query = Arrays.stream(sc.nextLine().split(" ")).toArray(String[]::new);
            var tempSabun = 0;
            var kenban = Integer.parseInt(query[0]);

            if (query[1].equals("L")) {
                if (initialL > -1) {
                    tempSabun = Math.abs(initialL - kenban);
                }
                initialL = kenban;
            } else {
                if (initialR > -1) {
                    tempSabun = Math.abs(initialR - kenban);
                }
                initialR = kenban;
            }

            result = result + tempSabun;
        }
        System.out.println(result);
    }
}
