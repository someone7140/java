package beginnerContest424;

import java.util.*;

public class CNewSkillAcquired {
    static Set<Integer> skillSet;
    static Set<Integer> skillSetInitial;
    static Map<Integer, Set<Integer>> skillGetsMap;

    static void loop(Integer skill) {
        var skills = skillGetsMap.get(skill);
        if (skills != null) {
            for (var skill2 : skills) {
                if (!skillSet.contains(skill2)) {
                    skillSet.add(skill2);
                    loop(skill2);
                }
            }
        }
    }

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();

        skillSet = new HashSet();
        skillSetInitial = new HashSet();
        skillGetsMap = new HashMap();
        for (int i = 0; i < n; i++) {
            var queries = sc.nextLine().split(" ");
            var a = Integer.parseInt(queries[0]);
            var b = Integer.parseInt(queries[1]);
            if (a == 0 && b == 0) {
                skillSet.add(i + 1);
                skillSetInitial.add(i + 1);
            } else {
                var getSet = skillGetsMap.get(a);
                if (getSet == null) {
                    skillGetsMap.put(a, new HashSet(Arrays.asList(i + 1)));
                } else {
                    getSet.add(i + 1);
                    skillGetsMap.put(a, getSet);
                }

                getSet = skillGetsMap.get(b);
                if (getSet == null) {
                    skillGetsMap.put(b, new HashSet(Arrays.asList(i + 1)));
                } else {
                    getSet.add(i + 1);
                    skillGetsMap.put(b, getSet);
                }
            }
        }
        for (var skill : skillSetInitial) {
            loop(skill);
        }

        System.out.println(skillSet.size());
    }
}
