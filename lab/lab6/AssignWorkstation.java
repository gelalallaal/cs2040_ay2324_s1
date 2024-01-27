import java.io.*;
import java.util.*;

class AssignWorkstation {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    PriorityQueue<Researcher> researchers = new PriorityQueue<Researcher>();
    PriorityQueue<Slot> slots = new PriorityQueue<Slot>();
    int open = 0;
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());

      researchers.add(new Researcher(a, s));
    }

    while (!researchers.isEmpty()) {
      Researcher r = researchers.poll();
      // if (slots.isEmpty()) { // cnfm no unlocked
      //   open++;
      //   slots.add(new Slot(r.leave() + m, r.leave()));
      // } else { //might have unlocked if not expired
      //   Slot s = slots.poll(); //earlist expiry = earliest free
      //   while (s.isExpired(r.getArrival())) { // poll until got unlocked
      //     if (slots.isEmpty()) { // all expired
      //       open++;
      //       slots.add(new Slot(r.leave() + m, r.leave()));
      //       break;
      //     } else {
      //       s = slots.poll(); //remove expired
      //     }
      //   }
      //   if (s.isAvail(r.getArrival())) { // is avail
      //     slots.add(new Slot(r.leave() + m, r.leave()));
      //   } else { //not avail
      //     open++;
      //     slots.add(s);
      //     slots.add(new Slot(r.leave() + m, r.leave()));
      //   }
      // }

      slots.add(new Slot(r.leave() + m, r.leave()));
      while (!slots.isEmpty() && slots.peek().isExpired(r.getArrival())) {
        slots.poll();
      }

      if (slots.peek().isAvail(r.getArrival())) {
        slots.poll();
      } else {
        open++;
      }
    }

    pw.println(n-open);

    pw.close();
    br.close();
  }

  static class Researcher implements Comparable<Researcher>{
    int arrival;
    int stay;

    public Researcher(int a, int s) {
      arrival = a;
      stay = s;
    }

    public int getArrival() {
      return arrival;
    }

    public int leave() {
      return arrival + stay;
    }

    public int compareTo(Researcher r) {
      return arrival - r.getArrival()==0?stay-r.stay:arrival-r.getArrival();
    }

    public String toString() {
      return "(" + arrival + " " + stay + ")";
    }
  }

  static class Slot implements Comparable<Slot>{
    int expiry;
    int free;

    public Slot(int e, int f) {
      expiry = e;
      free = f;
    }

    public boolean isExpired(int a) {
      return a > expiry;
    }

    public boolean isAvail(int a) {
      return a <= expiry && a >= free;
    }

    public int compareTo(Slot s) {
      return free - s.free;
    }
  }
}
