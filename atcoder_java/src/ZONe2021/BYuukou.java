package ZONe2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BYuukou {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double d = sc.nextDouble();
        double h = sc.nextDouble();
        List<double[]> katamukiResult = new ArrayList<>();
        double[] zeroKatamuki = {d, h, h/d};
        katamukiResult.add(zeroKatamuki);
        double result = 0;
        for (int i = 0; i < n; i++) {
            double nd = sc.nextDouble();
            double nh = sc.nextDouble();
            double[] nKatamuki = {nd, nh, (h - nh)/(d - nd)};
            katamukiResult.add(nKatamuki);
        }
        double minKatamukiValue = 99999999999999999999999d;
        double[] minKatamuki = {0, 0, minKatamukiValue};

        for (int i = 0; i <= n; i++) {
            double[] tempKatamuki = katamukiResult.get(i);
            if (tempKatamuki[2] < minKatamukiValue) {
                minKatamukiValue = tempKatamuki[2];
                minKatamuki = tempKatamuki;
            }
        }
        if (minKatamuki[0] == 0 && minKatamuki[1] == 0) {
            result = minKatamuki[2];
        } else {
            double zeroH = minKatamuki[0] * minKatamuki[2];
            result = minKatamuki[1] - zeroH;
        }

        System.out.println(result);
    }
}
