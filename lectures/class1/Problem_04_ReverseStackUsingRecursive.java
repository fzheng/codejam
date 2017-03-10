package class1;

import java.util.Stack;

public class Problem_04_ReverseStackUsingRecursive {

  public static void reverse(Stack<Integer> stack) {
    if (stack.isEmpty()) {
      return;
    }
    int i = getAndRemoveLastElement(stack);
    reverse(stack);
    stack.push(i);
  }

  public static int getAndRemoveLastElement(Stack<Integer> stack) {
    int element = stack.pop();
    if (stack.isEmpty()) {
      return element;
    }
    int last = getAndRemoveLastElement(stack);
    stack.push(element);
    return last;
  }

  public static void main(String[] args) {
    Stack<Integer> test = new Stack<>();
    test.push(1);
    test.push(2);
    test.push(3);
    test.push(4);
    test.push(5);
    reverse(test);
    while (!test.isEmpty()) {
      System.out.println(test.pop());
    }
  }
}
