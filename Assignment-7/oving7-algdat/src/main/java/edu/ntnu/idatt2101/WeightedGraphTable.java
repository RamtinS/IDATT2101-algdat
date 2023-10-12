package edu.ntnu.idatt2101;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * The class represents a weighted edge in the flow network.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Oct 12, 2023.
 */
public class WeightedGraphTable {

  // The number of nodes in the graph.
  private int amountOfNodes;

  // The 2D array of weighted edges representing the graph.
  private WeightedEdge[][] edge;

  /**
   * Constructs a new WeightedGraphTable and initializes it with data from a BufferedReader.
   *
   * @param reader A BufferedReader containing information about the graph structure.
   * @throws IOException If an I/O error occurs while reading from the BufferedReader.
   */
  public void newGraph(BufferedReader reader) throws IOException {
    StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
    amountOfNodes = Integer.parseInt(stringTokenizer.nextToken());
    edge = new WeightedEdge[amountOfNodes][amountOfNodes];
    for (int i = 0; i < amountOfNodes; ++i) {
      for (int j = 0; j < amountOfNodes; ++j) {
        edge[i][j] = new WeightedEdge(0);
      }
    }
    int amountOfEdges = Integer.parseInt(stringTokenizer.nextToken());
    for (int i = 0; i < amountOfEdges; i++) {
      stringTokenizer = new StringTokenizer(reader.readLine());
      int from = Integer.parseInt(stringTokenizer.nextToken());
      int to = Integer.parseInt(stringTokenizer.nextToken());
      int capacity = Integer.parseInt(stringTokenizer.nextToken());
      edge[from][to] = new WeightedEdge(capacity);
    }
  }

  /**
   * Retrieves the array of weighted edges representing the graph.
   *
   * @return The array of weighted edges.
   */
  public WeightedEdge[][] getEdge() {
    return edge;
  }

  /**
   * Retrieves the number of nodes in the graph.
   *
   * @return The number of nodes in the graph.
   */
  public int getAmountOfNodes() {
    return amountOfNodes;
  }

  /**
   * Checks if an edge is part of an augmenting path by examining its remaining capacity.
   *
   * @param from The source node of the edge.
   * @param to The target node of the edge.
   * @return True if the edge has remaining capacity, false otherwise.
   */
  public boolean isEdgeInAugmentingPath(int from, int to) {
    return edge[from][to].getRemainingCapacity() > 0;
  }

  /**
   * Updates the remaining capacity of two edges by increasing the flow
   * from 'from' to 'to' and decreasing it in the reverse direction.
   *
   * @param from The source node of the edge.
   * @param to The target node of the edge.
   * @param bottleneck The amount by which to increase (and decrease in reverse) the flow through the edge.
   */
  public void updateRemainingEdgeCapacity(int from, int to, int bottleneck) {
    edge[from][to].increaseFlow(bottleneck);
    edge[to][from].increaseFlow(-bottleneck);
  }
}
