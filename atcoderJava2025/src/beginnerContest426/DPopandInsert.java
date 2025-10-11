package beginnerContest426;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DPopandInsert {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();
        var resultList = new ArrayList<String>();

        for (int i = 0; i < n; i++) {
            var t = Integer.parseInt(sc.nextLine());
            var sArray = sc.nextLine().split("");

            var zeroRenzokuMax = 0;
            var oneRenzokuMax = 0;
            var zeroRenzokuTemp = 0;
            var oneRenzokuTemp = 0;
            var zeroCount = 0;
            var oneCount = 0;

            for (int j = 0; j < t; j++) {
                if (sArray[j].equals("0")) {
                    if (zeroRenzokuTemp == 0) {
                        if (oneRenzokuTemp > oneRenzokuMax) {
                            oneRenzokuMax = oneRenzokuTemp;
                        }
                        oneRenzokuTemp = 0;
                        zeroRenzokuTemp = 1;
                    } else {
                        zeroRenzokuTemp = zeroRenzokuTemp + 1;
                    }
                    zeroCount = zeroCount + 1;
                } else {
                    if (oneRenzokuTemp == 0) {
                        if (zeroRenzokuTemp > zeroRenzokuMax) {
                            zeroRenzokuMax = zeroRenzokuTemp;
                        }
                        zeroRenzokuTemp = 0;
                        oneRenzokuTemp = 1;
                    } else {
                        oneRenzokuTemp = oneRenzokuTemp + 1;
                    }
                    oneCount = oneCount + 1;
                }
            }
            if (zeroRenzokuTemp > zeroRenzokuMax) {
                zeroRenzokuMax = zeroRenzokuTemp;
            }
            if (oneRenzokuTemp > oneRenzokuMax) {
                oneRenzokuMax = oneRenzokuTemp;
            }

            if (zeroCount == 0 || oneCount == 0) {
                resultList.add("0");
            } else {
                var tempResult1 = (zeroCount - zeroRenzokuMax) * 2 + oneCount;
                var tempResult2 = (oneCount - oneRenzokuMax) * 2 + zeroCount;
                if (tempResult1 > tempResult2) {
                    resultList.add(String.valueOf(tempResult2));
                } else {
                    resultList.add(String.valueOf(tempResult1));
                }
            }
        }

        System.out.println(String.join("\n", resultList));
    }
}
