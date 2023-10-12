package edu.ntnu.idatt2101;

import edu.ntnu.idatt2101.filehandling.FileGraphHandler;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * This class implements the Edmonds-Karp algorithm for
 * finding the maximum flow in a flow network.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Oct 12, 2023.
 */
public class EdmondsKarp {

  /**
   * The main entry point for lunching the algorithm.
   *
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    try {
      printEdmondsKarp(1, "https://www.idi.ntnu.no/emner/idatt2101/v-graf/flytgraf1", 0, 7);
      printEdmondsKarp(2, "https://www.idi.ntnu.no/emner/idatt2101/v-graf/flytgraf2", 0, 1);
      printEdmondsKarp(3, "https://www.idi.ntnu.no/emner/idatt2101/v-graf/flytgraf3", 0, 1);
      printEdmondsKarp(4, "https://www.idi.ntnu.no/emner/idatt2101/v-graf/flytgraf4", 0, 7);
      printEdmondsKarp(5, "https://www.idi.ntnu.no/emner/idatt2101/v-graf/flytgraf5", 0, 7);

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Print information from Edmonds-Karp algorithm.
   *
   * @param graphId The ID of the graph.
   * @param graphFile The URL of the graph file.
   * @param source The source node.
   * @param sink The sink node.
   * @throws IOException If there is an error reading the graph data.
   */
  public static void printEdmondsKarp(int graphId, String graphFile, int source, int sink) throws IOException {

    WeightedGraphTable graph = FileGraphHandler.parseGraphFromWebFile(graphFile);

    System.out.println("\nGraph " + graphId);
    System.out.println("Maximum flow from " + source + " to " + sink + " with Edmond Karp's algorithm.");
    System.out.println("Increase in flow | Flow augmented path ");
    int maxFlow = edmondsKarp(graph, source, sink);
    System.out.println("Maximum flow: " + maxFlow);
  }

  /**
   * Find the maximum flow in a flow network using the Edmonds-Karp algorithm.
   *
   * @param graph The flow network represented as a WeightedGraphTable.
   * @param source The source node.
   * @param sink The sink node.
   * @return The maximum flow in the network.
   */
  public static int edmondsKarp(WeightedGraphTable graph, int source, int sink) {
    int maxFlow = 0;

    while (true) {
      List<Integer> augmentingPath = bfs(graph, source, sink);

      if (augmentingPath.isEmpty()) {
        break; // No more augmenting paths.
      }

      int minCapacity = Integer.MAX_VALUE;
      for (int i = 1; i < augmentingPath.size(); i++) {
        int from = augmentingPath.get(i - 1);
        int to = augmentingPath.get(i);
        int capacity = graph.getEdge()[from][to].getRemainingCapacity();
        minCapacity = Math.min(minCapacity, capacity);
      }

      for (int i = 1; i < augmentingPath.size(); i++) {
        int from = augmentingPath.get(i - 1);
        int to = augmentingPath.get(i);
        graph.updateRemainingEdgeCapacity(from, to, minCapacity);
      }

      maxFlow += minCapacity;

      String formattedOutput = String.format("%8d         | %s", minCapacity, augmentingPath);
      System.out.println(formattedOutput);
    }

    return maxFlow;
  }

  /**
   * Perform a Breadth-First Search (BFS) to find an augmenting path in the flow network.
   *
   * @param graph The flow network represented as a WeightedGraphTable.
   * @param startNode The source node.
   * @param endNode The sink node.
   * @return The augmenting path or an empty list if no path is found.
   */
  public static List<Integer> bfs(WeightedGraphTable graph, int startNode, int endNode) {

    int amountOfNodes = graph.getAmountOfNodes();

    // Initialize BFS queue.
    Queue<Integer> queue = new ArrayDeque<>(amountOfNodes);

    // Array for visited nodes.
    boolean[] visited = new boolean[amountOfNodes];

    // Initialize predecessor array.
    int[] predecessor = new int[amountOfNodes];
    Arrays.fill(predecessor, -1);

    // Mark the starting node as visited and add it to queue.
    visited[startNode] = true;
    queue.add(startNode);

    while (!queue.isEmpty()) {
      int currentNode = queue.poll();
      for (int neighbor = 0; neighbor < amountOfNodes; neighbor++) {
        if (!visited[neighbor] && graph.isEdgeInAugmentingPath(currentNode, neighbor)) {
          visited[neighbor] = true;
          predecessor[neighbor] = currentNode;
          queue.add(neighbor);
        }
      }

      if (currentNode == endNode) {
        // We found an augmenting path.
        // Now we follow the path back to the start node with help from the predecessor array.
        List<Integer> augmentingPath = new ArrayList<>();
        int node = endNode;
        while (node != -1) {
          augmentingPath.add(node);
          node = predecessor[node];
        }
        // Revers the path so that is start from the source node.
        Collections.reverse(augmentingPath);
        return augmentingPath;
      }
    }

    // No augmenting path found.
    return Collections.emptyList();
  }
}




