package co.bk.javaskills.functional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Brian Kelly
 */
public class OptionalAtWork {

    public static void main(String[] args) {
        OptionalAtWork lesson = new OptionalAtWork();
        lesson.runExercises();
    }

    public void runExercises() {
        System.out.println("JDK 8 Optional examples");

        exampleSingleItemProcessAndThrowException();
        exampleListOfOptionalsToListOfObjects();
        exampleFlatmapAlwaysReturnsAnOptional();
        exampleOptionalListAndFindBiggestValue();
    }

    private void exampleSingleItemProcessAndThrowException() {

        String testValue = "Hello World!";

        Optional<String> optional = Optional.ofNullable(testValue);

        // #1 Option checks if null before processing. No ability to throw an exception as ifPresent is a Consumer.
        optional.ifPresent(
            item -> {
                System.out.println("output via ifPresent: " + item);
            }
        );

        // #2 Option (preferred) checks if null before processing. Ability to throw an exception :)
        optional
        .map( item -> {
            System.out.println("output via Map processing: " + item);
            // Add service calls etc here as required...
            return item;
        })
        .orElseThrow(() -> new RuntimeException("Null Value detected so throwing exception"));
    }

    /**
     * Check if optional is empty... and processes optional if not empty.
     */
    private void exampleListOfOptionalsToListOfObjects() {

        List<String> ids = Arrays.asList("13dfe8be-e066-11ea-87d0-0242ac130003", "1c52b940-e066-11ea-87d0-0242ac130003", "22c1bd1c-e066-11ea-87d0-0242ac130003");

        List<UUID> uuids =  ids.stream()
                .map(this::convertId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        System.out.println("exampleListOfOptionalsToListOfObjects: output list size: " + uuids.size());
    }


    private Optional<UUID> convertId(String id) {
        try {
            return Optional.of(UUID.fromString(id));
        }
        catch(IllegalArgumentException e){
            return Optional.empty();
        }
    }

    private void exampleFlatmapAlwaysReturnsAnOptional() {

        Optional<List<BigDecimal>> data = Optional.of(Arrays.asList(BigDecimal.TEN, BigDecimal.ONE, BigDecimal.valueOf(100), BigDecimal.valueOf(55)));

        // https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
        // flatMap(Function<? super T,Optional<U>> mapper)
        // If a value is present, apply the provided Optional-bearing mapping function to it, return that result, otherwise return an empty Optional.
        System.out.println(data.flatMap(OptionalAtWork::mapperFunction));
    }

    static Optional<String> mapperFunction(List<BigDecimal> input) {
        String result = input == null ? null : "size for " + input.size();
        return Optional.of(result);
    }

    private void exampleOptionalListAndFindBiggestValue() {

        Optional<List<BigDecimal>> data = Optional.of(Arrays.asList(BigDecimal.TEN, BigDecimal.ONE, BigDecimal.valueOf(100), BigDecimal.valueOf(55)));

        // Assumes optional is never empty
        BigDecimal biggestValue = data.get()
            .stream().filter(Objects::nonNull) // check list not empty
            .max(BigDecimal::compareTo)
            .orElse(BigDecimal.valueOf(-1));

        System.out.println("exampleOptionalListAndFindBiggestValue: result #1: " +  biggestValue);

        // Assumes optional could be empty
        BigDecimal biggestValue2 = data
                .orElseGet(Collections::emptyList)
                .stream()
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.valueOf(-1));

        System.out.println("exampleOptionalListAndFindBiggestValue: result #2: " +  biggestValue2);

        // Checks optional is not null; isPresent approach is more verbose and not preferred as much as using map() directly on an optional or the orElseGet approach
        if (data.isPresent()) {
            BigDecimal biggestValue3 = data
                .get()
                .stream()
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.valueOf(-1));

            System.out.println("exampleOptionalListAndFindBiggestValue: result #3: " +  biggestValue3);
        }
    }

}

