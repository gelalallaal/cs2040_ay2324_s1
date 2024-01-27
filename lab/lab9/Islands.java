import java.util.*;
import java.io.*;

public class Islands {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());
    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    char[][] grid = new char[r][c];
    for (int i = 0; i < r; i++) {
      grid[i] = br.readLine().toCharArray();
    }

    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    Boolean[][] visited = new Boolean[r][c];
    for (int i = 0; i < r; i++) {
      Arrays.fill(visited[i], false);
    }
    int result = 0;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++){
        if (grid[i][j] == 'L' && !visited[i][j]) {
          //BFS
          result++;
          LinkedList<Vertex> queue = new LinkedList<Vertex>();
          visited[i][j] = true;
          queue.add(new Vertex(i, j));
          while (!queue.isEmpty()) {
            Vertex next = queue.poll();
            for (int k = 0; k < 4; k++) {
              try {
                int nextR = next.r + directions[k][0];
                int nextC = next.c + directions[k][1];
                if (grid[nextR][nextC] != 'W' && !visited[nextR][nextC]) {
                  visited[nextR][nextC] = true;
                  queue.add(new Vertex(nextR, nextC));
                } else if (grid[i][j] == 'W') {
                  visited[i][j] = true;
                }
              } catch (ArrayIndexOutOfBoundsException e) {
                continue;
              }
            }
          }
        }
      }
    }
    pw.println(result);
    pw.close();
  }
}

class Vertex {
  int r;
  int c;

  Vertex(int r, int c) {
    this.r = r;
    this.c = c;
  }
}
