import java.util.*;

interface FIFOQueue<T> {
    public void enqueue(T o);
    public T dequeue();
    public boolean isEmpty();
    public int size();
}

class VectorQueue<T> implements FIFOQueue<T> {
    private Vector<T> queue = new Vector<T>();


    public T dequeue() {
        if (queue.size() > 0) {
            return queue.remove(0);
        }
        return null;
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }

    public int size() {
        return queue.size();
    }

    @Override
    public void enqueue(Object o) {
        throw new UnsupportedOperationException("Unimplemented method 'enqueue'");
    }
}

class ArrayListQueue<T> implements FIFOQueue<T> {
    private ArrayList<T> queue = new ArrayList<T>();


    public T dequeue() {
        if (queue.size() > 0) {
            return queue.remove(0);
        }
        return null;
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }

    public int size() {
        return queue.size();
    }

    @Override
    public void enqueue(Object o) {
        throw new UnsupportedOperationException("Unimplemented method 'enqueue'");
    }
}

abstract class Queue<T>{
    protected FIFOQueue<T> queue;

    public Queue(FIFOQueue<T> queue) {
        this.queue = queue;
    }

    public void enqueue(T o) {
        queue.enqueue(o);
    }

    public Object dequeue() {
        return queue.dequeue();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}