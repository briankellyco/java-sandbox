package co.btk;

import java.math.BigDecimal;
import java.util.*;
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

        exampleAddBigDecimals_correctApproach();

        exampleCheckItemInList();

        exampleLambdaUsageInsideMap();

        exampleAggregateListIntoList();

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
         * Trick is.... use "flatMap(List::stream)" where the list is essentiall removed and the items are then
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
        private Set<String> languages;
        private BigDecimal salary;

        public Developer(String name) {
            this.languages = new HashSet<>();
            this.name = name;
        }

        public Developer(String name, String department) {
            this.languages = new HashSet<>();
            this.name = name;
            this.department = department;
        }

        public Developer(String name, String department, BigDecimal salary) {
            this.languages = new HashSet<>();
            this.name = name;
            this.department = department;
            this.salary = salary;
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

        public BigDecimal getSalary() {
            return salary;
        }

        public void setSalary(BigDecimal salary) {
            this.salary = salary;
        }
    }

    public List<Developer> stubTestData() {
        Developer devFunny = new Developer("tom", "consultancy", new BigDecimal("100.11111"));
        Developer devCool = new Developer("alice", "product", new BigDecimal("1000.22222"));
        Developer devSerious = new Developer("paul", "product", new BigDecimal("10000.55555"));
        Developer devQuiet = new Developer("paul", "product", null);

        List<Developer> developers = new ArrayList<Developer>();
        developers.add(devFunny);
        developers.add(devCool);
        developers.add(devSerious);
        developers.add(devQuiet);
        return developers;
    }


}

