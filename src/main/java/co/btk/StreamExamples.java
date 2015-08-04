package co.btk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * REFERENCE: http://www.dotnetperls.com/lambda-java
 */
public class StreamExamples {

    public static void main(String[] args) {
        StreamExamples lesson = new StreamExamples();
        lesson.runExercises();
    }

    public void runExercises() {
        System.out.println("JDK 8 stream examples");
        System.out.println("Running flatMap...");
        exampleFlatMap();
        System.out.println("Running supplier...");
        //exampleSupplier();

    }

    // Convert List of Lists to single List
    //@Test
    private void exampleFlatMap() {

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


    // http://www.adam-bien.com/roller/abien/entry/java_8_flatmap_example
    public static class Developer {

        private String name;
        private Set<String> languages;

        public Developer(String name) {
            this.languages = new HashSet<>();
            this.name = name;
        }

        public void add(String language) {
            this.languages.add(language);
        }

        public Set<String> getLanguages() {
            return languages;
        }
    }
}

