package co.bk.javaskills.basics.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.function.BiPredicate;
import java.util.Date;

public class RunExamples {


    public static void main(String[] args) {

        try {

            // ISO-8601 formatted string
            String str = "2009-12-02T11:25:25";
            LocalDateTime dateTime = LocalDateTime.parse(str); // cannot handle with Z
            System.out.println("Parsed LocalDateTime: " + dateTime);

            Instant instant = Instant.parse( "2011-05-03T11:58:01Z" );
            System.out.println("Parsed instant: " + instant);

            String localT = "11:25";
            LocalTime localTime = LocalTime.parse(localT);
            System.out.println("Parsed localTime: " + localTime);
            System.out.println("Parsed localTime and then toString: " + localTime.toString());

            String utcStringFromInstant = Instant.now().truncatedTo(ChronoUnit.SECONDS).toString();
            System.out.println("utcStringFromInstant: " + utcStringFromInstant);

            Date dateNow = Date.from(Instant.now());
            System.out.println("dateNow: " + dateNow);

            LocalDate arrDate = LocalDate.now();

            LocalDate deptDate = LocalDate.now().plus(1, ChronoUnit.DAYS);

            BiPredicate<LocalDate, LocalDate> areDatesValid = (arrivalDate, departureDate)
                    -> arrivalDate.isBefore(departureDate) == true;

            boolean datesValid = true;
            if (!areDatesValid.test(arrDate, deptDate)) {
                datesValid = false;
            }
            System.out.print("dates valid:" + datesValid);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
