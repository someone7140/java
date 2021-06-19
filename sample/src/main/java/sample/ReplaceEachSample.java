package sample;

import org.apache.commons.lang3.StringUtils;

public class ReplaceEachSample {
    public static void main(String[] args) {
        String testWord = "りんご|みかん|ぶどう|りんご|みかん|ぶどう";
        String result = StringUtils.replaceEach(
                testWord,
                new String[]{"りんご", "みかん", "ぶどう"},
                new String[]{"apple", "orange", "grape"}
        );
        System.out.println(result);
    }
}
