package regularContest108;

import java.util.Scanner;

public class BAbbreviateFox {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        char[] sArray = s.toCharArray();
        int result = 0;
        int fCount = 0;
        int oCount = 0;
        int xCount = 0;
        boolean ffFlag = false;
        boolean foFlag = false;
        String previosWord = "";
        for (int i = 0; i < s.length(); i++) {
            String word = String.valueOf(sArray[i]);
            if (word.equals("f")) {
                if (previosWord.equals("f")) {
                    ffFlag = true;
                    fCount = fCount + 1;
                } else if (previosWord.equals("o") && foFlag) {
                    foFlag = true;
                    fCount = fCount + 1;
                } else {
                    result = result + fCount + oCount + xCount;
                    fCount = 1;
                    oCount = 0;
                    xCount = 0;
                    ffFlag = false;
                    foFlag = false;
                }
            } else if(word.equals("o")) {
                if (ffFlag) {
                    if (previosWord.equals("f") || previosWord.equals("x")) {
                        oCount = oCount + 1;
                    } else {
                        result = result + 1 + fCount + oCount + xCount;
                        fCount = 0;
                        oCount = 0;
                        xCount = 0;
                        ffFlag = false;
                        foFlag = false;
                    }
                } else if (foFlag) {
                    if (previosWord.equals("f")) {
                        oCount = oCount + 1;
                    } else {
                        result = result + 1 + fCount + oCount + xCount;
                        fCount = 0;
                        oCount = 0;
                        xCount = 0;
                        ffFlag = false;
                        foFlag = false;
                    }
                } else if (previosWord.equals("f")) {
                    foFlag = true;
                    oCount = oCount + 1;
                } else {
                    result = result + 1 + fCount + oCount + xCount;
                    fCount = 0;
                    oCount = 0;
                    xCount = 0;
                    ffFlag = false;
                    foFlag = false;
                }
            } else if (word.equals("x")) {
                if (ffFlag) {
                    if (previosWord.equals("o")) {
                        fCount = fCount - 1;
                        oCount = oCount - 1;
                        if(fCount == 0) {
                            ffFlag = false;
                        }
                    } else {
                        result = result + 1 + fCount + oCount + xCount;
                        fCount = 0;
                        oCount = 0;
                        xCount = 0;
                        ffFlag = false;
                        foFlag = false;
                    }
                } else if (foFlag) {
                    if (previosWord.equals("o") || previosWord.equals("x") ) {
                        fCount = fCount - 1;
                        oCount = oCount - 1;
                        if(fCount == 0) {
                            foFlag = false;
                        }
                    } else {
                        result = result + 1 + fCount + oCount + xCount;
                        fCount = 0;
                        oCount = 0;
                        xCount = 0;
                        ffFlag = false;
                        foFlag = false;
                    }
                } else {
                    result = result + 1 + fCount + oCount + xCount;
                    fCount = 0;
                    oCount = 0;
                    xCount = 0;
                    ffFlag = false;
                    foFlag = false;
                }
            } else {
                result = result + 1 + fCount + oCount + xCount;
                fCount = 0;
                oCount = 0;
                xCount = 0;
                ffFlag = false;
                foFlag = false;
            }
            previosWord = word;
        }

        /*
        boolean loopFlg = true;
        String temp = s;
        while (loopFlg) {
            temp = temp.replaceAll("fox", "");
            int tempResult = temp.length();
            if (tempResult < result) {
                result = tempResult;
            } else {
                loopFlg = false;
            }
        }
        */
        System.out.println(result + fCount + oCount + xCount);
    }
}
