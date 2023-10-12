import java.util.Random;

/**
 * The class provides methods to perform quicksort algorithm on an array.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Sep 07, 2023.
 */
public class SortingAlgorithm {
  private static final int insertionThreshold = 300;
  private static final int elementsInArray = 1000000;

  /**
   * The method sorts an integer array using the quicksort
   * algorithm aided by a helper algorithm.
   *
   * @param array The integer array to be sorted.
   * @param lowIndex The index of the first element in the range to be sorted.
   * @param highIndex The index of the last element in the range to be sorted.
   */
  public static void quicksortWithHelperMethod(int[] array, int lowIndex, int highIndex) {
    if (highIndex - lowIndex > insertionThreshold) {
      int pivot = partition(array, lowIndex, highIndex);
      quicksortWithHelperMethod(array, lowIndex, pivot - 1);
      quicksortWithHelperMethod(array, pivot + 1, highIndex);
    } else {
      insertionSort(array, lowIndex, highIndex);
    }
  }

  /**
   * The method sorts an integer array using the quicksort algorithm.
   *
   * @param array The integer array to be sorted.
   * @param lowIndex The index of the first element in the range to be sorted.
   * @param highIndex The index of the last element in the range to be sorted.
   */
  public static void quicksort(int[] array, int lowIndex, int highIndex) {
    if (highIndex - lowIndex > 2) {
      int pivot = partition(array, lowIndex, highIndex);
      quicksort(array, lowIndex, pivot - 1);
      quicksort(array, pivot + 1, highIndex);
    } else {
      median3sort(array, lowIndex, highIndex);
    }
  }

  /**
   * Partitions an array for the quicksort algorithm.
   * The method selects a pivot element and rearranges the elements in
   * the array so that elements less than the pivot are on the left side,
   * and elements greater than the pivot are on the right side.
   *
   * @param array The integer array to be partitioned.
   * @param lowIndex The index of the first element in the range to be partitioned.
   * @param highIndex The index of the last element in the range to be partitioned.
   * @return The index of the pivot element after partitioning.
   */
  private static int partition(int[] array, int lowIndex, int highIndex) {
    int leftIndex;
    int rightIndex;
    int median = median3sort(array, lowIndex, highIndex);
    int pivotValue = array[median];
    swap(array, median, highIndex - 1);
    for (leftIndex = lowIndex, rightIndex = highIndex - 1;;) {
      while (array[++leftIndex] < pivotValue);
      while (array[--rightIndex] > pivotValue);
      if (leftIndex >= rightIndex) break;
      swap(array, leftIndex, rightIndex);
    }
    swap(array, leftIndex, highIndex - 1);
    return leftIndex;
  }

  /**
   * This method selects the median value among three
   * values in the array to improve pivot selection for quicksort.
   *
   * @param array The integer array containing the values.
   * @param lowIndex The index of the first value.
   * @param highIndex The index of the last value.
   * @return The index of the median value among the three.
   */
  private static int median3sort(int[] array, int lowIndex, int highIndex) {
    int median = (lowIndex + highIndex) / 2;
    if (array[lowIndex] > array[median]) {
      swap(array, lowIndex, median);
    }
    if (array[median] > array[highIndex]) {
      swap(array, median, highIndex);
      if (array[lowIndex] > array[median]) {
        swap(array, lowIndex, median);
      }
    }
    return median;
  }

  /**
   * The method sorts an integer array using the insertion sort algorithm.
   *
   * @param array The integer array to be sorted.
   * @param lowIndex The index of the first element in the range to be sorted.
   * @param highIndex The index of the last element in the range to be sorted.
   */
  public static void insertionSort(int[] array, int lowIndex, int highIndex) {
    for (int i = lowIndex + 1; i <= highIndex; i++) {
      int swap = array[i];
      int j = i - 1;
      while (j >= lowIndex && array[j] > swap) {
        array[j + 1] = array[j];
        --j;
      }
      array[j + 1] = swap;
    }
  }

  /**
   * The method swaps two elements in an integer array.
   *
   * @param array The array in which elements are to be swapped.
   * @param i The index of the first element to be swapped.
   * @param j The index of the second element to be swapped.
   */
  public static void swap(int[] array, int i, int j) {
    int temp = array[j];
    array[j] = array[i];
    array[i] = temp;
  }

  /**
   * The main entry point for lunching the algorithm.
   *
   * @param args An array of command-line arguments for the application.
   */
  public static void main(String[] args) {
    for (int i = 0; i < 40; i++) {
      int[] array = generateArray();
      quicksortWithHelperMethod(array, 0, array.length - 1);
    }

    //The array to be sorted.
    int[] numbers = generateArray();
    int[] numbersCopy = numbers.clone();

    //Quicksort with helper method.
    int sumBeforeSort = calculateArraySum(numbers);
    long startTime = System.currentTimeMillis();
    quicksortWithHelperMethod(numbers, 0, numbers.length - 1);
    long endTime = System.currentTimeMillis();
    long time = endTime - startTime;
    int sumAfterSort = calculateArraySum(numbers);
    boolean isSumEqual = sumBeforeSort == sumAfterSort;
    printSortResults("Quicksort with helper method.",
            insertionThreshold, time, numbers, isSumEqual);

    //Quicksort without helper method.
    int sumBeforeSort2 = calculateArraySum(numbersCopy);
    long startTime2 = System.currentTimeMillis();
    quicksort(numbersCopy, 0, numbersCopy.length - 1);
    long endTime2 = System.currentTimeMillis();
    long time2 = endTime2 - startTime2;
    int sumAfterSort2 = calculateArraySum(numbersCopy);
    boolean isSumEqual2 = sumBeforeSort2 == sumAfterSort2;
    printSortResults("Quicksort without helper method.",
            0, time2, numbersCopy, isSumEqual2);

    //Quicksort with helper method on sorted list.
    int sumBeforeSort3 = calculateArraySum(numbersCopy);
    long startTime3 = System.currentTimeMillis();
    quicksortWithHelperMethod(numbers, 0, numbers.length - 1);
    long endTime3 = System.currentTimeMillis();
    long time3 = endTime3 - startTime3;
    int sumAfterSort3 = calculateArraySum(numbers);
    boolean isSumEqual3 = sumBeforeSort3 == sumAfterSort3;
    printSortResults("Quicksort with helper method on sorted array.",
            insertionThreshold, time3, numbers, isSumEqual3);

    //Quicksort without helper method on sorted list.
    int sumBeforeSort4 = calculateArraySum(numbersCopy);
    long startTime4 = System.currentTimeMillis();
    quicksort(numbersCopy, 0, numbersCopy.length - 1);
    long endTime4 = System.currentTimeMillis();
    long time4 = endTime4 - startTime4;
    int sumAfterSort4 = calculateArraySum(numbersCopy);
    boolean isSumEqual4 = sumBeforeSort4 == sumAfterSort4;
    printSortResults("Quicksort without helper method on sorted array.",
            0, time4, numbersCopy, isSumEqual4);
  }

  /**
   * Print the results of the sorting operation.
   *
   * @param title Description for the sorting operation.
   * @param insertionThreshold The threshold value.
   * @param time The time taken for the sorting operation in milliseconds.
   * @param numbers Array of integers to be sorted.
   * @param isSumEqual Indicates whether the sum of array elements remains unchanged after sorting
   */
  public static void printSortResults(String title, int insertionThreshold,
                                      long time, int[] numbers, boolean isSumEqual) {
    System.out.println("\n" + title
            + "\nElements: " + elementsInArray
            + (insertionThreshold > 0 ? "\nThreshold: " + insertionThreshold : "")
            + "\nTime: " + time + " ms."
            + "\nSorted: " + checkSorted(numbers)
            + "\nEqual sum before and after sorting: " + isSumEqual);
  }

  /**
   * Generates an integer array with a mix of random numbers and the value 42.
   *
   * @return An array of random integers.
   */
  private static int[] generateArray() {
    Random random = new Random();
    int[] numbers = new int[elementsInArray];

    for (int i = 0; i < numbers.length; i++) {
      if (i % 2 == 0) {
        numbers[i] = random.nextInt(10000);
      } else {
        numbers[i] = 42;
      }
    }
    return numbers;
  }

  /**
   * The method checks if an integer array is sorted.
   *
   * @param array The integer array to be checked for sorted order.
   */
  private static boolean checkSorted(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] > array[i + 1]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Calculates the sum of all the elements in an integer array.
   *
   * @param array The integer array for which the sum needs to be calculated.
   * @return The sum of all the elements in the provided array.
   */
  private static int calculateArraySum(int[] array) {
    int sum = 0;
    for (int num : array) {
      sum += num;
    }
    return sum;
  }
}