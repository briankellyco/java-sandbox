package co.btk;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.*;

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

}
