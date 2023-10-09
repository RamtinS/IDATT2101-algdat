package edu.ntnu.idatt2101;

/**
 * THe CustomQueue class is a simple implementation of a circular queue with a fixed size.
 * It allows you to add objects to the queue, retrieve the next object in the queue,
 * check if the queue is empty or full, and peek at the object at the front of the queue.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Oct 06, 2023.
 */
public class CustomQueue {
  private final Object[] table;
  private int start;
  private int end;
  private int amount;

  /**
   * Constructs a CustomQueue with the specified table size.
   *
   * @param tableSize The size of the queue table.
   */
  public CustomQueue(int tableSize) {
    this.table = new Object[tableSize];
    this.start = 0;
    this.end = 0;
    this.amount = 0;
  }

  /**
   * Checks if the queue is empty.
   *
   * @return true if the queue is empty, false otherwise.
   */
  public boolean empty() {
    return amount == 0;
  }

  /**
   * Checks if the queue is full.
   *
   * @return true if the queue is full, false otherwise.
   */
  public boolean full() {
    return amount == table.length;
  }

  /**
   * Adds an object to the end of the queue if it's not full.
   *
   * @param object The object to be added to the queue.
   */
  public void addToQueue(Object object) {
    if (full()) {
      return;
    }
    table[end] = object;
    end = (end + 1) % table.length;
    ++amount;
  }

  /**
   * Retrieves and removes the next object in the queue if it's not empty.
   *
   * @return The next object in the queue, or null if the queue is empty.
   */
  public Object nextInQueue() {
    if (!empty()) {
      Object object = table[start];
      start = (start + 1) % table.length;
      --amount;
      return object;
    } else {
      return null;
    }
  }

  /**
   * Peeks at the object at the front of the queue without removing it if the queue is not empty.
   *
   * @return The object at the front of the queue, or null if the queue is empty.
   */
  public Object peek() {
    if (!empty()) {
      return table[start];
    } else {
      return null;
    }
  }
}
