/**
 * Copyright © 2014, Oracle and/or its affiliates. All rights reserved.
 * <p/>
 * JDK 8 MOOC Lesson 1 homework
 */
package co.bk.javaskills.mooc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Lesson1 {

    public static void main(String[] args) {
        Lesson1 lesson = new Lesson1();
        lesson.runExercises();
    }

    /**
     * All exercises should be completed using Lambda expressions and the new
     * methods added to JDK 8 where appropriate. There is no need to use an
     * explicit loop in any of the code. Use method references rather than full
     * lambda expressions wherever possible.
     */

    /**
     * Run the exercises to ensure we got the right answers
     */
    public void runExercises() {
        System.out.println("JDK 8 Lambdas and Streams MOOC Lesson 1");
        System.out.println("Running exercise 1 solution...");
        exercise1();
        System.out.println("Running exercise 2 solution...");
        exercise2();
        System.out.println("Running exercise 3 solution...");
        exercise3();
        System.out.println("Running exercise 4 solution...");
        exercise4();
        System.out.println("Running exercise 5 solution...");
        exercise5();
    }

    /**
     * Exercise 1
     * <p/>
     * Create a string that consists of the first letter of each word in the list
     * of Strings provided.
     */
    private void exercise1() {
        List<String> list = Arrays.asList(
                "alpha", "bravo", "charlie", "delta", "echo", "foxtrot");

        /* YOUR CODE HERE */
        // One liner stream approach
        String target = list.stream().map(item -> item.substring(0, 1)).collect(Collectors.joining(""));

        // Multi liner forEach approach using new StringJoiner object.
        StringJoiner sj = new StringJoiner("");
        list.forEach(item ->
                        sj.add(item.substring(0, 1))
        );

        System.out.println("exercise1 - first letters combined - one liner: " + target);
        System.out.println("exercise1 - first letters combined - multi liner: " + sj.toString());

    }

    /**
     * Exercise 2
     * <p/>
     * Remove the words that have odd lengths from the list.
     */
    private void exercise2() {
        List<String> list = new ArrayList<>(Arrays.asList(
                "alpha", "bravo", "charlie", "delta", "echo", "foxtrot"));

        /* YOUR CODE HERE */
        StringJoiner sj = new StringJoiner("");
        list.forEach(item -> {
            if ((item.length() % 2) == 0) {
                sj.add(item);
            }
        });

        System.out.println("exercise2 - even length words: " + sj.toString());

    }

    /**
     * Exercise 3
     * <p/>
     * Replace every word in the list with its upper case equivalent.
     */
    private void exercise3() {
        List<String> list = new ArrayList<>(Arrays.asList(
                "alpha", "bravo", "charlie", "delta", "echo", "foxtrot"));

        /* YOUR CODE HERE */
        List<String> target = list.stream().map(String::toUpperCase).collect(Collectors.toList());

        System.out.println("exercise 3 - uppercase items: " + target);
    }

    /**
     * Exercise 4
     * <p/>
     * Convert every key-value pair of the map into a string and append them all
     * into a single string, in iteration order.
     */
    private void exercise4() {
        Map<String, Integer> map = new TreeMap<>();
        map.put("c", 3);
        map.put("b", 2);
        map.put("a", 1);

        /* YOUR CODE HERE */
        String target = map.entrySet().stream().map( item -> item.getKey() + item.getValue()).collect(Collectors.joining(", "));

        System.out.println("exercise 4 - concatenated items: " + target);
    }

    /**
     * Exercise 5
     * <p/>
     * Create a new thread that prints the numbers from the list.
     */
    private void exercise5() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        /* YOUR CODE HERE */
        // create a "task", code that implements Runnable.
        // Lambda expression is implicitly assigned to our functional interface.
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
            list.forEach(System.out::print);
        };

        // Code executed by Lesson1 main thread
        task.run();

        // Code executed by new thread just created
        Thread thread = new Thread(task);
        thread.start();

        System.out.println("exercise5 - done");
    }
}
