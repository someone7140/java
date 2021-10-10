package beginnerContest221;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSelectMul {
    static long result = -9999999999L;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nStr = sc.nextLine();
        char[] nCharArray = nStr.toCharArray();
        List<String> nList = new ArrayList<>();
        for (int i = 0; i < nCharArray.length; i++) {
            nList.add(String.valueOf(nCharArray[i]));
        }
        calc("", nList);
        System.out.println(result);
    }

    private static void calc(String nInput, List<String> nListTemp) {
        if (nListTemp.size() > 1) {
            for (int i = 0; i < nListTemp.size(); i++) {
                String nTemp = nInput + nListTemp.get(i);
                if (nTemp.length() > 0 && !nTemp.startsWith("0")) {
                    List nCopy = new ArrayList<>(nListTemp);
                    nCopy.remove(i);
                    String copyStr = String.join("", nCopy);
                    if (!copyStr.startsWith("0")) {
                        Long temp = Long.parseLong(
                                Stream.of(nTemp.split(""))
                                        .sorted(Comparator.reverseOrder()) // 降順にソート
                                        .collect(Collectors.joining())
                        ) * Long.parseLong(
                                Stream.of(copyStr.split(""))
                                        .sorted(Comparator.reverseOrder()) // 降順にソート
                                        .collect(Collectors.joining())
                        );
                        if (temp > result) {
                            result = temp;
                        }
                    }
                    calc(nTemp, nCopy);
                }
            }
        }
    }
}
