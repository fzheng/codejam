package class7_8;

import java.util.LinkedList;

public class Problem_03_ExpressionCompute {

  public static int getValue(String str) {
    return value(str.toCharArray(), 0)[0];
  }

  public static int[] value(char[] str, int i) {
    LinkedList<String> que = new LinkedList<>();
    int pre = 0;
    int[] bra;
    while (i < str.length && str[i] != ')') {
      if (str[i] >= '0' && str[i] <= '9') {
        pre = pre * 10 + str[i++] - '0';
      } else if (str[i] != '(') {
        addNum(que, pre);
        que.addLast(String.valueOf(str[i++]));
        pre = 0;
      } else {
        bra = value(str, i + 1);
        pre = bra[0];
        i = bra[1] + 1;
      }
    }
    addNum(que, pre);
    return new int[]{getNum(que), i};
  }

  public static void addNum(LinkedList<String> que, int num) {
    if (!que.isEmpty()) {
      int cur;
      String top = que.pollLast();
      if (top.equals("+") || top.equals("-")) {
        que.addLast(top);
      } else {
        cur = Integer.parseInt(que.pollLast());
        num = top.equals("*") ? (cur * num) : (cur / num);
      }
    }
    que.addLast(String.valueOf(num));
  }

  public static int getNum(LinkedList<String> que) {
    int res = 0;
    boolean add = true;
    String cur;
    int num;
    while (!que.isEmpty()) {
      cur = que.pollFirst();
      switch (cur) {
        case "+":
          add = true;
          break;
        case "-":
          add = false;
          break;
        default:
          num = Integer.parseInt(cur);
          res += add ? num : (-num);
          break;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    String exp = "48*((70-65)-43)+8*1";
    System.out.println(getValue(exp));

    exp = "4*(6+78)+53-9/2+45*8";
    System.out.println(getValue(exp));

    exp = "10-5*3";
    System.out.println(getValue(exp));

    exp = "-3*4";
    System.out.println(getValue(exp));

    exp = "3+1*4";
    System.out.println(getValue(exp));

  }

}
