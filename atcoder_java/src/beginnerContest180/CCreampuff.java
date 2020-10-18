package beginnerContest180;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CCreampuff {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        Set<Long> resultSet = new HashSet<>();
        for (long i = 1; i * i <= n; i++) {
            if(n % i == 0) {
                resultSet.add(i);
                if (i * i != n) {
                    resultSet.add(n / i);
                }
            }
        }
        resultSet.stream().sorted().forEach(r ->
            System.out.println(r)
        );
    }
}
