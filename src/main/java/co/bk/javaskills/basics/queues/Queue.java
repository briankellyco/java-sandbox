package co.bk.javaskills.basics.queues;

interface Queue<T> {
    Queue<T> enqueue(T ele);
    T dequeue();
}