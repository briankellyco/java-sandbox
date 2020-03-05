package co.bk;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

public class Scratch {

    public static void main(String[] args) {

        // OffsetDateTime7: 2020-01-10T16:44:29.037873+01:00 Berlin....
        OffsetDateTime offsetDT7 = OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        System.out.println("OffsetDateTime7: " + offsetDT7);


        ////////////////////
//        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
//        date.atStartOfDay().format(DateTimeFormatter.ofPattern(dateFormat));

        //OffsetDateTime offsetDT1 = OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        // https://stackoverflow.com/questions/45452791/convert-yyyy-mm-ddthhmmss-mmmz-to-normal-hhmm-a-format
        Instant instant = Instant.parse("2017-08-02T06:05:30.000Z");
        ZonedDateTime z = instant.atZone(ZoneId.of("Europe/Berlin"));

        System.out.println("instant1: " + instant.toString());        // 2017-08-02T06:05:30Z e.g UTC
        System.out.println("zoned1: " + z.toString());                // 2017-08-02T08:05:30+02:00[Europe/Berlin] e.g in February with time savings

        String input = "test";

//        String output = Optional
//                .ofNullable(input)
//                .map(i -> "hello" + i.get));
//                .orElse(null);


    }
}
