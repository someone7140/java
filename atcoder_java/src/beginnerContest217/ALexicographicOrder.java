package beginnerContest217;

import java.util.Scanner;

public class ALexicographicOrder {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        String s = st.split(" ")[0];
        String t = st.split(" ")[1];
        int result = s.compareTo(t);
        if (result < 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
