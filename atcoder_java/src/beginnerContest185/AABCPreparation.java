package beginnerContest185;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AABCPreparation {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a1 = sc.nextInt();
        int a2 = sc.nextInt();
        int a3 = sc.nextInt();
        int a4 = sc.nextInt();

        List<Integer> list = Arrays.asList(a1, a2, a3,a4);
        System.out.println(list.stream().min(Integer::compareTo).get());
    }
}
