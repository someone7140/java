package unicorn2021;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class DPlayTrain {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] nArray = new int[n];
        HashMap<Integer, Deque<Integer>> dequeMap = new HashMap<>();

        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            int command = sc.nextInt();
            if (command == 1) {
                int before = sc.nextInt();
                int after = sc.nextInt();
                if (nArray[before - 1] == 0 && nArray[after - 1] == 0) {
                    Deque outputQue = new ArrayDeque<>();
                    outputQue.addFirst(after);
                    outputQue.addFirst(before);
                    int index = dequeMap.size() + 1;
                    dequeMap.put(index, outputQue);
                    nArray[before - 1] = index;
                    nArray[after - 1] = index;
                } else {
                    if (nArray[before - 1] != 0 && nArray[after - 1] != 0) {
                        Deque<Integer> beforeQue = dequeMap.get(nArray[before - 1]);
                        Deque<Integer> afterQue = dequeMap.get(nArray[after - 1]);
                        while (afterQue.size() != 0) {
                            int toridashi = afterQue.pop();
                            beforeQue.addLast(toridashi);
                            nArray[toridashi - 1] = nArray[before - 1];
                        }
                        dequeMap.put(nArray[before - 1], beforeQue);
                    } else if (nArray[before - 1] != 0) {
                        Deque<Integer> outputQue = dequeMap.get(nArray[before - 1]);
                        outputQue.addLast(after);
                        dequeMap.put(nArray[before - 1], outputQue);
                        nArray[after - 1] = nArray[before - 1];
                    } else {
                        Deque<Integer> outputQue = dequeMap.get(nArray[after - 1]);
                        outputQue.addFirst(before);
                        dequeMap.put(nArray[after - 1], outputQue);
                        nArray[before - 1] = nArray[after - 1];
                    }

                }
            } else if (command == 2) {
                int before = sc.nextInt();
                int after = sc.nextInt();
                Deque<Integer> outputQue = dequeMap.get(nArray[before - 1]);
                boolean roopFlag = true;
                Deque<Integer> newOutputQue = new ArrayDeque<>();
                while (roopFlag) {
                    int toridashi = outputQue.pop();
                    newOutputQue.addLast(toridashi);
                    if (toridashi == before) {
                        break;
                    }
                }
                if (outputQue.size() == 1) {
                    nArray[after - 1] = 0;
                }
                if (newOutputQue.size() > 1) {
                    int index = dequeMap.size() + 1;
                    dequeMap.put(index, newOutputQue);
                    newOutputQue.iterator().forEachRemaining(s -> {
                        int arrrayIndex = s - 1;
                        nArray[arrrayIndex] = index;
                    });
                } else {
                    nArray[before - 1] = 0;
                }
            } else {
                int x = sc.nextInt();
                if (nArray[x - 1] == 0) {
                    resultList.add(1 + " " + x);
                } else {
                    Deque<Integer> outputQue = dequeMap.get(nArray[x - 1]);
                    AtomicReference<String> result = new AtomicReference<>("");
                    outputQue.iterator().forEachRemaining(s -> {
                        result.set(result.get() + s + " ");
                    });
                    resultList.add(outputQue.size() + " " + result.get().trim());
                }
            }
        }
        resultList.stream().forEach(r -> {
            System.out.println(r);
        });

    }
}
