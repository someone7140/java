package beginnerContest356;

import java.util.*;
import java.util.stream.Collectors;

public class CKeys {
     static List<List<Integer>> combination(List list, int count) {
        List ret = new ArrayList<List<String>>();
        for (int i = 0; i < list.size(); i++) {
            if (i + count > list.size()) {
                break;
            }
            Stack stack = new Stack<Integer>();
            stack.push(list.get(i));
            _combination(ret, list, stack, i + 1, count);
        }
        return ret;
    }

     static void _combination(List ret, List list, Stack stack, int index, int count) {
        for (int i = index; i < list.size(); i++) {
            stack.push(list.get(i));
            if (stack.size() == count) {
                ret.add(Arrays.asList(stack.toArray()));
                stack.pop();
                continue;
            }
            _combination(ret, list, stack, i + 1, count);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();

        record CKeysARecord(List<List<Integer>> combinations, String keyResult) { }
        int pattern = (int)Math.pow(2, n);
        List<CKeysARecord> keysARecord = new ArrayList();

        for (int i = 0; i < m; i++) {
            String[] cStrs = sc.nextLine().split(" ");
            int len = cStrs.length;
            String[] aStr = Arrays.copyOfRange(cStrs, 1, len - 1);
            String keyResult = cStrs[len - 1];
            List<List<Integer>> combinations = combination(
                    Arrays.stream(aStr).map(a -> Integer.parseInt(a) - 1).collect(Collectors.toList())
                    ,k);
            CKeysARecord record = new CKeysARecord(combinations, keyResult);
            keysARecord.add(record);
        }
        int result = 0;
        for (int i = 0; i < pattern; i++) {
            String bitStr = Integer.toBinaryString(i);
            String[] keyStr = new String[n];
            int temp = 0;
            StringBuilder bitStrSb = new StringBuilder(bitStr);
            for (char bit : bitStrSb.reverse().toString().toCharArray()) {
                if (String.valueOf(bit).equals("1")) {
                    keyStr[temp] = "1";
                }
                temp++;
            }

            boolean tempResult = true;
            for (CKeysARecord record : keysARecord) {
                String keyResult = record.keyResult;
                List<List<Integer>> combinations = record.combinations;
                boolean openFlag = false;
                for (List<Integer> combination : combinations) {
                    boolean keyResult2 = false;
                    for (Integer c : combination) {
                        if (keyResult.equals("o")) {
                            if ("1".equals(keyStr[c])) {
                                keyResult2 = true;
                                openFlag = true;
                                break;
                            }
                        } else {
                            if (!"1".equals(keyStr[c])) {
                                keyResult2 = true;
                                openFlag = true;
                                break;
                            }
                        }
                    }

                    if (keyResult2) {
                        break;
                    }

                }

                if (!openFlag) {
                    tempResult = false;
                }
                if (!tempResult) {
                    break;
                }
            }

            if (tempResult) {
                result = result + 1;
            }
        }
        System.out.println(result);
    }
}
