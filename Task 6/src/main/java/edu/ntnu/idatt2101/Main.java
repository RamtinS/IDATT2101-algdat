package edu.ntnu.idatt2101;

import edu.ntnu.idatt2101.filehandling.FileGraphHandler;
import java.io.IOException;

/**
 * The Main class contains the main entry point for executing
 * breadth-first search and topological sort operations on a graph.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Oct 06, 2023.
 */
public class Main {

  /**
   * The main method of the application.
   *
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    try {
      executeBreadthFirstSearch("src/main/resources/graph1.txt", 5);
      executeTopologicalSort("src/main/resources/graph5.txt");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Executes breadth-first search on a graph.
   *
   * @param graphFile The file path of the graph to be loaded.
   * @param start The starting node for the breadth-first search.
   * @throws IOException If there is an error reading the graph file.
   */
  private static void executeBreadthFirstSearch(String graphFile, int start) throws IOException {
    System.out.println("\nBreadth first search");

    // Create graph.
    Graph graph = FileGraphHandler.parseGraphFromFile(graphFile);

    // Choose a start node for BFS.
    Node startNode = graph.getNode()[start];
    System.out.println("\nStart node: " + startNode + "\n");

    // Perform BFS to calculate distances and predecessors.
    graph.breadthFirstSearch(startNode);

    // Print table headlines.
    System.out.println("Node | Predecessor  | Distance");

    // Print distances and predecessors.
    for (int i = 0; i < graph.getAmountOfNodes(); i++) {
      Node currentNode = graph.getNode()[i];
      Predecessor predecessor = (Predecessor) currentNode.getD();

      System.out.printf("%-4d | %-12s | %-8d%n", i,
              predecessor.getPredecessor() != null ? predecessor.getPredecessor().toString() : "-",
              predecessor.getDistance());
    }
  }

  /**
   * Executes topological sort on a graph.
   *
   * @param graphFile The file path of the graph to be loaded.
   * @throws IOException If there is an error reading the graph file.
   */
  private static void executeTopologicalSort(String graphFile) throws IOException {
    // Create graph.
    Graph graph = FileGraphHandler.parseGraphFromFile(graphFile);

    // Perform topological sorting.
    Node currentNode = graph.topologicalSorting();

    // Print the sorted nodes.
    System.out.println("\nTopological Sorting:");

    while (currentNode != null) {
      System.out.print(currentNode.getValue() + " ");
      currentNode = ((TopologicalSortStructure) currentNode.getD()).next;
    }
  }
}