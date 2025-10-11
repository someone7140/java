package beginnerContest426;

import java.util.Scanner;

public class AOSVersions {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var xy = sc.nextLine().split(" ");
        var x = xy[0];
        var y = xy[1];

        if (x.equals("Ocelot")) {
            if (y.equals("Ocelot")) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } else if (x.equals("Serval")) {
            if (y.equals("Ocelot") || y.equals("Serval")) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } else {
            System.out.println("Yes");
        }
    }
}
