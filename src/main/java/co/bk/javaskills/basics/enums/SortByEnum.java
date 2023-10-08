package co.bk.javaskills.basics.enums;

import java.util.Arrays;
import java.util.Optional;

// https://www.baeldung.com/java-comparing-string-to-enum
// https://www.baeldung.com/a-guide-to-java-enums
public enum SortByEnum {


    START_TIME_ASC("startTime"),
    START_TIME_DESC("-startTime"),
    END_TIME_ASC("endTime"),
    END_TIME_DESC("-endTime");

    private String sortParam;

    SortByEnum(String sortParam) {
        this.sortParam = sortParam;
    }

    /*
     * Could be used to help validate incoming HTTP requests in the controller.
     */
    public static Optional<SortByEnum> identifySortParameter(String sortParamToIdentify) {
        return Arrays.stream(values()).filter(it -> it.sortParam.equalsIgnoreCase(sortParamToIdentify)).findAny();
    }
}
