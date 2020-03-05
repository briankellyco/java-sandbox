package co.bk.concurrency;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ForkJoinProcessing {

    public static void main(String[] args) {
        ForkJoinProcessing lesson = new ForkJoinProcessing();
        lesson.runExercises();
    }

    public void runExercises() {
        System.out.println("ForkJoinProcessing - leverage multiple CPU cores to speed up data processing");

        exampleDemosThreadsRampingUpAndDown();
        exampleProcessList();

    }

    private void exampleDemosThreadsRampingUpAndDown() {

        // Learnings:
        // 1. Demo of technique that submits a task to a fork-join pool to run the parallel stream in that pool.
        // 2. ForkJoin enables multiple threads to run
        // 3. Streams normally are sequential but java API makes parallelStream easy to use. Key benefit is they can
        //    take advantage of multicore processors breakings its elements into 2 or more smaller streams before recombining.

        IntStream stream = IntStream.range(0, 1000);

        final Set<String> threadNames = Collections.synchronizedSet(new HashSet<String>());

        try {

            ForkJoinPool forkJoinPool = new ForkJoinPool(10);
            forkJoinPool.submit(() -> {
                stream.parallel().forEach(n -> {

                    System.out.println("Processing n: " + n);
                    try {
                        threadNames.add(Thread.currentThread().getName());
                        System.out.println("Size: " + threadNames.size() + " activeCount: " + forkJoinPool.getActiveThreadCount());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }).get();

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("ForkJoinProcessing.exampleDemosThreadsRampingUpAndDown() - error: " + e);
        }
    }

    private void exampleProcessList() {

        // Learnings:
        // 1. Practical application is use ForkJoin to create threads when querying a slow ORM dataset/database.
        // 2. CopyOnWriteArrayList provides thread safe list.

        List<Integer> ints = IntStream.range(0, 1000).boxed().collect(Collectors.toList());
        Set<Integer> inputData = new HashSet<>(ints);

        List<Integer> data = new CopyOnWriteArrayList<>();

        ForkJoinPool forkJoinPool = null;
        AtomicInteger calcCount = new AtomicInteger(0);
        try {

            forkJoinPool = new ForkJoinPool(10);
            forkJoinPool.submit(() -> {
                inputData.parallelStream()
                    .forEach(n -> {
                        System.out.println("Processing n: " + n);

                        int i = calcCount.incrementAndGet();
                        if ((i > 0) && ((i % 100) == 0)) {
                            System.out.println("Status of parallel processing progress: records processed: " + i);
                            data.add(i); //example off adding processed data to a thread-safe collection
                        }
                    });
            }).get();

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("ForkJoinProcessing.exampleProcessList() - error: " + e);
        } finally {
            if (forkJoinPool != null) {
                forkJoinPool.shutdown();
            }
        }
        System.out.println("Data collection size at end: " + data.size());
    }


}
