package co.bk.javaskills.functional.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Brian Kelly
 */
public class StreamBasicsI {

    public static void main(String[] args) {
        StreamBasicsI lesson = new StreamBasicsI();
        lesson.runExercises();
    }

    public void runExercises() {
        System.out.println("JDK 8 stream examples");

        exampleFlatMap();
        exampleInfiniteStream_terminateOnCondition();
        exampleCollectorAverageInts();
        exampleCollectorSumLongs();
        exampleCollectorToSet();
        exampleCollectorGroupingBy();
        exampleCollectorGroupingBy_plusMapping();

    }

    // Convert List of Lists to single List
    private void exampleFlatMap() {

        // http://www.adam-bien.com/roller/abien/entry/java_8_flatmap_example
        List<Developer> team = new ArrayList<>();
        Developer polyglot = new Developer("esoteric");
        polyglot.add("clojure");
        polyglot.add("scala");
        polyglot.add("groovy");
        polyglot.add("go");

        Developer busy = new Developer("pragmatic");
        busy.add("java");
        busy.add("javascript");

        team.add(polyglot);
        team.add(busy);

        List<String> teamLanguages = team.stream().
                map(d -> d.getLanguages()).
                flatMap(l -> l.stream()).
                collect(Collectors.toList());
        //Asserts.assertTrue(teamLanguages.containsAll(polyglot.getLanguages()));
        //assertTrue(teamLanguages.containsAll(busy.getLanguages()));
    }

    public static class Developer {

        private String name;
        private String department;
        private Set<String> languages;

        public Developer(String name) {
            this.languages = new HashSet<>();
            this.name = name;
        }

        public Developer(String name, String department) {
            this.languages = new HashSet<>();
            this.name = name;
            this.department = department;
        }

        public void add(String language) {
            this.languages.add(language);
        }

        public Set<String> getLanguages() {
            return languages;
        }

        public String getDepartment() {
            return this.department;
        }

        public String getName() {
            return this.name;
        }
    }


    private void exampleInfiniteStream_terminateOnCondition() {

        // This will not iterate the whole collection, because streams are lazily evaluated. Stops when condition met.
        Random r = new Random();
        OptionalInt val =  r.ints().filter(i -> i > 256).findFirst();

        System.out.println("exampleInfiniteStream_terminateOnCondition - terminated on = " + val.getAsInt());
    }

    private void exampleCollectorAverageInts() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        Double result = list.stream().collect(Collectors.averagingInt(v -> v * 2));
        System.out.println("exampleCollectorAverageInts - average = " + result);
    }

    private void exampleCollectorSumLongs() {
        List<Long> list = new ArrayList<>();
        list.add((long) 220);
        list.add((long) 250);
        list.add((long) 300);
        long result = list.stream().collect(Collectors.summingLong(i -> i));
        System.out.println("exampleCollectorSumLongs - sum = " + result);
    }

    private void exampleCollectorToSet() {

        Set<String> set = Stream.of("1", "2", "3").collect(Collectors.toSet());
        set.forEach(s -> System.out.println("exampleCollectorToSet1 - value =" + s));

        // syntax option
        Set<String> set2 = Stream.of("1", "2", "3").collect(Collectors.<String>toSet());
        set2.forEach(s -> System.out.println("exampleCollectorToSet2 - value =" + s));
    }

    private void exampleCollectorGroupingBy() {

        /*
            Collectors provide powerful ways to gather elements of an input stream
            - Into collections
            - In numerical ways like totals and averages
            - Collectors can be composed to build more complex ones
            - You can also create your own Collector
         */
        Developer devFunny = new Developer("tom", "consultancy");
        Developer devCool = new Developer("alice", "product");
        Developer devSerious = new Developer("paul", "product");

        List<Developer> developers = new ArrayList<Developer>();
        developers.add(devFunny);
        developers.add(devCool);
        developers.add(devSerious);

        // Group employees by department
        Map<String, List<Developer>> byDept = developers
                .stream()
                .collect(Collectors.groupingBy(Developer::getDepartment));

        System.out.println("exampleCollectorGroupingBy: " + byDept);
    }

    private void exampleCollectorGroupingBy_plusMapping() {

        // "mapping" adapts a Collector to accept different type elements.
        Developer devFunny = new Developer("tom", "consultancy");
        Developer devCool = new Developer("alice", "product");
        Developer devSerious = new Developer("paul", "product");

        List<Developer> developers = new ArrayList<Developer>();
        developers.add(devFunny);
        developers.add(devCool);
        developers.add(devSerious);

        // Group by department the names of developers. Mapping is being used as a "downstream" collector.
        Map<String, Set<String>> byDept = developers
                .stream()
                .collect(Collectors.groupingBy(Developer::getDepartment,
                        Collectors.mapping(Developer::getName, Collectors.toSet())
                ));

        System.out.println("exampleCollectorGroupingBy_plusMapping: " + byDept);
    }

}

