package co.bk.javaskills.basics.queues;

/**
 * Custom stack object. Resizes as required.
 * 
 * reference:
 *   http://eddmann.com/posts/implementing-a-stack-in-java-using-arrays-and-linked-lists/
 */
public class StackArray<T> implements Stack<T> {

    private T[] arr;

    private int total;

    public StackArray() {
        arr = (T[]) new Object[2];
    }

    /**
     * Drawback as resizing impacts performance.
     * 
     * @param capacity
     */
    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        System.arraycopy(arr, 0, tmp, 0, total);
        arr = tmp;
    }

    public StackArray<T> push(T ele) {
        if (arr.length == total) resize(arr.length * 2);
        arr[total++] = ele;
        return this;
    }

    public T pop() {
        if (total == 0) throw new java.util.NoSuchElementException();
        T ele = arr[--total];
        arr[total] = null;
        if (total > 0 && total == arr.length / 4) resize(arr.length / 2);
        return ele;
    }

    @Override
    public String toString() {
        return java.util.Arrays.toString(arr);
    }

}