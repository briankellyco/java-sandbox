package co.bk.javaskills.functional.function;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * BiFunctions are good for remapping values.
 *
 */
public class BiFunctionAtWork {

    public static void main(String[] args) {

        computeIfAbsent();
        useBiFunctionsSeparately();
    }

    public static void computeIfAbsent() {

        /*
         * Function runs only if value is present.
         *
         * If the value for the specified key is present and non-null, it attempts to compute a new mapping given the key and its current mapped value.
         *
         * If the function returns null, the mapping is removed.
         */

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 11);
        map.put("C", 2);

        // inline lambda
        Integer priority = map.computeIfPresent("C", (k, v) -> v);
        System.out.println("priority: " + priority);

        // lambda in function
        BiFunction<String, Integer, Integer> biFunction = (k, v) -> v + 10;

        // "A" is already present in map, so its value will be incremented (e.g updated in-situ in the "map")
        map.computeIfPresent("A", biFunction);
        System.out.println(map);

        // Since "D" is not present in map, the computation won't occur
        map.computeIfPresent("D", biFunction);
        System.out.println(map);
    }

    public static void useBiFunctionsSeparately() {

        // Chaining BiFunctions is not possible.
        // Chaining BiFunction with a Function is possible.

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 11);
        map.put("C", 2);

        BiFunction<String, Integer, Integer> biFunction = (k, v) -> v + 10;

        BiFunction<ObjectForUpdate, Integer, ObjectForUpdate> biFunctionPackageResult = (k, v) -> {
            k.setPriority(v);
            return k;
        };

        Integer result = map.computeIfPresent("C", biFunction);

        ObjectForUpdate objectResult = biFunctionPackageResult.apply(new ObjectForUpdate(-1), result);

        System.out.println("ObjectForUpdate: priority = " + objectResult.getPriority());


    }


    public static class ObjectForUpdate {

        int priority;

        public ObjectForUpdate(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

    }


}
