/**
 * Copyright © 2014, Oracle and/or its affiliates. All rights reserved.
 * <p/>
 * JDK 8 MOOC Lesson 3 homework
 */
package co.bk.java8mooc;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class to generate a list of random words
 */
public class RandomWords {

    private List<String> sourceWords;

    /**
     * Constructor
     *
     * @throws IOException If the source words file cannot be read
     */
    public RandomWords() throws IOException {

        try {
            BufferedReader reader = Files.newBufferedReader(
                    Paths.get(ClassLoader.getSystemClassLoader().getResource("./words.txt").toURI()), StandardCharsets.UTF_8);

            // example of how to get an "ArrayList" rather than "List".
            sourceWords = reader.lines().collect(Collectors.toCollection(ArrayList::new));

            System.out.println("Loaded " + sourceWords.size() + " words");

        } catch (Exception e) {
            System.out.println("RandomWords - source file could not be read.");
        }
    }

    /**
     * Create a list of a given size containing random words
     *
     * @param listSize The size of the list to create
     * @return The created list
     */
    public List<String> createList(int listSize) {
        Random rand = new Random();
        List<String> wordList = null;

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        //int randomNum = rand.nextInt((max - min) + 1) + min;
        int min = 1;
        int max = listSize;

        wordList = randomSelectN(sourceWords, listSize);

        // alternative approach
        //wordList = rand.ints(listSize, 0, sourceWords.size())
        //        .mapToObj(i -> sourceWords.get(i))
        //        .collect(Collectors.toList());

        return wordList;
    }

    /**
     * Return the list of all source words, which cannot be modified
     *
     * @return The unmodifiable list of all source words
     */
    public List<String> allWords() {
        return Collections.unmodifiableList(sourceWords);
    }

    static <E> List<E> randomSelectN(Collection<? extends E> coll, int n) {
        assert n <= coll.size();
        return coll.stream()
                .filter(new Selector(coll.size(), n))
                .collect(Collectors.toList());
    }

    /*
     * Stateful predicate approach.
     *
     * http://stackoverflow.com/questions/28651908/perform-operation-on-n-random-distinct-elements-from-collection-using-streams-ap
     */
    static class Selector implements Predicate<Object> {
        int total;  // total number items remaining
        int remain; // number of items remaining to select
        Random random = new Random();

        Selector(int total, int remain) {
            this.total = total;
            this.remain = remain;
        }

        @Override
        public synchronized boolean test(Object o) {
            assert total > 0;
            if (random.nextInt(total--) < remain) {
                remain--;
                return true;
            } else {
                return false;
            }
        }
    }
}