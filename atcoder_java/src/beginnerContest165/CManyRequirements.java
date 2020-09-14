package beginnerContest165;

import java.util.*;

public class CManyRequirements {
    static int n;
    static int m;
    static int q;
    static int[] a;
    static int[] b;
    static int[] c;
    static int[] d;
    static List<Integer> ansList = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
        a = new int[q];
        b = new int[q];
        c = new int[q];
        d = new int[q];
        for(int i = 0; i < q; i++) {
            int[] abcd = new int[4];
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
            c[i] = sc.nextInt();
            d[i] = sc.nextInt();
        }
        ArrayList<Integer> A = new ArrayList<>();
        A.add(1);
        dfs(A);
        System.out.println(Collections.max(ansList));
    }

    private static void dfs(ArrayList<Integer> A) {
        if (A.size() == n + 1) {
            int now = 0;
            for(int i = 0; i < q; i++) {
                if (A.get(b[i]) - A.get(a[i]) == c[i]) {
                    now += d[i];
                }
            }
            ansList.add(now);
            return;
        }
        A.add(A.get(A.size() - 1));
        while (A.get(A.size() - 1) <= m) {
            dfs((ArrayList<Integer>)A.clone());
            A.set(A.size() - 1, A.get(A.size() - 1) + 1);
        }
    }

}
