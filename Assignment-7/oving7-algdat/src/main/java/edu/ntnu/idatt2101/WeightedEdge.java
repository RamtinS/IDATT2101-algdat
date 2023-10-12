package edu.ntnu.idatt2101;

/**
 * The class represents a weighted edge with capacity and flow information.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Oct 12, 2023.
 */
public class WeightedEdge {

  // The maximum capacity of the edge.
  private final int capacity;

  //The current flow through the edge.
  private int flow;

  /**
   * Constructs a new WeightedEdge with the given capacity and initializes the flow to 0.
   *
   * @param capacity The maximum capacity of the edge.
   */
  public WeightedEdge(int capacity) {
    this.capacity = capacity;
    this.flow = 0;
  }

  /**
   * Increases the flow through the edge by the
   * minimum capacity (bottleneck) in the augmented path.
   *
   * @param bottleneck The minimum capacity in the augmented path.
   */
  public void increaseFlow(int bottleneck) {
    flow += bottleneck;
  }

  /**
   * Retrieves the remaining capacity of the edge,
   * which is the maximum capacity minus the current flow.
   *
   * @return The remaining capacity of the edge.
   */
  public int getRemainingCapacity() {
    return capacity - flow;
  }
}

