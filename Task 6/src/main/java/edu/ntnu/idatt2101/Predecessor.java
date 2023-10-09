package edu.ntnu.idatt2101;

/**
 * The Predecessor class represents information about the
 * predecessor of a node in the graph.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Oct 06, 2023.
 */
public class Predecessor {
  private int distance;

  private Node predecessor;

  /**
   * Represents an "infinite" distance, which is applied to
   * nodes that cannot be reached from the start node.
   */
  private final int infinite = 1000000000;

  /**
   * Constructs a Predecessor object with an initial distance set to infinite.
   */
  public Predecessor() {
    this.distance = infinite;
  }

  /**
   * Retrieves the distance from the start node to the current node.
   *
   * @return The distance value.
   */
  public int getDistance() {
    return distance;
  }

  /**
   * Retrieves the predecessor node in the graph.
   *
   * @return The predecessor node.
   */
  public Node getPredecessor() {
    return predecessor;
  }

  /**
   * Retrieves the constant value representing an infinite distance.
   *
   * @return The value used to represent an infinite distance.
   */
  public int getInfinite() {
    return infinite;
  }

  /**
   * Sets the distance from the start node to the current node.
   *
   * @param distance The distance value to be set.
   */
  public void setDistance(int distance) {
    this.distance = distance;
  }

  /**
   * Sets the predecessor node in the traversal path.
   *
   * @param predecessor The predecessor node to be set.
   */
  public void setPredecessor(Node predecessor) {
    this.predecessor = predecessor;
  }
}
