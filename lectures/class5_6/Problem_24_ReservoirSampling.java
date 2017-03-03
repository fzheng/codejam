package class5_6;

import java.util.Random;

public class Problem_24_ReservoirSampling {

  public static int rand(int max) {
    Random random = new Random();
    return random.nextInt(max) + 1;
  }

  public static int[] getKNumsRand(int k, int max) {
    if (max < 1 || k < 1) {
      return null;
    }
    int[] res = new int[Math.min(k, max)];
    for (int i = 0; i != res.length; i++) {
      res[i] = i + 1;
    }
    for (int i = k + 1; i < max + 1; i++) {
      if (rand(i) <= k) {
        res[rand(k) - 1] = i;
      }
    }
    return res;
  }

  public static void printArray(int[] arr) {
    for (int i = 0; i != arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[] res = getKNumsRand(10, 10000);
    printArray(res);
  }

}
