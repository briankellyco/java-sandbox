/**
 * Copyright © 2014, Oracle and/or its affiliates. All rights reserved.
 * <p/>
 * JDK 8 MOOC Lesson 2 homework
 */
package co.bk.javaskills.mooc;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lesson2 {


    private static final String WORD_REGEXP = "[- .:,]+";

    public static void main(String[] args) throws IOException {
        Lesson2 lesson = new Lesson2();
        lesson.runExercises();
    }

    /**
     * Run the exercises to ensure we got the right answers
     *
     * @throws java.io.IOException
     */
    public void runExercises() throws IOException {
        System.out.println("JDK 8 Lambdas and Streams MOOC Lesson 2");
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
        System.out.println("Running exercise 6 solution...");
        exercise6();
        System.out.println("Running exercise 7 solution...");
        exercise7();
    }

    /**
     * Exercise 1
     *
     * Create a new list with all the general from original list dto to
     * lower case and print them out.
     */
    private void exercise1() {
        List<String> list = Arrays.asList(
                "The", "Quick", "BROWN", "Fox", "Jumped", "Over", "The", "LAZY", "DOG");

        List<String> target = list.stream().map(String::toLowerCase).collect(Collectors.toList());
        System.out.println("exercise 1 - lowercase items: " + target);
    }

    /**
     * Exercise 2
     *
     * Modify exercise 1 so that the new list only contains general that have an
     * odd length
     */
    private void exercise2() {
        List<String> list = Arrays.asList(
                "The", "Quick", "BROWN", "Fox", "Jumped", "Over", "The", "LAZY", "DOG");

        List<String> target = list.stream()
                .map(String::toLowerCase)
                .filter(item -> (item.length() % 2 == 1))
                .collect(Collectors.toList());
        System.out.println("exercise 2 - lowercase items that are odd lengths: " + target);
    }

    /**
     * Exercise 3
     *
     * Join the second, third and forth general of the list into a single string,
     * where each word is separated by a hyphen (-). Print the resulting string.
     */
    private void exercise3() {
        List<String> list = Arrays.asList(
                "The", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog");

        // The Java 8 streams API lacks the features of getting the "index" of a stream element.
        // There are often workarounds, however. Usually this can be done by "driving" the stream with an integer range,
        // and taking advantage of the fact that the elements are often in an array or in a collection accessible by index.
        String target =
                IntStream.range(0, list.size())
                        .filter(i -> i == 2 || i == 3 | i == 4)
                        .mapToObj(i -> list.get(i))
                        .collect(Collectors.joining("-"));
        System.out.println("exercise 3 - filtered and joined items: " + target);
    }

    /**
     * Count the number of lines in the file using the BufferedReader provided
     */
    private void exercise4() throws IOException {

        long count = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(
                    Paths.get(ClassLoader.getSystemClassLoader().getResource("./poem.txt").toURI()), StandardCharsets.UTF_8);
            count = reader.lines().count();
        } catch (Exception e) {
        }
        System.out.println("exercise 4 - line count: " + count);
    }

    /**
     * Using the BufferedReader to access the file, create a list of words with
     * no duplicates contained in the file.  Print the words.
     *
     * HINT: A regular expression, WORD_REGEXP, is already defined for your use.
     */
    private void exercise5() throws IOException {

        try {
            // Approach 1
            BufferedReader reader = Files.newBufferedReader(
                    Paths.get(ClassLoader.getSystemClassLoader().getResource("./poem.txt").toURI()), StandardCharsets.UTF_8);

            Function<String, List<String>> splitFunction = line -> {
                Pattern p = Pattern.compile(WORD_REGEXP);
                CharSequence cs = line.toLowerCase();
                return Arrays.asList(p.split(cs));
            };

            TreeSet<String> target = reader.lines()
                    .map(line -> splitFunction.apply(line))
                    .flatMap(l -> l.stream())
                    .collect(Collectors.toCollection(TreeSet::new));
            ;
            System.out.println("exercise 5 - list of words, no duplicates: approach1: " + target);

            // Approach 2
            BufferedReader readerII = Files.newBufferedReader(
                    Paths.get(ClassLoader.getSystemClassLoader().getResource("./poem.txt").toURI()), StandardCharsets.UTF_8);
            List<String> target2 = readerII.lines()
                    .flatMap(line -> Stream.of(line.toLowerCase().split(WORD_REGEXP)))
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            System.out.println("exercise 5 - list of words, no duplicates: approach2: " + target2);

        } catch (Exception e) {
        }
    }

    /**
     * Using the BufferedReader to access the file create a list of words from
     * the file, dto to lower-case and with duplicates removed, which is
     * sorted by natural order.  Print the contents of the list.
     */
    private void exercise6() throws IOException {

        try {
            BufferedReader reader = Files.newBufferedReader(
                    Paths.get(ClassLoader.getSystemClassLoader().getResource("./poem.txt").toURI()), StandardCharsets.UTF_8);

            List<String> target = reader.lines()
                    .flatMap(line -> Stream.of(line.toLowerCase().split(WORD_REGEXP)))
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            System.out.println("exercise 6 - list of words, no duplicates, natural order: " + target);

        } catch (Exception e) {
        }

    }

    /**
     * Modify exercise6 so that the words are sorted by length
     */
    private void exercise7() throws IOException {

        try {
            BufferedReader reader = Files.newBufferedReader(
                    Paths.get(ClassLoader.getSystemClassLoader().getResource("./poem.txt").toURI()), StandardCharsets.UTF_8);

            List<String> target = reader.lines()
                    .flatMap(line -> Stream.of(line.toLowerCase().split(WORD_REGEXP)))
                    .distinct()
                    .sorted(Comparator.comparing(String::length))
                    .collect(Collectors.toList());

            System.out.println("exercise 7 - list of words, no duplicates, natural order, sorted by length " + target);

        } catch (Exception e) {
        }
    }

}

