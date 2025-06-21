package beginnerContest411;

import java.util.*;
import java.util.stream.Collectors;

public class DConflict2 {

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var q = sc.nextInt();
        sc.nextLine();

        var mojiList = new ArrayList<String>();
        var mojiLen = 0;
        List<Integer>[] pcMojiIndexList = new ArrayList[n];
        Integer serverPcIndex = null;
        Integer serverMaxIndex = null;

        for (int i = 0; i < q; i++) {
            var queryArray = sc.nextLine().split(" ");

            if (queryArray[0].equals("1")) {
                var pcIndex = Integer.parseInt(queryArray[1]) - 1;
                if (serverPcIndex == null) {
                    pcMojiIndexList[pcIndex] = null;
                } else {
                    pcMojiIndexList[pcIndex] = new ArrayList<>(pcMojiIndexList[serverPcIndex]);
                }
            }

            if (queryArray[0].equals("2")) {
                var pcIndex = Integer.parseInt(queryArray[1]) - 1;
                var addMoji = queryArray[2];
                mojiList.add(addMoji);

                if (pcMojiIndexList[pcIndex] == null) {
                    var newList = new ArrayList<Integer>();
                    newList.add(mojiLen);
                    pcMojiIndexList[pcIndex] = newList;
                } else {
                    var mojiListPc = pcMojiIndexList[pcIndex];
                    mojiListPc.add(mojiLen);
                    pcMojiIndexList[pcIndex] = mojiListPc;
                }
                mojiLen = mojiLen + 1;
            }

            if (queryArray[0].equals("3")) {
                var pcIndex = Integer.parseInt(queryArray[1]) - 1;
                serverPcIndex = pcIndex;
                if (pcMojiIndexList[pcIndex] != null) {
                    serverMaxIndex = pcMojiIndexList[pcIndex].size();
                }
            }
        }

        if (serverPcIndex != null && serverMaxIndex != null && pcMojiIndexList[serverPcIndex] != null) {
            var result = "";
            var count = 0;
            for (int mojiIndex : pcMojiIndexList[serverPcIndex]) {
                result = result + mojiList.get(mojiIndex);
                count ++;
                if (count == serverMaxIndex) {
                    break;
                }
            }
            System.out.println(result);
        }
    }
}
