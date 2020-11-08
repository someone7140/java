package experiment;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTest {

    public static void main(String[] args){
        Date date = new Date(100000000);
        System.out.println(date);
        OffsetDateTime time = OffsetDateTime.ofInstant(Instant.ofEpochMilli(100000000), ZoneId.systemDefault());
        System.out.println(time.toString());
    }

}
