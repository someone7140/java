package beginnerContest207;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ARepression {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<Integer> abcList = new ArrayList<>();
        abcList.add(sc.nextInt());
        abcList.add(sc.nextInt());
        abcList.add(sc.nextInt());
        abcList = abcList.stream().sorted().collect(Collectors.toList());
        System.out.println(abcList.get(2) + abcList.get(1));
    }
}
