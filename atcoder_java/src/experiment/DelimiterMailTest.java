package experiment;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DelimiterMailTest {

    public static void main(String[] args){
        String testData = "\",test1\"@aaa.com,test2@aaa.com,\"te,s,t3\"@aaa.com";
        Pattern p = Pattern.compile("(.+?@.+?),|(.+?@.+$)");
        Matcher m = p.matcher(testData);
        List<String> result = new ArrayList<>();
        while (m.find()) {
            String extract = m.group(0);
            if (extract.endsWith(",")) {
                result.add(extract.substring(0, extract.length() - 1));
            } else {
                result.add(extract);
            }
        }
        result.stream().forEach(r-> System.out.println(r));
    }
}
