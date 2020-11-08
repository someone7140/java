package beginnerContest181;

import java.util.*;

public class CCollinearity {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double VERTICAL = 999999999;
        int n = sc.nextInt();
        int xyArray[][] = new int[n][2];
        for (int i = 0; i < n; i++) {
            xyArray[i][0] = sc.nextInt();
            xyArray[i][1] = sc.nextInt();
        }

        boolean findFlag = false;
        for (int i = 0; i < n; i++) {
            List<Double> katamukiList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    int downX = xyArray[j][0] - xyArray[i][0];
                    int downY = xyArray[j][1] - xyArray[i][1];
                    if (downX == 0) {
                        double katamuki = VERTICAL;
                        Optional<Double> find = katamukiList.stream().filter(k -> k == katamuki).findAny();
                        if (find.isPresent()) {
                            findFlag = true;
                            break;
                        } else {
                            katamukiList.add(katamuki);
                        }
                    } else {
                        double katamuki = (double)downY / (double)downX;
                        Optional<Double> find = katamukiList.stream().filter(k -> k == katamuki).findAny();
                        if (find.isPresent()) {
                            findFlag = true;
                            break;
                        } else {
                            katamukiList.add(katamuki);
                        }
                    }
                }

            }

            if (findFlag) {
                break;
            }
        }

        if (findFlag) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
