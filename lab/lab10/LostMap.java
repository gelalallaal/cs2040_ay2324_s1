import java.io.*;
import java.util.*;

public class LostMap {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int n = Integer.parseInt(br.readLine());
    ArrayList<IntegerTriple> edgeList = new ArrayList<IntegerTriple>();
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        int w = Integer.parseInt(st.nextToken());
        if (w != 0) edgeList.add(new IntegerTriple(w, i, j));
      }
    }
    Collections.sort(edgeList);

    ArrayList<IntegerTriple> mst = new ArrayList<IntegerTriple>();
    int num_taken = 0;

    UnionFind UF = new UnionFind(n);
    for (int i = 0; i < edgeList.size(); i++) {
      IntegerTriple front = edgeList.get(i);
      if (!UF.isSameSet(front.second(), front.third())) {
        mst.add(front);
        UF.unionSet(front.second(), front.third());
        num_taken++;
        if (num_taken == n - 1) break;
      }
    }

    for (int i = 0; i < mst.size(); i++) {
      IntegerTriple front = mst.get(i);
      pw.println((front.second() + 1) + " " + (front.third() + 1));
    }

    pw.close();
    br.close();
  }
}

class UnionFind { // OOP style
  private ArrayList<Integer> p, rank, setSize;
  private int numSets;

  public UnionFind(int N) {
    p = new ArrayList<Integer>(N);
    rank = new ArrayList<Integer>(N);
    setSize = new ArrayList<Integer>(N);
    numSets = N;
    for (int i = 0; i < N; i++) {
      p.add(i);
      rank.add(0);
      setSize.add(1);
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
      // rank is used to keep the tree short
      if (rank.get(x) > rank.get(y)) {
        p.set(y, x);
        setSize.set(x, setSize.get(x) + setSize.get(y));
      } else {
        p.set(x, y);
        setSize.set(y, setSize.get(y) + setSize.get(x));
        if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y) + 1);
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

class IntegerTriple implements Comparable<IntegerTriple> {
  Integer _first, _second, _third;

  public IntegerTriple(Integer f, Integer s, Integer t) {
    _first = f;
    _second = s;
    _third = t;
  }

  public int compareTo(IntegerTriple o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else if (!this.second().equals(o.second()))
      return this.second() - o.second();
    else
      return this.third() - o.third();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
  Integer third() { return _third; }
}

