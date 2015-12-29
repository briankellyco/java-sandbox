package co.btk;

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
public class StreamBasicsII {

    public static void main(String[] args) {
        StreamBasicsII lesson = new StreamBasicsII();
        lesson.runExercises();
    }

    public void runExercises() {
        System.out.println("JDK 8 stream examples");

        exampleCollectorGroupingBy_plusMapping();

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

        // Group by department the names of developers
        Map<String, Set<String>> byDept = developers
                .stream()
                .collect(Collectors.groupingBy(Developer::getDepartment,
                        Collectors.mapping(Developer::getName, Collectors.toSet())
                ));

        System.out.println("exampleCollectorGroupingBy_plusMapping: " + byDept);
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


}

