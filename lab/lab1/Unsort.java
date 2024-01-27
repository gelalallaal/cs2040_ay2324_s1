import java.util.*;

public class Unsort {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList<Integer> list = new ArrayList<Integer>();
    while (sc.hasNextInt()) {
      list.add(sc.nextInt());
    }

    int size = list.size();
    for (int i = 0; i < size - 2; i++) {
      int a = list.get(i);
      int b = list.get(i + 1);
      int c = list.get(i + 2);
      if (a > b && b > c) {
        list.set(i + 1, c);
        list.set(i + 2, b);
      } else if (a < b && b < c) {
        list.set(i+1, c);
        list.set(i+2, b);
      } else {
        continue;
      }
      // a > b? b > c? (list.set(i+1, c), list.set(i+2, b)) : continue :
      // a < b? b < c? (list.set(i+1, c), list.set(i+2, b)) : continue : continue;
    }

    System.out.println(list);
  }
}
