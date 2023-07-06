package util;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Linked blocking queue with {@link #add(Object)} method, which adds only element, that is not already in the queue.
 */
public class SetBlockingQueue<T> extends LinkedBlockingQueue<T> {
    private static final long serialVersionUID = -4731318426330991826L;

    private final Set<T> set = Collections.newSetFromMap(new ConcurrentHashMap<>());

    /**
     * Add only element, that is not already enqueued.
     * The method is synchronized, so that the duplicate elements can't get in during race condition.
     *
     * @param t
     *            object to put in
     * @return true, if the queue was changed, false otherwise
     */
    @Override
    public synchronized boolean add(final T t) {
        if (this.set.contains(t)) {
            return false;
        } else {
            this.set.add(t);
            return super.add(t);
        }
    }

    /**
     * Takes the element from the queue.
     * Note that no synchronization with {@link #add(Object)} is here, as we don't care about the element staying in the
     * set longer needed.
     *
     * @return taken element
     * @throws InterruptedException
     *             e
     */
    @Override
    public T take() throws InterruptedException {
        final T t = super.take();
        this.set.remove(t);
        return t;
    }
}
