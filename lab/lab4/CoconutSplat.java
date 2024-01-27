import java.util.*;
import java.io.*;

class CoconutSplat {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());
    int s = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());

    List<Player> players = new LinkedList<Player>();
    for (int i = 0; i < n; i++) {
      players.add(new Player(i+1, 3));
    }

    int nextPlayer = 0; //start from index 0
    while (players.size() > 1) { //loop till 1 plyer left
      nextPlayer = (nextPlayer + s - 1) % players.size(); //index of next player
      Player currentPlayer = players.get(nextPlayer);
      currentPlayer.update();
      if (currentPlayer.getState() == 2) { //changed to half ccnut
        Player clone = new Player(currentPlayer);
        players.add(nextPlayer+1, clone);
      } else if (currentPlayer.getState() == 1) {
        nextPlayer++;
      } else if (currentPlayer.getState() == 0) { //hands to back
        players.remove(nextPlayer);
      }
    }
    pw.println(players.get(0).getId());
    pw.flush();
    pw.close();
    br.close();
  }

  static class Player {
    int id;
    int state;

    public Player(int id, int state) {
      this.id = id;
      this.state = state;
    }

    public Player(Player p) {
      this.id = p.getId();
      this.state = p.getState();
    }

    public int getId() {
      return id;
    }

    public int getState() {
      return state;
    }

    public void update() {
      this.state = this.state - 1;
    }

    @Override
    public String toString() {
      return "Player " + id + " " + state;
    }
  }
}
