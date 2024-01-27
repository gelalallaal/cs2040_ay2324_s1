import java.io.*;
import java.util.*;

public class AlmostUnionFind {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    String temp = br.readLine();
    while (temp != null) {
      StringTokenizer st = new StringTokenizer(temp);
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      UnionFind uf = new UnionFind(n);
      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        int cmd = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken()) - 1;
        if (cmd == 1) {
          int q = Integer.parseInt(st.nextToken()) - 1;
          uf.unionSet(p, q);
        } else if (cmd == 2) {
          int q = Integer.parseInt(st.nextToken()) - 1;
          int x = uf.findSet(p);
          int y = uf.findSet(q);
          if (x != y) {
            uf.p.set(p, y);
            uf.setSize.set(y, uf.setSize.get(y) + 1);
            uf.setSize.set(x, uf.setSize.get(x) - 1);
            uf.sum[y] += p;
            uf.sum[x] -= p;
          }
        } else if (cmd == 3) {
          pw.println(uf.sizeOfSet(p) + " " + uf.sum[uf.findSet(p)]);
        }
        System.out.println(cmd);
      }
      System.out.println("done");
      temp = br.readLine();
    }
    System.out.println("end");
    pw.flush();
    pw.close();
    br.close();
  }
}

class UnionFind {
  public ArrayList<Integer> p, rank, setSize;
  public int numSets;
  public long[] sum;

  public UnionFind(int N) {
    p = new ArrayList<>(N);
    rank = new ArrayList<>(N);
    setSize = new ArrayList<>(N);
    sum = new long[N];
    numSets = N;
    for (int i = 0; i < N; i++) {
      p.add(i);
      rank.add(0);
      setSize.add(1);
      sum[i] = i;
    }
  }

  public int findSet(int i) {
    if (p.get(i) == i) return i;
    else {
      int ret = findSet(p.get(i));
      p.set(i, ret);
      return ret;
    }
  }

  public Boolean isSameSet(int i, int j) {
    return findSet(i) == findSet(j);
  }

  public void unionSet(int i, int j) {
    if (!isSameSet(i, j)) {
      numSets--;
      int x = findSet(i), y = findSet(j);
      if (rank.get(x) > rank.get(y)) {
        p.set(y, x);
        setSize.set(x, setSize.get(x) + setSize.get(y));
        sum[x] += sum[y];
      } else {
        p.set(x, y);
        setSize.set(y, setSize.get(y) + setSize.get(x));
        if (rank.get(x) == rank.get(y)) {
          rank.set(y, rank.get(y) + 1);
        }
        sum[y] += sum[x];
      }
    }
  }

  public int numDisjointSets() {
    return numSets;
  }

  public int sizeOfSet(int i) {
    return setSize.get(findSet(i));
  }
}
