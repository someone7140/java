package unicorn2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BStarorNot {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> kouhoList = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (i == 0) {
                kouhoList.add(a);
                kouhoList.add(b);
            } else {
                kouhoList = kouhoList.stream().filter(k -> k == a || k == b).collect(Collectors.toList());
            }
        }
        if (kouhoList.size() == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
}
