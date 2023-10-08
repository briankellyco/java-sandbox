package co.bk.sandbox.functional;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LongestString {

    public static void main(String[] args) {
        LongestString lesson = new LongestString();
        lesson.runExercises();
    }

    public void runExercises() {

        exampleLongestLineInFile();

        exampleLongestLine_inefficient();

        exampleExternalIteration();

        exampleFindLongestString_recursion();

        exampleFindLongestString_recursionWithStream();

        exampleFindLongestString_recursionWithStreamSimplest();
    }

    private void exampleLongestLineInFile() {


        try {
            Path input = Paths.get(ClassLoader.getSystemClassLoader().getResource("./poem.txt").toURI());
            int longest = Files.lines(input)
                    .mapToInt(String::length)
                    .max()
                    .getAsInt();
            System.out.println("exampleLongestLineInFile - longest line length = " + longest);

        } catch (Exception e) {
        }
    }

    private void exampleLongestLine_inefficient() {


        try {
            Path input = Paths.get(ClassLoader.getSystemClassLoader().getResource("./poem.txt").toURI());
            String longest = Files.lines(input)
                    .sorted((x, y) -> y.length() - x.length())
                    .findFirst()
                    .get();

            System.out.println("exampleLongestLine_inefficient - longest line = " + longest);

        } catch (Exception e) {
        }
    }

    /**
     * Simple, but inherently serial. Not thread safe due to mutable state. Not functional.
     */
    private void exampleExternalIteration() {


        try {
            BufferedReader reader = Files.newBufferedReader(
                    Paths.get(ClassLoader.getSystemClassLoader().getResource("./poem.txt").toURI()), StandardCharsets.UTF_8);
            String longest = "";

            String s;
            while ((s = reader.readLine()) != null)
            if (s.length() > longest.length())
                longest = s;

            System.out.println("exampleExternalIteration - longest line = " + longest);

        } catch (Exception e) {
        }
    }

    private void exampleFindLongestString_recursion() {

        try {
            BufferedReader reader = Files.newBufferedReader(
                    Paths.get(ClassLoader.getSystemClassLoader().getResource("./poem.txt").toURI()), StandardCharsets.UTF_8);
            List<String> lines = new ArrayList<>();
            String s;
            while ((s = reader.readLine()) != null)
                lines.add(s);
            String longest = findLongestString("", 0, lines);

            System.out.println("exampleFindLongestString - longest line = " + longest);
        } catch (Exception e) {
        }

    }

    /*
     * Recursive method.
     *
     * No explicit loop, no mutable state, so we now have a functional solution.
     * Unfortunately not a usable one – larger data sets will generate an OOM exception.
     */
    String findLongestString(String s, int index, List<String> l) {
        if (index >= l.size())
            return s;
        if (index == l.size() - 1) {
            if (s.length() > l.get(index).length())
                return s;
            return l.get(index);
        }
        String s2 = findLongestString(l.get(index), index + 1, l);
        if (s.length() > s2.length())
            return s;
        return s2;
    }

    /*
     * x in effect maintains state by holding longest string found so far.
     */
    private void exampleFindLongestString_recursionWithStream() {

        try {
            Path input = Paths.get(ClassLoader.getSystemClassLoader().getResource("./poem.txt").toURI());

            String longest = Files.lines(input)
                    .reduce((x, y) -> {
                        if (x.length() > y.length())
                            return x;
                        return y;
                    })
                    .get();

            System.out.println("exampleFindLongestString_recursionWithStream - longest line = " + longest);
        } catch (Exception e) {
        }

    }

    /*
     * Stream operations are divided into intermediate (Stream-producing) operations and terminal (value- or side-effect-producing) operations.
     * The "max" operation is terminal.
     */
    private void exampleFindLongestString_recursionWithStreamSimplest() {

        try {
            Path input = Paths.get(ClassLoader.getSystemClassLoader().getResource("./poem.txt").toURI());

            String longest = Files.lines(input)
                    .max(Comparator.comparingInt(String::length))
                    .get();

            System.out.println("exampleFindLongestString_recursionWithStreamSimplest - longest line = " + longest);
        } catch (Exception e) {
        }

    }

}

