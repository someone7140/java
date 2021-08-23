package beginnerContest211;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BCycleHit {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<String> sList = new ArrayList<>();
        sList.add(sc.nextLine());
        sList.add(sc.nextLine());
        sList.add(sc.nextLine());
        sList.add(sc.nextLine());
        boolean h = sList.stream().anyMatch(s -> s.equals("H"));
        boolean twoB = sList.stream().anyMatch(s -> s.equals("2B"));
        boolean threeB = sList.stream().anyMatch(s -> s.equals("3B"));
        boolean hr = sList.stream().anyMatch(s -> s.equals("HR"));
        if (h && twoB && threeB && hr) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

