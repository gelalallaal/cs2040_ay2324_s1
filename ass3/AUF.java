import java.util.*;
import java.io.*;

public class AUF {
  public static void main(String[] args) throws IOException {
    Kattio io = new Kattio(System.in, System.out);

    while (io.hasMoreTokens()) {
      int n = io.getInt();
      int m = io.getInt();

      UFDS ufds = new UFDS(n);
      // System.err.println(ufds);
      for (int i = 0; i<m; i++) {
        int op = io.getInt();
        if (op == 1) {
          int p = io.getInt();
          int q = io.getInt();
          ufds.union(p, q);
        } else if (op == 2) {
          int p = io.getInt();
          int q = io.getInt();
          ufds.move(p, q);
        } else if (op == 3) {
          int p = io.getInt();
          io.println(ufds.nElements(p) + " " + ufds.sum(p));
        }
        // System.err.println(ufds);
      }
    }
    io.close();
  }//main
}

class UFDS {
  int[] parent;
  int[] successor;
  int[] nElements;
  long[] sum;

  public UFDS(int n) {
    parent = new int[n+1];
    successor = new int[n+1];
    nElements = new int[n+1];
    sum = new long[n+1];
    for (int i = 0; i < n + 1; i++) {
      parent[i] = i;
      successor[i] = i;
      nElements[i] = 1;
      sum[i] = i;
    }
  }

  public int find(int x) {
    while (successor[x] != parent[successor[x]]) {
      successor[x] = parent[successor[x]];
    }
    return successor[x];
  }

  public void union(int x, int y) {
    int xRoot = find(x);
    int yRoot = find(y);
    if(xRoot != yRoot) {
      parent[xRoot] = yRoot;
      successor[x] = yRoot;
      nElements[yRoot] += nElements[xRoot];
      sum[yRoot] += sum[xRoot];
    }
  }

  public void move(int x, int y) {
    int xRoot = find(x);
    int yRoot = find(y);
    if(xRoot != yRoot) {
      successor[x] = yRoot;
      nElements[xRoot]--;
      nElements[yRoot]++;
      sum[xRoot] -= x;
      sum[yRoot] += x;
    }
  }

  public int nElements(int x) {
    return nElements[find(x)];
  }

  public long sum(int x) {
    return sum[find(x)];
  }

  public String toString() {
    return Arrays.toString(parent) + "\n" + Arrays.toString(nElements) + "\n" + Arrays.toString(sum)+"\n";
  }
}
