package co.bk;

import javax.xml.stream.events.ProcessingInstruction;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class Scratch {


    public static void main(String[] args) {
        String location = "/guided-procurement/v1-alpha/activities/9d1c0106-31bf-42fe-8436-065ef4ed1179";

        String id = location.split("/")[4];

        System.out.printf("id: " + id );
    }

//    public static void main(String[] args) throws Exception {
//        final String json
//                = "[\n"
//                + "    {\n"
//                + "        \"a\":\"b\",\n"
//                + "        \"c\":\"d\"\n"
//                + "    },\n"
//                + "    {\n"
//                + "        \"e\":\"f\",\n"
//                + "        \"g\":\"h\",\n"
//                + "        \"i\":\"j\"\n"
//                + "    },\n"
//                + "    {\n"
//                + "        \"h\":\"k\"\n"
//                + "    }\n"
//                + "]"; // [{a:b,c:d},{e:f,g:h,i:j},{h:k}]
//        ObjectMapper mapper = new ObjectMapper();
//        TypeFactory factory = TypeFactory.defaultInstance();
//        List<Map<String, String>> list = mapper.readValue(json,factory
//                .constructCollectionType(List.class, factory
//                        .constructMapType(Map.class, String.class, String.class)));
//        System.out.println(list.toString());
//    }

//    public static void main(String[] args) {
//
//        Map<String, List<ProcessingTaskOrder>> channelStateAssociations = new HashMap<String, List<ProcessingTaskOrder>>();
//
//        // ProcessingTaskOrder
//
//        List<ProcessingTaskOrder> stateAssociations = new ArrayList<>();
//
//        //stateAssociations.
//        Map<String, Integer> processingTaskOrderForAssessmentPending  = new HashMap<String,Integer>();
//        processingTaskOrderForAssessmentPending.put("StateChangedEventPublisher", 0);
//        processingTaskOrderForAssessmentPending.put("ConsolidatorPsgNotificationPublisher", 1);
//
//        Map<String, Integer> processingTaskOrderForRequestRejected  = new HashMap<String,Integer>();
//        processingTaskOrderForRequestRejected.put("ConsolidatorPsgNotificationPublisher", 0);
//        processingTaskOrderForRequestRejected.put("StateChangedEventPublisher", 1);
//
//        stateAssociations.add(new ProcessingTaskOrder("ASSESSMENT_PENDING", processingTaskOrderForAssessmentPending));
//        stateAssociations.add(new ProcessingTaskOrder("REQUEST_REJECTED", processingTaskOrderForRequestRejected));
//
//        channelStateAssociations.put("PSG", stateAssociations);
//        channelStateAssociations.put("EASYRFX", stateAssociations);
//
//        /**
//         * Better approach would be....
//         */
//        //channelStateAssociations.put("EASYRFX", Map<String,<Map<String,Integer>>);
//
//        Map<String,Integer> processingTaskOrder = channelStateAssociations.get("buyingChannelName").get("activityStatusName");
//        Integer executionOrder = processingTaskOrder.get("NameOfClassDetected");
//        myProcessingTask.setExecutionOrder(executionOrder);
//    }

    public static class ProcessingTaskOrder {

        public String activityStatus;

        public Map<String, Integer> processorOrder;

        public ProcessingTaskOrder(String activityStatus){
            this.activityStatus = activityStatus;
        }

        public ProcessingTaskOrder(String activityStatus, Map<String,Integer> processorOrder){
            this.activityStatus = activityStatus;
            this.processorOrder = processorOrder;
        }

        public String getActivityStatus() {
            return activityStatus;
        }

        public void setActivityStatus(String activityStatus) {
            this.activityStatus = activityStatus;
        }

        public Map<String, Integer> getProcessorOrder() {
            return processorOrder;
        }

        public void setProcessorOrder(Map<String, Integer> processorOrder) {
            this.processorOrder = processorOrder;
        }
    }

//    public static void main(String[] args) {
//
//        String uuid = UUID.randomUUID().toString();
//
//        // OffsetDateTime7: 2020-01-10T16:44:29.037873+01:00 Berlin....
//        OffsetDateTime offsetDT7 = OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
//        System.out.println("OffsetDateTime7: " + offsetDT7);
//
//
//        ////////////////////
////        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
////        date.atStartOfDay().format(DateTimeFormatter.ofPattern(dateFormat));
//
//        //OffsetDateTime offsetDT1 = OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
//        // https://stackoverflow.com/questions/45452791/convert-yyyy-mm-ddthhmmss-mmmz-to-normal-hhmm-a-format
//        Instant instant = Instant.parse("2017-08-02T06:05:30.000Z");
//        ZonedDateTime z = instant.atZone(ZoneId.of("Europe/Berlin"));
//
//        System.out.println("instant1: " + instant.toString());        // 2017-08-02T06:05:30Z e.g UTC
//        System.out.println("zoned1: " + z.toString());                // 2017-08-02T08:05:30+02:00[Europe/Berlin] e.g in February with time savings
//
//        String input = "test";
//
////        String output = Optional
////                .ofNullable(input)
////                .map(i -> "hello" + i.get));
////                .orElse(null);
//
//
//    }
}
