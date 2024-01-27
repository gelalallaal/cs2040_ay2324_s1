import java.util.*;
import java.lang.*;

public class A {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.nextLine();

    Runner[] runners = new Runner[n];

    for (int i = 0; i < n; i++) {
      String line = sc.nextLine();
      String[] parts = line.split(" ");
      String name = parts[0];
      double firstBat = Double.parseDouble(parts[1]);
      double laterBat = Double.parseDouble(parts[2]);
      runners[i] = new Runner(name, firstBat, laterBat);
    }

    Arrays.sort(runners, (a,b) -> Double.compare(a.laterBat, b.laterBat));

    ArrayList<Runner> finalTeam = new ArrayList<Runner>();
    double min = Double.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      ArrayList<Runner> team = new ArrayList<Runner>();
      team.add(runners[i]);
      double time = runners[i].firstBat;
      for (int j = 0; j < n; j++) {
        if (j != i) {
          team.add(runners[j]);
          time += runners[j].laterBat;
        }
        if (team.size() == 4) {
          break;
        }
      }
      if (time < min) {
        min = time;
        finalTeam = team;
      }
    }

    System.out.println(min);

    for (Runner r : finalTeam) {
      System.out.println(r.name);
    }
  }

  static class Runner {
        String name;
        double firstBat;
        double laterBat;

        Runner(String name, double firstBat, double laterBat) {
            this.name = name;
            this.firstBat = firstBat;
            this.laterBat = laterBat;
        }

        @Override
        public String toString() {
          return name;
        }
    }
}
