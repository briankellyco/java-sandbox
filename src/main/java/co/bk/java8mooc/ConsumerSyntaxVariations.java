package co.bk.java8mooc;

import java.util.function.Consumer;

/**
 * @author Brian Kelly
 */
public class ConsumerSyntaxVariations {

    public static void main(String args[]) {

        /* Java Consumer example using a class */
        ConsumerClassExample sce = new ConsumerClassExample();
        sce.accept(new Long(2));

        /* Functional Consumer example using inner class */
        Consumer<Long> innerConsumer = new Consumer<Long>() {
            @Override
            public void accept(Long t) {
                System.out.println(t * t);
            }
        };
        innerConsumer.accept(new Long(4));

        /* Implemented Consumer function with verbose lambda expression */
        Consumer<Long> lambdaConsumer = (Long t) -> System.out.println(t * t);
        lambdaConsumer.accept(new Long(5));

        /* Concise lambda and Consumer function example */
        Consumer<Long> conciseLambda = t -> System.out.println(t * t);
        conciseLambda.accept(new Long(5));

    }
}

/*
    class ConsumerClassExample implements Consumer<Long> {
      public void accept(Long t) {
        System.out.println(t*t);
      }
    }
 */

