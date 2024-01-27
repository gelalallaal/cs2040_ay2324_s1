import java.util.*;
import java.io.*;

class Conform {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    int n = Integer.parseInt(br.readLine());
    HashMap<Long, Integer> map = new HashMap<Long, Integer>();
    for (int i = 0; i < n; i++) {
      ArrayList<Integer> subjects = new ArrayList<Integer>();
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 5; j++) {
        int m = Integer.parseInt(st.nextToken());
        subjects.add(m);
      }
      Collections.sort(subjects);
      long key = 0L;
      for (int j = 0; j < 5; j++) {
        key = key + subjects.get(j) * (long) Math.pow(10, j*3);
      }
      if (map.containsKey(key)) {
        map.replace(key, map.get(key) + 1);
      } else {
        map.put(key, 1);
      }
    }

    int max = 0;
    int count = 0;
    for (int i : map.values()) {
      if (i > max) {
        max = i;
        count = i;
      }else if (i == max) {
        count += i;
      }
    }
    if (count == 1) {
      pw.println(n);
    } else {
      pw.println(count);
    }
    pw.flush();
    pw.close();
    br.close();
  }
}
