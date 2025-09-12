package beginnerContest419;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BGetMin {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var q = sc.nextInt();
        sc.nextLine();
        var resultList = new ArrayList<String>();
        var numberList = new ArrayList<Integer>();

        for (int i = 0; i < q; i++) {
            var queries = sc.nextLine().split(" ");
            if(queries[0].equals("1")) {
                var num = Integer.parseInt(queries[1]);
                numberList.add(num);
                Collections.sort(numberList);
            } else {
                resultList.add(String.valueOf(numberList.get(0)));
                numberList.remove(0);
            }

        }
        System.out.println(String.join("\n", resultList));
    }
}
