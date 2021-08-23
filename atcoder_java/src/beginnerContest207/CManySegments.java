package beginnerContest207;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CManySegments {
    public static void main(String[] args){
        List<Position> positionList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();
            if (t == 1) {
                positionList.add(
                        new Position(
                                i, l, 10, false
                        )
                );
                positionList.add(
                        new Position(
                                i, r, 10, true
                        )
                );
            } else if(t == 2) {
                positionList.add(
                        new Position(
                                i, l, 10, false
                        )
                );
                positionList.add(
                        new Position(
                                i, r, 9, true
                        )
                );
            } else if(t == 3) {
                positionList.add(
                        new Position(
                                i, l, 11, false
                        )
                );
                positionList.add(
                        new Position(
                                i, r, 10, true
                        )
                );
            } else {
                positionList.add(
                        new Position(
                                i, l, 11, false
                        )
                );
                positionList.add(
                        new Position(
                                i, r, 9, true
                        )
                );
            }
        }
        positionList = positionList.stream().sorted((p1, p2) -> {
                if (p1.number == p2.number) {
                    return p1.sortIndex - p2.sortIndex;
                } else {
                    return p1.number - p2.number;
                }
        }).collect(Collectors.toList());
        int tempCount = 0;
        long result = 0;
        for (Position position : positionList) {
            if (!position.endFlag) {
                tempCount = tempCount + 1;
                result = result + (tempCount - 1);
            } else {
                tempCount = tempCount -1;
            }
        }
        System.out.println(result);
    }

    private static class Position {

        public int id;
        public int number;
        public int sortIndex;
        public boolean endFlag;

        public Position(int id, int number, int sortIndex, boolean endFlag) {
            this.id = id;
            this.number = number;
            this.sortIndex = sortIndex;
            this.endFlag = endFlag;
        }
    }
}
