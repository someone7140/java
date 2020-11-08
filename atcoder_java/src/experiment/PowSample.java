package experiment;

import java.math.BigInteger;

public class PowSample {

    public static void main(String[] args){
        System.out.println("【5の10乗】");
        displayRuijou(5, 10);
        System.out.println("【5の20乗】");
        displayRuijou(5, 20);
        System.out.println("【5の25乗】");
        displayRuijou(5, 25);
        System.out.println("【3の10乗】");
        displayRuijou(3, 10);
        System.out.println("【3の20乗】");
        displayRuijou(3, 20);
        System.out.println("【3の30乗】");
        displayRuijou(3, 30);
        System.out.println("【3の33乗】");
        displayRuijou(3, 33);
        System.out.println("【3の34乗】");
        displayRuijou(3, 34);
        System.out.println("【3の35乗】");
        displayRuijou(3, 35);
    }

    private static void displayRuijou(long kisuu, int shisuu) {
        // Math.powを使って累乗
        double powDouble = Math.pow(kisuu, shisuu);
        System.out.println((long)powDouble);

        // 愚直に書いて累乗
        long result = 1;
        for(int i = 0; i < shisuu; i++) {
            result= result * kisuu;
        }
        System.out.println(result);
        java.math.BigInteger bigInteger1 = new BigInteger(String.valueOf(kisuu));
        System.out.println(bigInteger1.pow(shisuu));
    }
}
