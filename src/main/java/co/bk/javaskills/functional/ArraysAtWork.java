package co.bk.javaskills.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Arrays;

public class ArraysAtWork {

    public static void main(String args[]) {

        ArraysAtWork aaw = new ArraysAtWork();
        aaw.runExercises();
    }

    public void runExercises() {
        System.out.println("JDK 8 array examples");

        examplePrimitivesToList();
        examplePrimitivesToListPre8();

        exampleListToPrimitives();
        exampleListToPrimitivesPre8();

    }

    private void examplePrimitivesToList() {
        int[] ints = {1,2,3};
        List<Integer> list = IntStream.of(ints).boxed().collect(Collectors.toList());
        System.out.println("examplePrimitivesToList - list = " + list);
    }

    private void examplePrimitivesToListPre8() {

        //"List" contains types and Arrays.asList does NOT autobox primitives. Manual insertion required.
        int[] ints = {1,2,3};
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < ints.length; i++)  {
            list.add(ints[i]);
        }
        System.out.println("examplePrimitivesToListPre8 - list = " + list);
    }

    private void exampleListToPrimitives() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        int[] array = list.stream().mapToInt(i->i).toArray();
        System.out.println("exampleListToPrimitives - array = " + Arrays.toString(array));
    }

    private void exampleListToPrimitivesPre8() {
        Integer[] intArray = new Integer[] { 1, 2, 3 };
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(intArray));
        int[] ints = new int[list.size()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = list.get(i);
        }
        System.out.println("exampleListToPrimitivesPre8 - array = " + Arrays.toString(ints));
    }


}
