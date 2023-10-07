package co.bk.sandbox.functional.predicate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.BiPredicate;

public class SimpleBiPredicate {

    public static void main(String[] args) {

        LocalDate arrDate = LocalDate.now();

        LocalDate deptDate = LocalDate.now().plus(1, ChronoUnit.DAYS);

        BiPredicate<LocalDate, LocalDate> areDatesValid = (arrivalDate, departureDate)
                -> arrivalDate.isBefore(departureDate) == true;

        boolean datesValid = true;
        if (!areDatesValid.test(arrDate, deptDate)) {
            datesValid = false;
        }
        System.out.print("dates valid:" + datesValid);
    }
}
