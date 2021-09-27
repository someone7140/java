package beginnerContest220;

import java.util.Scanner;

public class AFindMultiple {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int i = 1;
        int result = -1;

        while(true) {
            int temp = i * c;
            if (temp >= a && temp <= b) {
                result = temp;
                break;
            } else if(temp > b) {
                break;
            }
            i++;
        }
        System.out.println(result);
    }
}
