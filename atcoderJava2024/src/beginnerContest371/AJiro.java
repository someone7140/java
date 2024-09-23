package beginnerContest371;

import java.util.Arrays;
import java.util.Scanner;

public class AJiro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var sArray = Arrays.stream(sc.nextLine().split(" ")).toArray(String[]::new);

        if (sArray[0].equals("<") && sArray[1].equals("<") && sArray[2].equals("<")) {
            System.out.println("B");
        } else if (sArray[0].equals("<") && sArray[1].equals("<") && sArray[2].equals(">")) {
            System.out.println("C");
        } else if (sArray[0].equals("<") && sArray[1].equals(">") && sArray[2].equals(">")) {
            System.out.println("A");
        } else if (sArray[0].equals(">") && sArray[1].equals("<") && sArray[2].equals("<")) {
            System.out.println("A");
        } else if (sArray[0].equals(">") && sArray[1].equals(">") && sArray[2].equals("<")) {
            System.out.println("C");
        } else {
            System.out.println("B");
        }
    }
}
