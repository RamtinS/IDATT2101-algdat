import java.util.Random;

/**
 * The class represents an algorithm for finding the maximum profit that
 * can be obtained by buying and selling a stock, within a given set of
 * stock price changes over time.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Aug 24, 2023.
 */
public class StockProfitCalculator {

  /**
   * The method calculates the maximum profit that can be
   * obtained by buying and selling a stock based on the
   * provided array of stock price changes.
   *
   * @param stockChange an array of the stock price changes over time.
   * @return an array containing information about the result.
   */
  public static int[] calculateMaxStockProfit(int[] stockChange) {
    int currentProfit;
    int maxProfit = 0;
    int buyDate = 0;
    int sellDate = 0;

    for (int i = 0; i < stockChange.length - 1; i++) { //O(n)
      currentProfit = 0;

      for (int j = i + 1; j < stockChange.length; j++) { //O(n)
        currentProfit += stockChange[j];

        if (currentProfit > maxProfit) {
          maxProfit = currentProfit;
          buyDate = i + 1;
          sellDate = j + 1;
        }
      }
    }

    int[] array = new int[4];
    array[0] = stockChange.length;
    array[1] = maxProfit;
    array[2] = buyDate;
    array[3] = sellDate;

    return array;
  }

  /**
   * The method lunches the algorithm.
   *
   * @param args an array of command-line arguments for the application.
   */
  public static void main(String[] args) {
    //Test data.
    int[] stockChange1 = {-1, 3, -9, 2, 2, -1, 2, -1, -5};

    int[] stockChange2 = {-1, 3, -9, -40, 2, -1, 2, -1, 100, -4};

    Random random = new Random();
    int[] stockChange3 = new int[100000];
    for (int i = 0; i < stockChange3.length; i++) {
      stockChange3[i] = random.nextInt(200) - 100;
    }

    //Start time.
    long startTime = System.currentTimeMillis();

    //Algorithm.
    int[] array = calculateMaxStockProfit(stockChange3);

    //End time.
    long endTime = System.currentTimeMillis();

    //Time used.
    long time = endTime - startTime;


    System.out.println("------------------------");
    System.out.println("Elements: " + array[0]);
    System.out.println("Max profit: " + array[1]);
    System.out.println("Buy date: " + array[2]);
    System.out.println("Sell date: " + array[3]);
    System.out.println("Time: " + time + " ms.");
    System.out.println("------------------------");
  }
}