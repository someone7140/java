package beginnerContest173;

import java.util.*;

public class CHandV {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int k = sc.nextInt();

        List cList = new ArrayList<char[]>();
        int result = 0;

        for(int i =0; i < h; i++) {
            String c = sc.next();
            cList.add(c.toCharArray());
        }

        for (int i = 0; i < (1<<h); i++) {
            for (int j = 0; j < (1<<w); j++) {
                int count = 0;
                for(int i2 = 0; i2 < h; i2++) {
                    for (int j2 = 0; j2 < w; j2++) {
                        if((i>>i2 & 1) == 1) {
                            continue;
                        }
                        if((j>>j2 & 1) == 1) {
                            continue;
                        }
                        char[] ca = (char[])cList.get(i2);
                        if(String.valueOf(ca[j2]).equals("#")) {
                            count++;
                        }
                    }
                }
                if(count == k) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
