package class1;

import java.util.LinkedList;
import java.util.Random;

public class Problem_12_AllLessNumSubArray {

  public static int getNum(int[] arr, int num) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    LinkedList<Integer> qMin = new LinkedList<>();
    LinkedList<Integer> qMax = new LinkedList<>();
    int i = 0;
    int j = 0;
    int res = 0;
    while (i < arr.length) {
      while (j < arr.length) {
        while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[j]) {
          qMin.pollLast();
        }
        qMin.addLast(j);
        while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[j]) {
          qMax.pollLast();
        }
        qMax.addLast(j);
        if (arr[qMax.getFirst()] - arr[qMin.getFirst()] > num) {
          break;
        }
        j++;
      }
      if (qMin.peekFirst() == i) {
        qMin.pollFirst();
      }
      if (qMax.peekFirst() == i) {
        qMax.pollFirst();
      }
      res += j - i;
      i++;
    }
    return res;
  }

  // for test
  public static int[] getRandomArray(int len) {
    Random random = new Random();
    if (len < 0) {
      return null;
    }
    int[] arr = new int[len];
    for (int i = 0; i < len; i++) {
      arr[i] = random.nextInt(10);
    }
    return arr;
  }

  // for test
  public static void printArray(int[] arr) {
    if (arr != null) {
      for (int anArr : arr) {
        System.out.print(anArr + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[] arr = getRandomArray(30);
    int num = 5;
    printArray(arr);
    System.out.println(getNum(arr, num));

  }

}
