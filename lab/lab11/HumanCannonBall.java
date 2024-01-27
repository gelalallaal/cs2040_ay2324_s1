import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

class HumanCannonBall {
  public static void main(String[] args) throws Exception {
    PrintWriter pw = new PrintWriter(System.out);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    double INF = 1e9;
    ArrayList<Coords> cannons = new ArrayList<>();

    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    double currX = Double.parseDouble(st.nextToken());
    double currY = Double.parseDouble(st.nextToken());

    st = new StringTokenizer(br.readLine(), " ");
    double tarX = Double.parseDouble(st.nextToken());
    double tarY = Double.parseDouble(st.nextToken());

    cannons.add(new Coords(tarX, tarY, 2));
    cannons.add(new Coords(currX, currY, 1));

    int n = Integer.parseInt(br.readLine());

    ArrayList<ArrayList<Pair>> adjList = new ArrayList<ArrayList<Pair>>();
    adjList.add(new ArrayList<>());
    adjList.add(new ArrayList<>());
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      double x = Double.parseDouble(st.nextToken());
      double y = Double.parseDouble(st.nextToken());
      cannons.add(new Coords(x, y, 0));
      adjList.add(new ArrayList<>());
    }
    // pw.println(cannons);

    for (int i = 1; i < n + 2; i++) {
      for (int j = 0; j < n + 2; j++) {
        if (i == j)
          continue;
        // pw.println("i: " + i + "j: " + j + " " +
        // cannons.get(i).directTimeTo(cannons.get(j)));
        // adjList.get(i).add(cannons.get(i).directTimeTo(cannons.get(j)));
        adjList.get(i).add(new Pair(j, cannons.get(i).directTimeTo(cannons.get(j))));
      }
    }

    // pw.println(adjList);

    ArrayList<Double> dist = new ArrayList<>(Collections.nCopies(n + 2, INF));
    int s = 1;
    int e = 0;
    dist.set(s, 0.0); // INF = 1e9 here
    dist.set(e, cannons.get(s).directTimeTo(cannons.get(e)));
    // (Modified) Dijkstra's algorithm
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    pq.offer(new Pair(s, 0.0));

    // sort the pairs by non-decreasing distance from s
    while (!pq.isEmpty()) { // main loop
      Pair top = pq.poll();
      int u = top.first(); // shortest unvisited u
      double d = top.second();
      if (d > dist.get(u))
        continue; // a very important check
      for (Pair v_w : adjList.get(u)) { // all edges from u
        int v = v_w.first();
        double w = v_w.second();
        if (dist.get(u) + w >= dist.get(v))
          continue; // not improving, skip
        dist.set(v, dist.get(u) + w); // relax operation
        pq.offer(new Pair(v, dist.get(v))); // enqueue better pair
      }
    }
    // pw.println(dist);

    // pw.println(dist.get(0));
    DecimalFormat df = new DecimalFormat("#0.000000");
    df.setRoundingMode(RoundingMode.DOWN);
    pw.println(df.format(dist.get(0)));

    pw.close();
    br.close();
  }
}

class Pair implements Comparable<Pair> {
  Integer _first;
  Double _second;

  public Pair(Integer f, Double s) {
    _first = f;
    _second = s;
  }

  public int compareTo(Pair o) {
    if (!this.second().equals(o.second()))
      return Double.compare(this.second(), o.second());
    else
      return Integer.compare(this.first(), o.first());
  }

  Integer first() {
    return _first;
  }

  Double second() {
    return _second;
  }
}

class Coords {
  double x;
  double y;
  // 0 = cannon, 1 = start, 2 = end
  int type;

  Coords(double x, double y, int type) {
    this.x = x;
    this.y = y;
    this.type = type;
  }

  public double distTo(Coords other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }

  public double directTimeTo(Coords other) {
    double dist = this.distTo(other);
    // pure walking : any to any
    double walkTime = dist / 5;
    // cannon + walking : type 0 to any
    double cannonWalkTime = this.type == 0 ? Math.abs(dist - 50) / 5 + 2 : Double.MAX_VALUE;
    return Math.min(walkTime, cannonWalkTime);
    // walking + cannon : type 1 to 0 or 2 not needed
  }

  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
