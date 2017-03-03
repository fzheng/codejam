package class3;

import java.util.HashMap;
import java.util.Map;

public class Problem_06_BiggestBSTTopologyInTree {

  public static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }

  public static int bstTopologySize1(Node head) {
    if (head == null) {
      return 0;
    }
    int max = maxTopology(head, head);
    max = Math.max(bstTopologySize1(head.left), max);
    max = Math.max(bstTopologySize1(head.right), max);
    return max;
  }

  public static int maxTopology(Node h, Node n) {
    if (h != null && n != null && isBSTNode(h, n, n.value)) {
      return maxTopology(h, n.left) + maxTopology(h, n.right) + 1;
    }
    return 0;
  }

  public static boolean isBSTNode(Node h, Node n, int value) {
    return h != null && (h == n || isBSTNode(h.value > value ? h.left : h.right, n, value));
  }

  public static class Record {
    public int l;
    public int r;

    public Record(int left, int right) {
      this.l = left;
      this.r = right;
    }
  }

  public static int bstTopologySize2(Node head) {
    Map<Node, Record> map = new HashMap<>();
    return posOrder(head, map);
  }

  public static int posOrder(Node h, Map<Node, Record> map) {
    if (h == null) {
      return 0;
    }
    int ls = posOrder(h.left, map);
    int rs = posOrder(h.right, map);
    modifyMap(h.left, h.value, map, true);
    modifyMap(h.right, h.value, map, false);
    Record lr = map.get(h.left);
    Record rr = map.get(h.right);
    int lBst = lr == null ? 0 : lr.l + lr.r + 1;
    int rBst = rr == null ? 0 : rr.l + rr.r + 1;
    map.put(h, new Record(lBst, rBst));
    return Math.max(lBst + rBst + 1, Math.max(ls, rs));
  }

  public static int modifyMap(Node n, int v, Map<Node, Record> m, boolean s) {
    if (n == null || (!m.containsKey(n))) {
      return 0;
    }
    Record r = m.get(n);
    if ((s && n.value > v) || ((!s) && n.value < v)) {
      m.remove(n);
      return r.l + r.r + 1;
    } else {
      int minus = modifyMap(s ? n.right : n.left, v, m, s);
      if (s) {
        r.r = r.r - minus;
      } else {
        r.l = r.l - minus;
      }
      m.put(n, r);
      return minus;
    }
  }

  // for test -- print tree
  public static void printTree(Node head) {
    System.out.println("Binary Tree:");
    printInOrder(head, 0, "H", 17);
    System.out.println();
  }

  public static void printInOrder(Node head, int height, String to, int len) {
    if (head == null) {
      return;
    }
    printInOrder(head.right, height + 1, "v", len);
    String val = to + head.value + to;
    int lenM = val.length();
    int lenL = (len - lenM) / 2;
    int lenR = len - lenM - lenL;
    val = getSpace(lenL) + val + getSpace(lenR);
    System.out.println(getSpace(height * len) + val);
    printInOrder(head.left, height + 1, "^", len);
  }

  public static String getSpace(int num) {
    String space = " ";
    StringBuilder buf = new StringBuilder("");
    for (int i = 0; i < num; i++) {
      buf.append(space);
    }
    return buf.toString();
  }

  public static void main(String[] args) {
    Node head = new Node(6);
    head.left = new Node(1);
    head.left.left = new Node(0);
    head.left.right = new Node(3);
    head.right = new Node(12);
    head.right.left = new Node(10);
    head.right.left.left = new Node(4);
    head.right.left.left.left = new Node(2);
    head.right.left.left.right = new Node(5);
    head.right.left.right = new Node(14);
    head.right.left.right.left = new Node(11);
    head.right.left.right.right = new Node(15);
    head.right.right = new Node(13);
    head.right.right.left = new Node(20);
    head.right.right.right = new Node(16);
    printTree(head);

    System.out.println(bstTopologySize1(head));
    System.out.println(bstTopologySize2(head));

  }

}
