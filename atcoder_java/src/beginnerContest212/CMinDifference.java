package beginnerContest212;

import java.util.*;
import java.util.stream.Collectors;

public class CMinDifference {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Map<Integer,String> nmMap = new HashMap<>();
        Set<Integer> nmSet = new HashSet<>();
        boolean sameFlag = false;
        for (int i = 0; i < n; i++) {
            int tempN = sc.nextInt();
            nmMap.put(tempN, "N");
            nmSet.add(tempN);
        }
        for (int i = 0; i < m; i++) {
            int tempM = sc.nextInt();
            String sameCheck = nmMap.get(tempM);
            if (sameCheck == null || sameCheck.equals("M")) {
                nmMap.put(tempM, "M");
            } else {
                sameFlag = true;
            }
            nmSet.add(tempM);
        }
        if (sameFlag) {
            System.out.println(0);
        } else {
            boolean initialFlag = true;
            boolean nFlag = true;
            int result = 2000000000;
            int temp = 2000000000;
            int previous = 2000000000;
            List<Integer> nmList= nmSet.stream().collect(Collectors.toList()).stream().sorted().collect(Collectors.toList());
            for (Integer nm : nmList) {
                String nmJudge = nmMap.get(nm);
                if (initialFlag) {
                    previous = nm;
                    if (nmJudge.equals("N")) {
                        nFlag = true;
                    } else {
                        nFlag = false;
                    }
                    initialFlag = false;
                } else {
                    if (nmJudge.equals("N") && nFlag) {
                        previous = nm;
                    } else if(nmJudge.equals("M") && !nFlag) {
                        previous = nm;
                    } else {
                        temp = Math.abs(previous - nm);
                        if (temp < result) {
                            result = temp;
                        }
                        previous = nm;
                    }
                    if (nmJudge.equals("N")) {
                        nFlag = true;
                    } else {
                        nFlag = false;
                    }
                }
            }
            System.out.println(result);
        }
    }
}
