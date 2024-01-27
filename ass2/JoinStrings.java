import java.util.*;
import java.io.*;

public class JoinStrings {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    int n = Integer.parseInt(br.readLine());
    SLL[] strings = new SLL[n];
    for (int i = 0; i < n; i++) {
      strings[i] = new SLL(br.readLine());
    }
    int last = 0;
    for (int i = 0; i < n -1; i++) {
      String[] parts =br.readLine().split(" ");
      last = Integer.parseInt(parts[0]) - 1;
      int op2 = Integer.parseInt(parts[1]) - 1;
      strings[last].join(strings[op2]);
      strings[op2] = new SLL("");
    }
    Node curr = strings[last].head;
    while (curr != null) {
      pw.print(curr.value);
      curr = curr.next;
    }
    pw.flush();
    pw.close();
    br.close();
  }

  static class Node {
    String value;
    Node next;
    Node(String value) {
      this.value = value;
      this.next = null;
    }
  }

  static class SLL {
    Node head;
    Node tail;

    public SLL(String s) {
      this.head = new Node(s);
      this.tail = this.head;
    }

    public void join(SLL other) {
      this.tail.next = other.head;
      this.tail = other.tail;
    }
  }
}
