package beginnerContest421;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DRLEMoving {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var rt = sc.nextLong();
        var ct = sc.nextLong();
        var ra = sc.nextLong();
        var ca = sc.nextLong();
        sc.nextLine();
        var nml = sc.nextLine().split(" ");
        var n = Long.parseLong(nml[0]);
        var m = Integer.parseInt(nml[1]);
        var l = Integer.parseInt(nml[2]);

        class Move {
            public String direction;
            public long count;

            public Move(String direction, long count) {
                this.direction = direction;
                this.count = count;
            }
        }

        var tMoveList = new ArrayList<Move>();
        for (int i = 0; i < m; i++) {
            var sa = sc.nextLine().split(" ");
            tMoveList.add(new Move(sa[0], Long.parseLong(sa[1])));
        }
        var aMoveList = new ArrayList<Move>();
        for (int i = 0; i < l; i++) {
            var sa = sc.nextLine().split(" ");
            aMoveList.add(new Move(sa[0], Long.parseLong(sa[1])));
        }

        // 現在の状態
        var tateTState = rt == ra ? "equal" : rt > ra ? "large" : "small";
        var yokoTState = ct == ca ? "equal" : ct > ca ? "large" : "small";
        var totalRecord = m + l;

        var beforeRt = rt;
        var beforeCt = ct;
        var beforeRa = ra;
        var beforeCa = ca;

        var result = 0L;
        while (totalRecord > 0) {
            var tMove = tMoveList.get(0);
            var aMove = aMoveList.get(0);
            var count = tMove.count < aMove.count ? tMove.count : aMove.count;

            var afterRt = beforeRt;
            var afterCt = beforeCt;
            if (tMove.direction.equals("R")) {
                afterCt = afterCt + count;
            } else if (tMove.direction.equals("L")) {
                afterCt = afterCt - count;
            } else if (tMove.direction.equals("U")) {
                afterRt = afterRt - count;
            } else {
                afterRt = afterRt + count;
            }

            var afterRa = beforeRa;
            var afterCa = beforeCa;
            if (aMove.direction.equals("R")) {
                afterCa = afterCa + count;
            } else if (aMove.direction.equals("L")) {
                afterCa = afterCa - count;
            } else if (aMove.direction.equals("U")) {
                afterRa = afterRa - count;
            } else {
                afterRa = afterRa + count;
            }

            var newTateTState = afterRt == afterRa ? "equal" : afterRt > afterRa ? "large" : "small";
            var newYokoTState = afterCt == afterCa ? "equal" : afterCt > afterCa ? "large" : "small";

            if (yokoTState.equals("equal") && newYokoTState.equals("equal") && tateTState.equals("equal") && newTateTState.equals("equal")) {
                result = result + count;
            } else if (newYokoTState.equals("equal") && newTateTState.equals("equal")) {
                result = result + 1;
            } else if (yokoTState.equals("equal") && newYokoTState.equals("equal") && !tateTState.equals("equal") && !newTateTState.equals(tateTState) &&
                    !((tMove.direction.equals("U") || tMove.direction.equals("D")) && (aMove.direction.equals("U") || aMove.direction.equals("D")))
            ) {
                result = result + 1;
            } else if (tateTState.equals("equal") && newTateTState.equals("equal") && !yokoTState.equals("equal") && !newYokoTState.equals(yokoTState) &&
                    !((tMove.direction.equals("R") || tMove.direction.equals("L")) && (aMove.direction.equals("R") || aMove.direction.equals("L")))
            ) {
                result = result + 1;
            }

            yokoTState = newYokoTState;
            tateTState = newTateTState;
            beforeRt = afterRt;
            beforeCt = afterCt;
            beforeRa = afterRa;
            beforeCa = afterCa;

            if (tMove.count == count && aMove.count == count) {
                totalRecord = totalRecord - 2;
                tMoveList.remove(0);
                aMoveList.remove(0);
            } else if (tMove.count > count) {
                totalRecord = totalRecord - 1;
                aMoveList.remove(0);
                tMove.count = tMove.count - count;
            } else {
                totalRecord = totalRecord - 1;
                tMoveList.remove(0);
                aMove.count = aMove.count - count;
            }
        }

        System.out.println(result);
    }
}
