package co.bk.java8mooc;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * The Supplier functional interface is yet another Function specialization that does not take any arguments.
 * The supplier can be used in all contexts where there is no input but an output is expected.
 *
 * Supplier’s Function Descriptor is:
 *   T -> ()
 * This means that there is no input in the lambda definition and the return output is an object of type “T”.
 *
 *
 * Use cases:
 *   1. typically used for lazy generation of values.
 *   2. defining a logic for sequence generation.
 *
 *
 * @author bkco
 */
public class SupplierAtWork
{

    //https://www.baeldung.com/java-8-functional-interfaces
    //https://examples.javacodegeeks.com/core-java/java-8-consumer-supplier-example/


    public static void main(String[] args) {
        SupplierAtWork lesson = new SupplierAtWork();
        lesson.runExercises();
    }

    public void runExercises() {
        System.out.println("Supplier examples");

        exampleLazyGenerationOfValues();

    }

    private void exampleLazyGenerationOfValues()
    {
        System.out.println(DateTimeFormatter.ISO_INSTANT.format(Instant.now())); // 2019-12-16T11:14:08.170122Z
    }

//    private void exampleSupplier() {
//
//        // A Supplier provides values. Call get() on it to retrieve its value.
//        // Pass lambdas to the display method... These conform to the Supplier class.
//        display(() -> 10);
//        display(() -> 100);
//        display(() -> (int) (Math.random() * 100));
//    }


}

