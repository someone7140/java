package mynavi2021;

import java.util.*;

public class BDoyouknowthesecondhighestmountain {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<Integer, String> stMap = new HashMap<>();
        List<Integer> tList = new ArrayList<>();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String temp = sc.nextLine();
            String[] temps = temp.split(" ");
            int t = Integer.parseInt(temps[1]);
            stMap.put(t, temps[0]);
            tList.add(t);
        }
        Collections.sort(tList, Collections.reverseOrder());
        System.out.println(stMap.get(tList.get(1)));
    }
}
