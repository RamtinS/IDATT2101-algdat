package edu.ntnu.idatt2101.filehandling;

import edu.ntnu.idatt2101.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileGraphHandler {

  public static Graph parseGraphFromFile(String pathOfFile) throws IOException {

    try (BufferedReader reader = new BufferedReader(
            new FileReader(pathOfFile.toLowerCase().trim()))) {

      Graph graph = new Graph();
      graph.newGraph(reader);
      return graph;

    } catch (IOException e) {
     String errorMessage = "Error reading graph from file: " + e.getMessage();
     throw new IOException(errorMessage);
    }
  }
}

