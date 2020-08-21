package co.bk;

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
}

