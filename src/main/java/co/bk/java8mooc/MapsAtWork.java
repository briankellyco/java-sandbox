package co.bk.java8mooc;

import com.google.common.collect.TreeMultimap;

import java.net.URLEncoder;
import java.util.*;
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

        exampleMapThatSupportsDuplicateKeys();

    }

    private void exampleIterateMap() {

        Map<String,String> map = new HashMap<>();
        map.put("heh heh", "my my");
        map.forEach( (k,v) -> System.out.println("exampleIterateMap - Key: " + k + ": Value: " + v));
    }


    private void exampleSortedByValue() {

        //LinkedHashMap respects ordering by value. Treemap imposes natural ordering on key. HashMap has no ordering.
        Map<Integer, Integer> unsorted = new HashMap<Integer, Integer>();
        unsorted.put(new Integer(1), new Integer(7));
        unsorted.put(new Integer(2), new Integer(5));
        unsorted.put(new Integer(3), new Integer(1));
        unsorted.put(new Integer(4), new Integer(7));

        LinkedHashMap<Integer, Integer> sortedMap =
                unsorted.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        sortedMap.forEach( (k,v) -> System.out.println("exampleSortedByValue - Key: " + k + ": Value: " + v));

    }

    private void exampleSortedByValue_usingSortByValue() {

        Map<Integer, Integer> unsorted = new HashMap<Integer, Integer>();
        unsorted.put(new Integer(1), new Integer(7));
        unsorted.put(new Integer(2), new Integer(5));
        unsorted.put(new Integer(3), new Integer(1));
        unsorted.put(new Integer(4), new Integer(7));

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
        unsorted.put(new Integer(1), new Integer(7));
        unsorted.put(new Integer(2), new Integer(5));
        unsorted.put(new Integer(3), new Integer(1));
        unsorted.put(new Integer(4), new Integer(7));

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



    private void exampleMapThatSupportsDuplicateKeys() {

        StringBuilder queryString = new StringBuilder();

        // allow duplicate keys (provided values are different). Natural ordering applied in treemultimap
        TreeMultimap<String, String> urlQueryParameters = TreeMultimap.create();
        urlQueryParameters.put("Param1", "value2");
        urlQueryParameters.put("Param1", "value1");
        urlQueryParameters.put("Param2", "value3");

        NavigableSet<String> keys = urlQueryParameters.keySet();
        for (String key : keys) {
            System.out.println("key = " + key);
            NavigableSet<String> values = urlQueryParameters.get(key);
            for (String value : values) {
                System.out.println("value = " + value);
                queryString.append(key).append("=").append(encodeParameter(value)).append("&");
            }
        }
        queryString.deleteCharAt(queryString.lastIndexOf("&"));
        System.out.println("exampleMapThatSupportsDuplicateKeys: concatenated url param string: " + queryString.toString());
    }

    private String encodeParameter(String param) {
        try {
            return URLEncoder.encode(param, "UTF-8");
        } catch (Exception e) {
            return URLEncoder.encode(param);
        }
    }




}
