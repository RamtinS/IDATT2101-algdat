
/**
 * The class implements algorithms for recursively multiplying
 * numbers using different approaches.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Aug 28, 2023.
 */
public class RecursiveMultiplier {

  /**
   * The method recursively multiplies two given numbers using addition.
   *
   * @param n The integer to be multiplied.
   * @param x The decimal to be multiplied.
   * @return The product of the two numbers.
   */
  public static double recursiveMultiply1(int n, double x) {
    if (n == 1) {                                            //Exit condition/Base case.
      return x;
    } else {
      return x + recursiveMultiply1(n - 1, x);            //Recursive step.
    }
  }

  /**
   * The method recursively multiplies two given numbers using addition.
   *
   * @param n The integer to be multiplied.
   * @param x The decimal to be multiplied.
   * @return The product of the two numbers.
   */
  public static double recursiveMultiply2(int n, double x) {
    if (n == 1) {
      return x;
    }
    if ((n & 1) == 1) {
      //Odd
      return recursiveMultiply2((n - 1) / 2, x + x) + x;
    } else {
      //Even
      return recursiveMultiply2(n / 2, x + x);
    }
  }

  /**
   * The main entry point for lunching the algorithm.
   *
   * @param args An array of command-line arguments for the application.
   */
  public static void main(String[] args) {
    testMultiply1(13, 2.5);
    testMultiply1(14, 10.1);

    testMultiply2(13, 2.5);
    testMultiply2(14, 10.1);

    testMultiply1(1000, 22.5);
    testMultiply2(1000, 22.5);

    testMultiply1(2000, 22.5);
    testMultiply2(2000, 22.5);

    testMultiply1(4000, 22.5);
    testMultiply2(4000, 22.5);

    testMultiply1(4800, 22.5);
    testMultiply2(4800, 22.5);
  }

  /**
   * Tests the recursiveMultiply1 method with a specific n and x value,
   * and prints the results along with execution time.
   *
   * @param n The integer value.
   * @param x The decimal value.
   */
  public static void testMultiply1(int n, double x) {
    long startTime = System.nanoTime();
    double product = recursiveMultiply1(n, x);
    long endTime = System.nanoTime();
    long time = endTime - startTime;

    System.out.println("\n------ Recursive Multiply 1 ------"
            + "\nInteger n: " + n
            + "\nDecimal x: " + x
            + "\nProduct: " + product
            + "\nTime: " + time + " ns.");
  }

  /**
   * Tests the recursiveMultiply2 method with a specific n and x value,
   * and prints the results along with execution time.
   *
   * @param n The integer value.
   * @param x The decimal value.
   */
  public static void testMultiply2(int n, double x) {
    long startTime = System.nanoTime();
    double product = recursiveMultiply2(n, x);
    long endTime = System.nanoTime();
    long time = endTime - startTime;

    System.out.println("\n------ Recursive Multiply 2 ------"
            + "\nInteger n: " + n
            + "\nDecimal x: " + x
            + "\nProduct: " + product
            + "\nTime: " + time + " ns.");
  }
}