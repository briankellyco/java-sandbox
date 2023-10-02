package co.bk;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * The trick is to flatten both streams and collect one of them to a list (so that it can be iterated over multiple times).
 *
 * Compare the two streams and filter data according to business rules.
 */
public class StreamOfStreams {

    public static void main(String[] args) {

        StreamOfStreams streamExample = new StreamOfStreams();

        PendingTransaction pendingTransaction1 = new PendingTransaction(50L);
        PendingTransaction pendingTransaction2 = new PendingTransaction(100L);
        PendingTransaction pendingTransaction3 = new PendingTransaction(200L);
        Stream<PendingTransaction> pendingStream = Stream.of(pendingTransaction1, pendingTransaction2, pendingTransaction3);

        ProcessedTransaction processedTransaction1 = new ProcessedTransaction("50", Optional.of("DONE"));
        ProcessedTransaction processedTransaction2 = new ProcessedTransaction("200", Optional.of("DONE"));
        ProcessedTransaction processedTransaction3 = new ProcessedTransaction("100", Optional.of("Pending"));
        ProcessedTransaction processedTransaction4 = new ProcessedTransaction("75", Optional.of("DONE"));

        Stream<ProcessedTransaction> processedStream1 = Stream.of(processedTransaction1, processedTransaction2);
        Stream<ProcessedTransaction> processedStream2 = Stream.of(processedTransaction3);
        Stream<ProcessedTransaction> processedStream3 = Stream.of(processedTransaction4);

        Stream<Stream<ProcessedTransaction>> processedStreams = Stream.of(processedStream1, processedStream2, processedStream3);

        Stream<PendingTransaction> result = streamExample.reconcileV2(pendingStream, processedStreams);

        List<PendingTransaction> pendingTransactionList = result.collect(Collectors.toList());

        pendingTransactionList.forEach((k) -> {
            System.out.println("id: " + k.getId());
        });
    }

    /*
     * The problem requires that the nested stream is flattened and then "collected" into a list. The list items can be compared against the pending stream.
     *
     * Attempting to compare two streams directly is not possible as the first an item in a stream is iterated over, it is consumed.
     * Consumed meaning it can't be iterated over again.
     *
     * With this in mind, the solution just needs to iterate one stream and have the items from the other stream stored in a list for comparison.
     *
     */
    Stream<PendingTransaction> reconcileV2(Stream<PendingTransaction> pending, Stream<Stream<ProcessedTransaction>> processed) {

        Stream<ProcessedTransaction> processedFlattened = flatten(processed);

        // Streams are iterable once. Collect processed stream into a list so that it can be iterated over multiple times.
        List<ProcessedTransaction> processedFlattenedList = processedFlattened.collect(Collectors.toList());

        Stream<PendingTransaction> filteredPending = pending.filter(pendingTransaction ->
                processedFlattenedList.stream().anyMatch(processedTransaction ->
                        isTransactionProcessed.test(pendingTransaction, processedTransaction)
                )
        );
        return filteredPending;
    }

    public BiPredicate<PendingTransaction, ProcessedTransaction> isTransactionProcessed =
            (pendingItem, processedItem) -> {

                if (processedItem.getId() != null && !processedItem.getId().isEmpty()) {
                    if (Long.valueOf(processedItem.getId()).equals(pendingItem.getId())) {
                        if (processedItem.getStatus().isPresent()) {
                            if (processedItem.getStatus().get().equalsIgnoreCase("DONE")) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            };

    public static Stream<ProcessedTransaction> flatten(Stream<Stream<ProcessedTransaction>> processed){
        return processed
                .flatMap(e -> e instanceof Stream ? e : Stream.empty());
    }

    static class PendingTransaction {
        Long id; // always set

        public PendingTransaction(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    static class ProcessedTransaction {
        String id; // null or empty possible... otherwise numeric

        Optional<String> status; // null or "DONE" (case insensitve)

        public ProcessedTransaction(String id, Optional<String> status) {
            this.id = id;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public Optional<String> getStatus() {
            return status;
        }
    }

}