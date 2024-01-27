import java.util.*;
import java.io.*;

public class Teque {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int n = Integer.parseInt(st.nextToken());
    int[] front = new int[2000000];
    int[] back = new int[2000000];
    int frontHead = 1000000;
    int frontsize = 0;
    int backHead = 1000000;
    int backsize = 0;
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      String cmd = st.nextToken();
      int num = Integer.parseInt(st.nextToken());
      if (cmd.equals("push_back")) {
        back[backHead + backsize] = num;
        backsize++;
        if (backsize > frontsize) {
          front[frontHead + frontsize] = back[backHead];
          backHead++;
          backsize--;
          frontsize++;
        }
      } else if (cmd.equals("push_front")) {
        front[frontHead - 1] = num;
        frontHead--;
        frontsize++;
        if (frontsize > backsize + 1) {
          back[backHead - 1] = front[frontHead + frontsize - 1];
          frontsize--;
          backHead--;
          backsize++;
        }
      } else if (cmd.equals("push_middle")) {
        if (frontsize > backsize) {
          back[backHead - 1] = num;
          backHead--;
          backsize++;
        } else {
          front[frontHead + frontsize] = num;
          frontsize++;
        }
      } else {
        if (num < frontsize) {
          pw.println(front[frontHead + num]);
        } else {
          pw.println(back[backHead + num - frontsize]);
        }
      }
    }
    pw.flush();
    pw.close();
    br.close();
  }
}
