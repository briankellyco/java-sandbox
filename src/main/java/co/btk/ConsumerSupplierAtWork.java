package co.btk;

import java.util.Optional;

/**
 * @author Brian Kelly
 */
public class ConsumerSupplierAtWork {

    public static void main(String[] args) {
        ConsumerSupplierAtWork lesson = new ConsumerSupplierAtWork();
        lesson.runExercises();
    }

    public void runExercises() {
        System.out.println("JDK 8 ConsumerSupplier examples");

        exampleConsumerUsage();

    }

    private void exampleConsumerUsage() {

        /**
         * Commented out to avoid setting up Spring libs in project!
         *
         * Spring WebClient enables async communication. Creating a new client custom headers may be added via a Consumer.
         * The WebClient defaultHeaders method is:
         *   defaultHeaders(java.util.function.Consumer<HttpHeaders> headersConsumer)
         * Docs at:
         *   https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/reactive/function/client/WebClient.Builder.html
         */

//        WebClient webClient = WebClient
//                .builder()
//                .baseUrl("http://localhost:8080")
//                .defaultHeaders(addCustomHeaders())
//                .build();
    }

    /**
     * EXAMPLE: commented to avoid setting up spring libs in project!
     *
     * Consumer has the "HttpHeaders" object implicitly available. See below.
     * @return
     */
//    public Consumer<HttpHeaders> addCustomHeaders() {
//
//        return headers -> {
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set(TenantContext.HEADER_TENANT_ID, testTenantId);
//        };
//    }
}

