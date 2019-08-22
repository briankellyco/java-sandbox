package co.bk.java8mooc;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
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

}

