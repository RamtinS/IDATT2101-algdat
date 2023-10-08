package edu.ntnu.idatt2101;

/**
 * The TopologicalSortStructure class represents a simple structure used
 * in topological sorting algorithms. It consists of two fields: a boolean
 * flag indicating if a node has been visited or found during the sorting process,
 * and a reference to the next node in the sorted order.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Oct 06, 2023.
 */
public class TopologicalSortStructure {
  boolean found;
  Node next;
}
