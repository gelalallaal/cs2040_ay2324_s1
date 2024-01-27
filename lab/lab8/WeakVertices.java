import java.util.*;
import java.io.*;

public class WeakVertices {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    while (true) {
      int n = Integer.parseInt(br.readLine());
      if (n == -1) break;
      int[][] matrix = new int[n][n];
      for (int i = 0; i < n; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < n; j++) {
          matrix[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      int[] stable = new int[n];
      for (int i = 0; i <n ; i++) {
        for (int j = 0; j < n; j++) {
          for (int k = 0; k < n; k++) {
            if (i != j && j != k && i!= k && matrix[i][j] ==1 && matrix[j][k] == 1 && matrix[k][i] == 1) {
              stable[i] = 1;
              stable[j] = 1;
              stable[k] = 1;
            }
          }
        }
      }
      for (int i = 0; i < n; i++) {
        if (stable[i] == 0) {
          pw.print(i + " ");
        }
      }
      pw.println();
    }
    pw.close();
    br.close();
  }
}
