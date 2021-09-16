package beginnerContest217;

import java.util.List;
import java.util.Scanner;

public class BAtCoderQuiz {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<String> sList = List.of("ABC", "ARC", "AGC", "AHC");
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        String s3 = sc.nextLine();
        String result = sList.stream().filter(s -> {
            return !s.equals(s1) && !s.equals(s2) && !s.equals(s3);
        }).findFirst().get();
        System.out.println(result);
    }
}
