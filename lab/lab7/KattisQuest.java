import java.util.*;
import java.io.*;

public class KattisQuest {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    TreeMap<Long, PriorityQueue<Long>> map = new TreeMap<>();
    int n = Integer.parseInt(br.readLine());

    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      if (st.nextToken().equals("add")) {
        long e = Long.parseLong(st.nextToken());
        long g = Long.parseLong(st.nextToken());
        if (!map.containsKey(e)) {
          map.put(e, new PriorityQueue<>(Collections.reverseOrder()));
        }
        map.get(e).add(g);
      } else {
        long x = Long.parseLong(st.nextToken());
        long sum = (long) 0;
        while (x >= 0 && !map.isEmpty() && map.floorKey(x) != null) {
          long e = map.floorKey(x);
          long g = map.get(e).poll();
          sum += g;
          x -= e;
          if (map.get(e).isEmpty()) {
            map.remove(e);
          }
        }
        pw.println(sum);
      }
    }
    pw.close();
    br.close();
  }
}
