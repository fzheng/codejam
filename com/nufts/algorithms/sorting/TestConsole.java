import com.nufts.algorithms.sorting.MergeSortTopDown;
import com.nufts.algorithms.sorting.QuickSort;
import com.nufts.algorithms.sorting.RSelection;
import com.nufts.algorithms.sorting.SelectionSort;

import java.util.Arrays;
import java.util.Random;

public class TestConsole {
  private static int N;
  private static boolean showArray;

  public static void main(String[] args) {
    if (args.length != 2)
      throw new IllegalArgumentException("missing array size and showArray");
    N = Integer.parseInt(args[0]);
    if (N <= 0)
      throw new IllegalArgumentException("invalid array size");
    showArray = Boolean.parseBoolean(args[1]);
    run();
  }

  private static void run() {
    Comparable[] x = randIntArray();
    long startTime, totalTime;
    print(x);
    startTime = System.currentTimeMillis();
    SelectionSort.sort(x);
    totalTime = System.currentTimeMillis() - startTime;
    print(x);
    System.out.println("Selectionsort: " + totalTime + " millisec");

    x = randIntArray();
    print(x);
    startTime = System.currentTimeMillis();
    MergeSortTopDown.sort(x);
    totalTime = System.currentTimeMillis() - startTime;
    print(x);
    System.out.println("Mergesort Top Down: " + totalTime + " millisec");

    x = randIntArray();
    print(x);
    startTime = System.currentTimeMillis();
    QuickSort.sort(x);
    totalTime = System.currentTimeMillis() - startTime;
    print(x);
    System.out.println("Quicksort: " + totalTime + " millisec");

    x = randIntArray();
    print(x);
    startTime = System.currentTimeMillis();
    Comparable element = RSelection.getRank(x, x.length / 2 + 1);
    totalTime = System.currentTimeMillis() - startTime;
    print(element);
    QuickSort.sort(x);
    print(x);
    System.out.println("Quick Selection: " + totalTime + " millisec");
  }

  private static void print(Comparable[] x) {
    if (showArray) System.out.println(Arrays.toString(x));
  }

  private static void print(Comparable x) {
    System.out.println(x);
  }

  private static Comparable[] randIntArray() {
    Comparable[] x = new Integer[N];
    int i = 0;
    Random generator = new Random();
    while (i < N) {
      x[i++] = generator.nextInt(2 * N);
    }
    return x;
  }
}