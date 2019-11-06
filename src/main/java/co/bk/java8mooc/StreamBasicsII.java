package co.bk.java8mooc;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        exampleAddBigDecimals_correctApproach();

        exampleCheckItemInList();

        exampleLambdaUsageInsideMap();

        exampleAggregateListIntoList();

        exampleSwitchDeveloperLanguage();

        exampleNullChecksAndReturnString();

        exampleNullListProcessing();

    }

    private void exampleCollectorGroupingBy_plusMapping() {

        // "mapping" adapts a Collector to accept different type elements.
        List<Developer> developers = stubTestData();

        // Group by department the names of developers
        Map<String, Set<String>> byDept = developers
                .stream()
                .collect(Collectors.groupingBy(Developer::getDepartment,
                        Collectors.mapping(Developer::getName, Collectors.toSet())
                ));

        System.out.println("exampleCollectorGroupingBy_plusMapping: " + byDept);
    }

    /**
     *  A normal "reduction" is meant to combine two immutable values such as int,
     *  double etc. and produce a new one; it’s an immutable reduction.
     *
     *
     */
    private void exampleAddBigDecimals_correctApproach() {

        List<Developer> developers = stubTestData();

        BigDecimal devTeamCost = developers.stream()
                .map(e->e.getSalary())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("exampleAddBigDecimals_correctApproach: " + devTeamCost);
    }

    private void exampleCheckItemInList() {

        List<Developer> developers = stubTestData();

        if (containsName(developers, "tom") == true) {
            System.out.println("exampleCheckItemInList: true!!!");
        } else {
            System.out.println("exampleCheckItemInList: false!!!");
        }
    }

    private void exampleLambdaUsageInsideMap() {

        List<Developer> developers = stubTestData();

        /*
         * Lambda "{}" enables logic to be embedded inside map. The map requires a "return" statement in this case and
         * will ALWAYS return an element e.g use a "filter" method if you want to filter.
         */
        List<Developer> result = developers.stream().map( developer -> {

            if (developer.getName().equalsIgnoreCase("paul")) {
                developer.setSalary(new BigDecimal("200000"));
            }
            return developer;

        }).collect(Collectors.toList());

        System.out.println("exampleLambdaUsageInsideMap: size: " + result.size());

    }

    private void exampleAggregateListIntoList() {

        /*
         * How to aggregate List<Developer> objects into a master aggregated List<Developer>. Set usage
         * represents some iterator condition that might produce such a scenario for this logic.
         *
         * Trick is.... use "flatMap(List::stream)" where the list is essentially removed and items are then
         * streamed out before before being "collected" into the aggregated list.
         *
         * SEE
         * https://stackoverflow.com/questions/25147094/turn-a-list-of-lists-into-a-list-using-lambdas/
         * "list into list
         * "aggregated list"
         */
        Set<String> deSet = new HashSet<String>(Arrays.asList(new String[]{"eins", "zwei", "drei"}));

        final List<Developer> developersTimesThree = deSet.stream()
                .map( setItem -> {
                    return stubTestData();

                }).flatMap(List::stream)
                .collect(Collectors.<Developer>toList());

        System.out.println("exampleAggregateListIntoList: " + developersTimesThree.size());
    }

    private void exampleSwitchDeveloperLanguage() {

        List<Developer> developers = stubTestData();

        //If you wanna modify current list, use Collection.forEach; use stream.map() to create a new list.
        developers.forEach( developer -> {
                List<String> languages = developer.getLanguages().stream().map(
                        language -> {
                            return (language != null && language.equalsIgnoreCase("java")) ? "vba" : language;
                        }
                ).collect(Collectors.toList());
            developer.setLanguages(languages);
        });
        System.out.println("exampleSwitchDeveloperLanguage: vba: " + developers.size());

        /*
         * Java8 enables named lambdas in addition to inline functions.
         */
        Consumer<Developer> developerList = developer -> {
            List<String> languages = developer.getLanguages().stream().map(
                    language -> {
                        return (language != null && language.equalsIgnoreCase("vba")) ? "java" : language;
                    }
            ).collect(Collectors.toList());
            developer.setLanguages(languages);
        };
        developers.forEach(developerList);
        System.out.println("exampleSwitchDeveloperLanguage: java: " + developers.size());

    }

    private void exampleNullChecksAndReturnString() {

        List<Developer> developers = stubTestData();

        /*
         * Demos getting first elements from a list in a null safe manner.
         * Demos getting a string from a map (or any other object type).
         * Use "maps" to chain null checks e.g http://winterbe.com/posts/2015/03/15/avoid-null-checks-in-java/
         */
        String title = Optional.ofNullable(developers)
                .orElseGet(Collections::emptyList)
                .stream()
                .findFirst()
                .map(developer -> {
                    return developer.getDepartment() != null ?  developer.getDepartment()
                : "no department defined";
            })
            .orElseThrow(() -> new RuntimeException("exception in exampleNullChecksAndReturnString()"));
        System.out.println("exampleNullChecksAndReturnString: title: " + title);
    }

    private void exampleNullListProcessing() {

        /*
         * Streaming a null list is not possible. Null lists should NOT exist. If in doubt create a stream.
         * Additionally collectors in java8 cannot return a null list, only an empty list. Which is ideal really.
         */

        List<Developer> input = null;


        // Options: ensure not null lists as that would represent a compile error.
        // 1. create a wrapper method equivalent to line below that returns a list OR
        // 2. use Apache commons CollectionUtils.emptyIfNull()
        // 3. ... and other options https://www.baeldung.com/java-null-safe-streams-from-collections
        Stream<Developer> processList = (input == null) ? Stream.empty() : input.stream();


        List<Developer> result = processList.map( developer -> {
            return developer;
        }).collect(Collectors.toList()); // toList() will at minimum return empty list.

        assertNotNull(result);
        System.out.println("exampleNullListProcessing: empty stream processed into empty list (null list return not possible)");

    }

    // TODO.... use normal for loop and not forEach
    private void exampleIterateListButBreak() {
        // https://stackoverflow.com/questions/23308193/break-or-return-from-java-8-stream-foreach
    }


    /**
     * Check if developer is already in list using a stream.
     *
     * @param list a list of developers.
     * @param name the name of the developer
     * @return true or false.
     */
    public boolean containsName(final List<Developer> list, final String name){
        return list.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
    }



    public static class Developer {

        private String name;
        private String department;
        private List<String> languages;
        private BigDecimal salary;

        public Developer(String name) {
            this.languages = new ArrayList<>();
            this.name = name;
        }

        public Developer(String name, String department) {
            this.languages = new ArrayList<>();
            this.name = name;
            this.department = department;
        }

        public Developer(String name, String department, BigDecimal salary) {
            this.languages = new ArrayList<>();
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public void add(String language) {
            this.languages.add(language);
        }

        public List<String> getLanguages() {
            return languages;
        }

        public void setLanguages(List<String> languages) {
            this.languages = languages;
        }

        public String getDepartment() {
            return this.department;
        }

        public String getName() {
            return this.name;
        }

        public BigDecimal getSalary() {
            return salary;
        }

        public void setSalary(BigDecimal salary) {
            this.salary = salary;
        }
    }

    public List<Developer> stubTestData() {
        Developer devFunny = new Developer("tom", "consultancy", new BigDecimal("100.11111"));
        devFunny.add("java");
        devFunny.add("javascript");
        Developer devCool = new Developer("alice", "product", new BigDecimal("1000.22222"));
        devCool.add("java");
        devCool.add("javascript");
        devCool.add("golang");
        devCool.add("groovy");
        Developer devSerious = new Developer("paul", "product", new BigDecimal("10000.55555"));
        Developer devQuiet = new Developer("john", "product", null);
        devQuiet.add("python");

        List<Developer> developers = new ArrayList<Developer>();
        developers.add(devFunny);
        developers.add(devCool);
        developers.add(devSerious);
        developers.add(devQuiet);
        return developers;
    }


}

