package beginnerContest211;

import java.util.*;

public class Cchokudai {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] sCharArray = s.toCharArray();
        long result = 0;
        long tempResult = 0;
        Map<Integer, List<Integer>> cMap = new HashMap<>();
        Map<Integer, List<Integer>> hMap = new HashMap<>();
        Map<Integer, List<Integer>> oMap = new HashMap<>();
        Map<Integer, List<Integer>> kMap = new HashMap<>();
        Map<Integer, List<Integer>> uMap = new HashMap<>();
        Map<Integer, List<Integer>> dMap = new HashMap<>();
        Map<Integer, List<Integer>> aMap = new HashMap<>();
        for (int i= 0; i < s.length(); i++) {
            String sString = String.valueOf(sCharArray[i]);
            if (sString.equals("c")) {
                cMap.put(i, new ArrayList<>());
            } else if (sString.equals("h")) {
                hMap.put(i, new ArrayList<>());
                for (Map.Entry<Integer, List<Integer>> entry : cMap.entrySet()) {
                    if (entry.getKey() < i) {
                        List<Integer> cList = cMap.get(entry.getKey());
                        cList.add(i);
                        cMap.put(entry.getKey(), cList);
                    }
                }
            } else if (sString.equals("o")) {
                oMap.put(i, new ArrayList<>());
                for (Map.Entry<Integer, List<Integer>> entry : hMap.entrySet()) {
                    if (entry.getKey() < i) {
                        List<Integer> hList = hMap.get(entry.getKey());
                        hList.add(i);
                        hMap.put(entry.getKey(), hList);
                    }
                }
            } else if (sString.equals("k")) {
                kMap.put(i, new ArrayList<>());
                for (Map.Entry<Integer, List<Integer>> entry : oMap.entrySet()) {
                    if (entry.getKey() < i) {
                        List<Integer> oList = oMap.get(entry.getKey());
                        oList.add(i);
                        oMap.put(entry.getKey(), oList);
                    }
                }
            } else if (sString.equals("u")) {
                uMap.put(i, new ArrayList<>());
                for (Map.Entry<Integer, List<Integer>> entry : kMap.entrySet()) {
                    if (entry.getKey() < i) {
                        List<Integer> kList = kMap.get(entry.getKey());
                        kList.add(i);
                        kMap.put(entry.getKey(), kList);
                    }
                }
            } else if (sString.equals("d")) {
                dMap.put(i, new ArrayList<>());
                for (Map.Entry<Integer, List<Integer>> entry : uMap.entrySet()) {
                    if (entry.getKey() < i) {
                        List<Integer> uList = uMap.get(entry.getKey());
                        uList.add(i);
                        uMap.put(entry.getKey(), uList);
                    }
                }
            } else if (sString.equals("a")) {
                aMap.put(i, new ArrayList<>());
                for (Map.Entry<Integer, List<Integer>> entry : dMap.entrySet()) {
                    if (entry.getKey() < i) {
                        List<Integer> dList = dMap.get(entry.getKey());
                        dList.add(i);
                        dMap.put(entry.getKey(), dList);
                    }
                }
            } else if (sString.equals("i")) {
                for (Map.Entry<Integer, List<Integer>> entry : aMap.entrySet()) {
                    if (entry.getKey() < i) {
                        List<Integer> aList = aMap.get(entry.getKey());
                        aList.add(i);
                        aMap.put(entry.getKey(), aList);
                    }
                }
            }
        }
        long wari = 1000000007;
        for  (Map.Entry<Integer, List<Integer>> entry : cMap.entrySet()) {
            if (entry.getValue().size() > 0) {
                for (Integer hIndex : entry.getValue()) {
                    List<Integer> oIndexes = hMap.get(hIndex);
                    if (oIndexes != null) {
                        for (Integer oIndex : oIndexes) {
                            List<Integer> kIndexes = oMap.get(oIndex);
                            if (kIndexes != null) {
                                for (Integer kIndex : kIndexes) {
                                    List<Integer> uIndexes = kMap.get(kIndex);
                                    if (uIndexes != null) {
                                        for (Integer uIndex : uIndexes) {
                                            List<Integer> dIndexes = uMap.get(uIndex);
                                            if (dIndexes != null) {
                                                for (Integer dIndex : dIndexes) {
                                                    List<Integer> aIndexes = dMap.get(dIndex);
                                                    if (aIndexes != null) {
                                                        for (Integer aIndex : aIndexes) {
                                                            List<Integer> iIndexes = aMap.get(aIndex);
                                                            result = (result + iIndexes.size()) % wari;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }
}

