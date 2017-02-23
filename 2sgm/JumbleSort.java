import java.util.*;

public class JumbleSort {
  public static void main(String[] args) {
    List<Integer> intList = new ArrayList<>();
    List<String> strList = new ArrayList<>();
    System.out.println("Input: ");
    Scanner sc = new Scanner(System.in, "UTF-8");
    if (sc.hasNextLine()) {
      String[] items = sc.nextLine().split("\\s+");
      int itemsLength = items.length;
      boolean[] isNumber = new boolean[itemsLength];
      for (int i = 0; i < itemsLength; i++) {
        if (items[i].matches("-?\\d+")) {
          intList.add(Integer.parseInt(items[i]));
          isNumber[i] = true;
        } else {
          strList.add(items[i]);
          isNumber[i] = false;
        }
      }
      Collections.sort(intList);
      Collections.sort(strList);
      System.out.println("Output: ");
      Iterator<Integer> iteratorInt = intList.iterator();
      Iterator<String> iteratorStr = strList.iterator();
      for (int i = 0; i < itemsLength; i++) {
        if (isNumber[i]) {
          System.out.print(iteratorInt.next());
        } else {
          System.out.print(iteratorStr.next());
        }
        System.out.print(" ");
      }
      System.out.println();
    }
    sc.close();
  }
}