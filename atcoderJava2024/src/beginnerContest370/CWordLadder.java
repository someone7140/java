package beginnerContest370;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CWordLadder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var sArray = Arrays.stream(sc.nextLine().split("")).toArray(String[]::new);
        var tArray = Arrays.stream(sc.nextLine().split("")).toArray(String[]::new);
        var len = sArray.length;
        var tempArray = sArray.clone();
        var resultList = new ArrayList<String>();
        var resultCount = 0;

        // アルファベット順で大きいものを小さいものにする
        for (int i = 0; i < len; i++) {
            int comparedResult = sArray[i].compareTo(tArray[i]);
            if (comparedResult > 0) {
                tempArray[i] = tArray[i];
                resultList.add(String.join("", tempArray));
                resultCount = resultCount + 1;
            }
        }
        // アルファベット順で小さいものを大きいものにする
        for (int i = len - 1; i >= 0; i--) {
            int comparedResult = sArray[i].compareTo(tArray[i]);
            if (comparedResult < 0) {
                tempArray[i] = tArray[i];
                resultList.add(String.join("", tempArray));
                resultCount = resultCount + 1;
            }
        }

        System.out.println(resultCount);
        if (resultCount > 0) {
            System.out.println(String.join("\n", resultList));
        }

    }
}
