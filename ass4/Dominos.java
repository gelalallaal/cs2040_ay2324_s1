import java.util.*;
import java.io.*;

public class Dominos {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    int t = Integer.parseInt(br.readLine());
    for (int i = 0; i<t; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      ArrayList<Boolean> hasInDegree = new ArrayList<Boolean>(n);
      ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n);
      ArrayList<Boolean> visited = new ArrayList<Boolean>(n);
      for (int j = 0; j < n; j++) {
        hasInDegree.add(false);
        visited.add(false);
        adj.add(new ArrayList<Integer>());
      }
      for (int j = 0; j<m; j++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        hasInDegree.set(y, true);
        adj.get(x).add(y);
        adj.get(y).add(x);
      }
      int count = 0;
      for (int j = 0; j < n; j++) {
        if (!hasInDegree.get(j)) {
          count++;
          dfs(j, visited, adj);
        }
      }
      for (int j = 0; j < n; j++) {
        if (!visited.get(j)) {
          count++;
          dfs(j, visited, adj);
        }
      }
      pw.println(count);
    }
    pw.close();
    br.close();
  }
  static void dfs(int x, ArrayList<Boolean> visited, ArrayList<ArrayList<Integer>> adj) {
    visited.set(x, true);
    for (int i = 0; i < adj.get(x).size(); i++) {
      if (!visited.get(adj.get(x).get(i))) {
        dfs(adj.get(x).get(i), visited, adj);
      }
    }
  }
}

