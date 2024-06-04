package org.mpsjunio2023.messagehandler;

/**
 * Interface implementing a bounded queue.
 *
 * @param <T>
 */
public interface BoundedQueue<T> {
  void put(T element) throws FullQueueException;
  T get() throws EmptyQueueException;
  boolean isFull()  ;
  boolean isEmpty() ;
  int getNumberOfItems() ;
  int getCapacity() ;
}
