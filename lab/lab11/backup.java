import java.util.*;
import java.io.*;

public class HumanCannonBall {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());
    int sx = Integer.parseInt(st.nextToken());
    int sy = Integer.parseInt(st.nextToken());
    Coords start = new Coords(sx, sy);
    st = new StringTokenizer(br.readLine());
    int ex = Integer.parseInt(st.nextToken());
HumanCannonBall    int ey = Integer.parseInt(st.nextToken());
    Coords end = new Coords(ex, ey);

    int n = Integer.parseInt(br.readLine());
    ArrayList<Coords> cannons = new ArrayList<Coords>();
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int cx = Integer.parseInt(st.nextToken());
      int cy = Integer.parseInt(st.nextToken());
      cannons.add(new Coords(cx, cy));
    }

    double[][] adjMatrix = new double[n+2][n+2];
    double[0][1] = walktime(distance(start, end));
    double[1][0] = walktime(distance(start, end));



  }

  public static double distance(Coords a, Coords b) {
    return Math.sqrt(Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2));
  }

  public static double walktime(double d) {
    return d/5;
  }

  public static double cannonballtime(double d) {
    return 2 + Math.abs(d-50)/5;
  }
}

class Coords {
  int x, y;
  public Coords(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
