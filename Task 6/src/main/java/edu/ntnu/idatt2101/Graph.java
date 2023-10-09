package edu.ntnu.idatt2101;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * The Graph class represents a graph data structure and provides methods for creating,
 * traversing, and manipulating the graph.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Oct 06, 2023.
 */
public class Graph {

  private int amountOfNodes;
  private int amountOfEdges;
  private Node[] node;

  /**
   * Constructs a new graph by reading data from the given BufferedReader.
   * The graph data is expected to be in a specific format, where the first line contains
   * the number of nodes and the number of edges, followed by lines specifying edge connections.
   *
   * @param reader The BufferedReader containing graph data.
   * @throws IOException If an I/O error occurs while reading the data.
   */
  public void newGraph(BufferedReader reader) throws IOException {
    StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
    amountOfNodes = Integer.parseInt(stringTokenizer.nextToken());
    node = new Node[amountOfNodes];
    for (int i = 0; i < amountOfNodes; ++i) {
      node[i] = new Node(i);
    }
    this.amountOfEdges = Integer.parseInt(stringTokenizer.nextToken());
    for (int i = 0; i < amountOfEdges; ++i) {
      stringTokenizer = new StringTokenizer(reader.readLine());
      int from = Integer.parseInt(stringTokenizer.nextToken());
      int to = Integer.parseInt(stringTokenizer.nextToken());
      Edge edge = new Edge(node[to], node[from].getEdge1());
      node[from].setEdge1(edge);
    }
  }

  /**
   * Initializes the predecessor information for all nodes in the graph.
   *
   * @param n The starting node for the initialization.
   */
  public void initializePredecessor(Node n) {
    for (int i = amountOfNodes; i-- > 0;) {
      node[i].setD(new Predecessor());
    }
    ((Predecessor)n.getD()).setDistance(0);
  }

  /**
   * Performs a breadth-first search starting from the specified node.
   *
   * @param startNode The starting node for the breadth-first search.
   */
  public void breadthFirstSearch(Node startNode) {
    initializePredecessor(startNode);
    CustomQueue queue = new CustomQueue(amountOfNodes - 1);
    queue.addToQueue(startNode);
    while (!queue.empty()) {
      Node node = (Node)queue.nextInQueue();
      for (Edge edge = node.getEdge1(); edge != null; edge = edge.getNext()) {
        Predecessor predecessor = (Predecessor) edge.getTo().getD();
        if (predecessor.getDistance() == predecessor.getInfinite()) {
          predecessor.setDistance(((Predecessor)node.getD()).getDistance() + 1);
          predecessor.setPredecessor(node);
          queue.addToQueue(edge.getTo());
        }
      }
    }
  }

  /**
   * Performs a depth-first topological sorting of the graph.
   *
   * @param startNode The starting node for the topological sorting.
   * @param nodeList The current list of nodes in the topological order.
   * @return The topologically sorted list of nodes.
   */
  public Node depthFirstTopological(Node startNode, Node nodeList) {
    TopologicalSortStructure startNodeList = (TopologicalSortStructure)startNode.getD();
    if (startNodeList.found) {
      return nodeList;
    }
    startNodeList.found = true;
    for (Edge edge = startNode.getEdge1(); edge != null; edge = edge.getNext()) {
      nodeList = depthFirstTopological(edge.getTo(), nodeList);
    }
    startNodeList.next = nodeList;
    return startNode;
  }

  /**
   * Performs a topological sorting of the graph.
   *
   * @return The topologically sorted list of nodes.
   */
  public Node topologicalSorting() {
    Node list = null;
    for (int i = amountOfNodes; i-- > 0 ;) {
      node[i].setD(new TopologicalSortStructure());
    }
    for (int i = amountOfNodes; i-- > 0;) {
      list = depthFirstTopological(node[i], list);
    }
    return list;
  }

  /**
   * Retrieves an array containing all nodes in the graph.
   *
   * @return An array of nodes in the graph.
   */
  public Node[] getNode() {
    return node;
  }

  /**
   * Retrieves the number of nodes in the graph.
   *
   * @return The number of nodes in the graph.
   */
  public int getAmountOfNodes() {
    return amountOfNodes;
  }
}
