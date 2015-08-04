package co.btk;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * REFERENCE: http://www.dotnetperls.com/lambda-java
 */
public class LongestString {

    public static void main(String[] args) {
        LongestString lesson = new LongestString();
        lesson.runExercises();
    }

    public void runExercises() {
        System.out.println("LongestString approaches");
        exampleLongestLineInFile();
        System.out.println("exampleLongestLine_inefficient");
        exampleLongestLine_inefficient();

        exampleExternalIteration();
    }

    private void exampleLongestLineInFile() {


        try {
            Path input = Paths.get(ClassLoader.getSystemClassLoader().getResource("./poem.txt").toURI());
            int longest = Files.lines(input)
                    .mapToInt(String::length)
                    .max()
                    .getAsInt();
            System.out.println("example1 - longest line length = " + longest);

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

            System.out.println("example1 - longest line = " + longest);

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

            System.out.println("example1 - longest line = " + longest);

        } catch (Exception e) {
        }
    }





}

