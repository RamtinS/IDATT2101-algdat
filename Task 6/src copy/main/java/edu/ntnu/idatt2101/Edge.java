package edu.ntnu.idatt2101;

/**
 * The Edge class represents an edge in a graph.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Oct 06, 2023.
 */
public class Edge {
  private final Edge next;
  private final Node to;

  /**
   * Constructs an Edge object with the specified Node and a
   * reference to the next edge.
   *
   * @param n The destination Node that this edge connects to.
   * @param nextEdge The next edge.
   */
  public Edge(Node n, Edge nextEdge) {
    this.to = n;
    this.next = nextEdge;
  }

  /**
   * Retrieves the next edge.
   *
   * @return The next edge, or null if this is the last edge.
   */
  public Edge getNext() {
    return next;
  }

  /**
   * Retrieves the Node that this edge connects to.
   *
   * @return The Node that this edge connects to.
   */
  public Node getTo() {
    return to;
  }
}
