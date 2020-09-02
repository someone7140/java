package beginnerContest134;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DPreparingBoxes {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long a[] = new long[n];
        long b[] = new long[n];
        for (int i = 0; i <= n - 1; i++) {
            a[i] = sc.nextLong();
        }

        List<Long> resultList = new ArrayList();
        boolean minusFlg = false;
        for(int i = n - 1; i>=0; i--) {
            long temp = i + 1;
            int cnt = 0;
            int step = i+1;
            for(int j=i+step; j<n; j+=step) {
                if(b[j]==1) cnt++;
            }

            if (a[i] == 0) {
                if (cnt % 2L != 0l) {
                    b[i]=1;
                    resultList.add(temp);
                };
            } else {
                if (cnt % 2L == 0l) {
                    b[i]=1;
                    resultList.add(temp);
                };
            }
        }
        if(minusFlg) {
            System.out.print(-1);
        } else if (resultList.isEmpty()) {
            System.out.print(0);
        } else {
            System.out.println(resultList.size());
            resultList.stream().sorted().forEach(r-> System.out.print(r + " "));
        }
    }
}
