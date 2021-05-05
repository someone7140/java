package experiment;

import java.util.Arrays;
import java.util.Collections;

public class MaxTest {

    public static void main(String[] args){
        int test1 = 3;
        int test2 = 4;
        int test3 = 5;
        System.out.println(getMax(test1, test2, test3));
    }

    private static int getMax(Integer... vals) {
        return Collections.max(Arrays.asList(vals));
    }
}
