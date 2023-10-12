package edu.ntnu.idatt2101.filehandling;

import edu.ntnu.idatt2101.WeightedGraphTable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * The FileGraphHandler class provides methods for
 * parsing graphs from files and web resources.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Oct 12, 2023.
 */
public class FileGraphHandler {

  /**
   * Parses a graph from a local file.
   *
   * @param pathOfFile The path to the local file containing the graph data.
   * @return The parsed Graph object.
   * @throws IOException If there is an error reading the file or parsing the graph.
   */
  public static WeightedGraphTable parseGraphFromFile(String pathOfFile) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(pathOfFile.toLowerCase().trim()))) {

      WeightedGraphTable graph = new WeightedGraphTable();
      graph.newGraph(reader);
      return graph;

    } catch (IOException e) {
      String errorMessage = "Error reading graph from file: " + e.getMessage();
      throw new IOException(errorMessage);
    }
  }

  /**
   * Parses a graph from a web resource.
   *
   * @param webURL The URL of the web resource containing the graph data.
   * @return The parsed Graph object.
   * @throws IOException If there is an error reading the web resource or parsing the graph.
   */
  public static WeightedGraphTable parseGraphFromWebFile(String webURL) throws IOException {
    try {
      URL url = new URL(webURL);
      URLConnection connection = url.openConnection();
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

      WeightedGraphTable graph = new WeightedGraphTable();
      graph.newGraph(reader);

      reader.close();

      return graph;
    } catch (IOException e) {
      String errorMessage = "Error reading graph from web: " + e.getMessage();
      throw new IOException(errorMessage);
    }
  }
}