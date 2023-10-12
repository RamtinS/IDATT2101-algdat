package edu.ntnu.idatt2101;

/**
 * The Node class represents a node in the graph. Each node has an associated integer value,
 * an optional object, and an edge that can connect it to other nodes.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Oct 06, 2023.
 */
public class Node {
  private Edge edge1;
  private Object d;
  private final int value;

  /**
   * Constructs a Node object with the specified integer value.
   *
   * @param value The integer value associated with the node.
   */
  public Node(int value) {
    this.value = value;
  }

  /**
   * Retrieves the first edge connected to this node.
   *
   * @return The first edge connected to this node, or `null` if there are no edges.
   */
  public Edge getEdge1() {
    return edge1;
  }

  /**
   * Retrieves the optional object associated with this node.
   *
   * @return The object associated with this node.
   */
  public Object getD() {
    return d;
  }

  /**
   * Retrieves the integer value associated with this node.
   *
   * @return The integer value of this node.
   */
  public int getValue() {
    return value;
  }

  /**
   * Sets the first edge connected to this node.
   *
   * @param edge1 The first edge to be connected to this node.
   */
  public void setEdge1(Edge edge1) {
    this.edge1 = edge1;
  }

  /**
   * Sets the optional object associated with this node.
   *
   * @param d The object to be associated with this node.
   */
  public void setD(Object d) {
    this.d = d;
  }

  /**
   * Returns a string representation of this node, which is its integer value.
   *
   * @return A string representation of the node's integer value.
   */
  @Override
  public String toString() {
    return Integer.toString(getValue());
  }
}
