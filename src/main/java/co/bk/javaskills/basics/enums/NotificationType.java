package co.bk.javaskills.basics.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Enums are one way to define unique states based on a combination of input values.
 *
 * Using a static map those states can be easily looked up using the corresponding unique text key. The enum is iterated only once with this approach.
 *  https://www.baeldung.com/java-enum-values
 */
public enum NotificationType {

    VENDORA_REQUESTFORITEM_REQUEST_OPENED(BuyingChannel.VENDOR_A, ProcessType.REQUEST_FOR_ITEM, ActivityState.REQUEST_OPENED),
    VENDORA_REQUESTFORITEM_REQUEST_RECEIVED(BuyingChannel.VENDOR_A, ProcessType.REQUEST_FOR_ITEM, ActivityState.REQUEST_RECEIVED),
    VENDORA_REQUESTFORITEM_REQUEST_FULFILLED(BuyingChannel.VENDOR_A, ProcessType.REQUEST_FOR_ITEM, ActivityState.REQUEST_FULFILLED),
    VENDORA_QUOTATION_QUOTATION_DRAFT(BuyingChannel.VENDOR_A, ProcessType.QUOTATION, ActivityState.QUOTATION_DRAFT);

    private final BuyingChannel buyingChannel;

    private final ProcessType processType;

    private final ActivityState activityState;

    private static final Map<String, NotificationType> LOOKUP_BY_UNIQUE_KEY = new HashMap<>();

    static {
        for (NotificationType notificationType: values()) {
            LOOKUP_BY_UNIQUE_KEY.put(buildUniqueKey(notificationType), notificationType);
        }
    }

    private NotificationType(BuyingChannel buyingChannel, ProcessType processType, ActivityState activityState) {

        this.buyingChannel = buyingChannel;
        this.processType = processType;
        this.activityState = activityState;
    }

    public static NotificationType valueOfUniqueKey(BuyingChannel buyingChannel, ProcessType processType, ActivityState activityState) {
        return LOOKUP_BY_UNIQUE_KEY.get(buildUniqueKey(buyingChannel,processType,activityState));
    }

    private static final String buildUniqueKey(NotificationType notificationType) {
        return new StringBuilder("-")
            .append(notificationType.buyingChannel.toString())
            .append(notificationType.processType.toString())
            .append(notificationType.activityState.toString())
            .toString();
    }

    private static final String buildUniqueKey(BuyingChannel buyingChannel, ProcessType processType, ActivityState activityState) {
        return new StringBuilder("-")
                .append(buyingChannel.toString())
                .append(processType.toString())
                .append(activityState.toString())
                .toString();
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }


    enum BuyingChannel {
        VENDOR_A,
        VENDOR_B;
    }

    enum ProcessType {
        QUOTATION,
        REQUEST_FOR_ITEM;
    }

    enum ActivityState {
        REQUEST_OPENED,
        REQUEST_RECEIVED,
        REQUEST_FULFILLED,
        QUOTATION_DRAFT,
        QUOTATION_SENT,
        QUOTATION_REJECTED;
    }

}
