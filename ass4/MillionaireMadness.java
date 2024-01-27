import java.util.*;
import java.io.*;

public class MillionaireMadness {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());
    int m = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    int[][] grid = new int[m][n];
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited = new boolean[m][n];
    PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> a.diff - b.diff);
    pq.add(new Node(0, 0, grid[0][0], 0));
    int minlength = 0;
    while (!pq.isEmpty()) {
      Node curr = pq.poll();
      if (curr.x == m - 1 && curr.y == n - 1) {
        minlength = curr.diff;
        break;
      }
      if (visited[curr.x][curr.y]) continue;
      visited[curr.x][curr.y] = true;
      for (int[] d : dir) {
        int nx = curr.x + d[0];
        int ny = curr.y + d[1];
        if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) continue;
        int diff = Math.max(curr.diff, grid[nx][ny] - curr.height);
        pq.add(new Node(nx, ny, grid[nx][ny], diff));
      }
    }

    pw.println(minlength<=0?0:minlength);
    pw.close();
  }
}

class Node {
  int x;
  int y;
  int height;
  int diff;
  public Node(int x, int y, int height, int diff) {
    this.x = x;
    this.y = y;
    this.height = height;
    this.diff = diff;
  }
}
