package beginnerContest191;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CDigitalGraffiti {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        sc.nextLine();
        String[][] sArray = new String[h][w];
        for (int i = 0; i < h; i++) {
            String input = sc.nextLine();
            char[] inputArray = input.toCharArray();
            for(int j = 0; j < w; j++) {
               String s  = String.valueOf(inputArray[j]);
               sArray[i][j] = s;
            }
        }
        int result = 0;
        int beforeStartW = -1;
        int beforeEndW = -1;
        int startW = -1;
        int endW = -1;
        for (int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if (sArray[i][j].equals("#")) {
                    if (sArray[i][j - 1].equals(".")) {
                        startW = j - 1;
                    }
                    if (sArray[i][j + 1].equals(".")) {
                        endW = j + 1;
                        if (beforeStartW == -1) {
                            result = result + 2;

                        } else {
                            if (beforeStartW != startW) {
                                result++;
                            }
                            if (beforeEndW != endW) {
                                result++;
                            }
                        }
                        beforeStartW = startW;
                        beforeEndW = endW;
                    }
                }
            }
        }
        int beforeStartH = -1;
        int beforeEndH = -1;
        int startH = -1;
        int endH = -1;
        Set<Integer> targetHSet = new HashSet<>();
        for (int j = 0; j < w; j++) {
            for(int i = 0; i < h; i++) {
                if (sArray[i][j].equals("#")) {
                    if (sArray[i - 1][j].equals(".")) {
                        startH = i - 1;
                    }
                    if (sArray[i + 1][j].equals(".")) {
                        endH = i + 1;
                        if (beforeStartH == -1) {
                            result = result + 2;
                        } else {
                            if (beforeStartH != startH) {
                                result++;
                            }
                            if (beforeEndH != endH) {
                                result++;
                            }
                        }
                        beforeStartH = startH;
                        beforeEndH = endH;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
