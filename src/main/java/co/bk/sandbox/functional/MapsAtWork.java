package co.bk.sandbox.functional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapsAtWork {

    public static void main(String args[]) {

        MapsAtWork aaw = new MapsAtWork();
        aaw.runExercises();
    }

    public void runExercises() {
        System.out.println("JDK 8 map examples");

        exampleIterateMap();
        exampleSortedByValue();
        exampleSortedByValue_usingSortByValue();
        exampleSortedByValue_usingMapEntry();
        exampleWriteHttpHeader_usingBiConsumer();

    }

    private void exampleIterateMap() {

        Map<String,String> map = new HashMap<>();
        map.put("heh heh", "my my");
        map.forEach( (k,v) -> System.out.println("exampleIterateMap - Key: " + k + ": Value: " + v));
    }


    private void exampleSortedByValue() {

        //LinkedHashMap respects ordering by value. Treemap imposes natural ordering on key. HashMap has no ordering.
        Map<Integer, Integer> unsorted = new HashMap<Integer, Integer>();
        unsorted.put(Integer.valueOf(1), Integer.valueOf(7));
        unsorted.put(Integer.valueOf(2), Integer.valueOf(5));
        unsorted.put(Integer.valueOf(3), Integer.valueOf(1));
        unsorted.put(Integer.valueOf(4), Integer.valueOf(7));

        LinkedHashMap<Integer, Integer> sortedMap =
                unsorted.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        sortedMap.forEach( (k,v) -> System.out.println("exampleSortedByValue - Key: " + k + ": Value: " + v));

    }

    private void exampleSortedByValue_usingSortByValue() {

        Map<Integer, Integer> unsorted = new HashMap<Integer, Integer>();
        unsorted.put(Integer.valueOf(1), Integer.valueOf(7));
        unsorted.put(Integer.valueOf(2), Integer.valueOf(5));
        unsorted.put(Integer.valueOf(3), Integer.valueOf(1));
        unsorted.put(Integer.valueOf(4), Integer.valueOf(7));

        Map sortedMap = MapsAtWork.sortByValue(unsorted);

        sortedMap.forEach( (k,v) -> System.out.println("exampleSortedByValue_usingSortByValue - Key: " + k + ": Value: " + v));

    }

    /**
     * Method sorts by value. LinkedHashMap respects iteration order.
     *
     * Signature indicates "the type parameter must support comparison with other instances of its
     * own type, via the Comparable interface.". For example java.lang.Integer would be a valid V.
     */
    public static <K, V extends Comparable<? super V>> Map<K, V>
        sortByValue(Map<K, V> map) {

        Map<K,V> result = new LinkedHashMap<>();
        Stream <Map.Entry<K,V>> st = map.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEachOrdered(e ->result.put(e.getKey(),e.getValue()));
        return result;
    }


    /**
     * Map.Entry provides comparison methods:
     *      https://docs.oracle.com/javase/8/docs/api/
     * Collectors do fun stuff:
     *      https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
     */
    private void exampleSortedByValue_usingMapEntry() {

        Map<Integer, Integer> unsorted = new HashMap<Integer, Integer>();
        unsorted.put(Integer.valueOf(1), Integer.valueOf(7));
        unsorted.put(Integer.valueOf(2), Integer.valueOf(5));
        unsorted.put(Integer.valueOf(3), Integer.valueOf(1));
        unsorted.put(Integer.valueOf(4), Integer.valueOf(7));

        List list =
                unsorted.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue).collect(Collectors.toList());
                //.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        list.forEach( (v) -> System.out.println("exampleSortedByValue_usingMapEntry - Value: " + v));

    }

    /**
     * BiConsumer function accepts two input parameters and returns none.
     *
     * Example usage: could be used to write customised http headers on a per tenant basis.
     */
    private void exampleWriteHttpHeader_usingBiConsumer() {

        Map<String, Collection<String>> headerMap = new HashMap<String, Collection<String>>();
        headerMap.put("tenant1", Arrays.asList("X-TENANT-ID:1", "X-PRODUCT-ID:5"));
        headerMap.put("tenant2", Arrays.asList("X-TENANT-ID:2", "X-PRODUCT-ID:4"));
        headerMap.put("tenant3", Arrays.asList("X-TENANT-ID:3", "X-PRODUCT-ID:1"));

        BiConsumer<String, Collection<String>> biConsumer = (key, value) -> {
            value.forEach( v -> {
                String[] header = v.split(":");
                addCustomHeader(null, header[0], header[1]);
            });
        };
        headerMap.forEach(biConsumer);
    }

    /**
     * Demo supporting method
     * @param o HttpServletResponse
     * @param headerKey custom header name
     * @param headerValue custom header value
     */
    private static void addCustomHeader(Object o, String headerKey, String headerValue) {
        System.out.println("headerKey: "+ headerKey+" headerValue: "+ headerValue);
    }


    private static void exampleEnumOrListToAMap() {

        // TODO

//        static final Map<String, Status> VALID_HTTP_STATUS_CODES =
//                Arrays.stream(Status.values()).collect(Collectors.toMap(item -> String.valueOf(item.getStatusCode()), Function.identity() ));

        // WORKS
//    static final Map<String, String> EXTENSION_TO_MIMETYPE =
//        Arrays.stream(new String[][] {
//            { "txt", "text/plain" },
//            { "html", "text/html" },
//            { "js", "application/javascript" },
//            { "css", "text/css" },
//            { "xml", "application/xml" },
//            { "png", "image/png" },
//            { "gif", "image/gif" },
//            { "jpg", "image/jpeg" },
//            { "jpeg", "image/jpeg" },
//            { "svg", "image/svg+xml" },
//        }).collect(Collectors.toMap(kv -> kv[0], kv -> kv[1]));

    }



}
