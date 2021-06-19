package mynavi2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ATinyArithmeticSequence {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a1 = sc.nextInt();
        int a2 = sc.nextInt();
        int a3 = sc.nextInt();
        List<Integer> aList = new ArrayList<>();
        aList.add(a1);
        aList.add(a2);
        aList.add(a3);
        Collections.sort(aList);

        if (aList.get(1) - aList.get(0) == aList.get(2) - aList.get(1)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
