package beginnerContest173;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;

public class DChatinaCircle {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Long> inputList = new ArrayList<Long>();
        for(int i = 0; i < n ;i++) {
            inputList.add(sc.nextLong());
        }

        /*
        List<Integer> sortedList = inputList.stream().sorted(reverseOrder()).collect(Collectors.toList());
        for(int a: sortedList) {
            System.out.println(a);
        }
        */
        List<Long> sortedList = inputList.stream().sorted(reverseOrder()).collect(Collectors.toList());
        long result = sortedList.get(0);
        int index = 1;
        int count = 0;
        for (int i = 0; i < n - 2 ; i++) {
            result += sortedList.get(index);
            count = count + 1;
            if (count == 2) {
                count = 0;
                index = index + 1;
            }
        }
        System.out.println(result);
    }
}
