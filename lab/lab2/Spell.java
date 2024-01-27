import java.util.*;
import java.lang.*;
import java.io.*;

public class Spell{
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    PrintWriter pw = new PrintWriter(System.out);
    
    for (int i = 1; i<=n; i++) {
      String str = String.format("Case #%d: ", i);
      StringBuilder sb = new StringBuilder(str);

      char[] arr = br.readLine().toCharArray();
      int prev = 10;

      for (int j = 0; j<arr.length; j++) {
        int ascii = (int) arr[j];
        if (ascii == 32) { // space
          sb.append((ascii == prev)? " 0": "0");
          prev = ascii;
        } else {
          int order = ascii - 96; // is not all 3!!!!!!!!!!!
          int num = 0;
          int rep = 0;
          if (order <= 15) {
            num = (order%3 == 0)? order/3 + 1 : order/3 + 2;
            rep = (order%3 == 0)? 3: order%3;
          } else if (order <=19 ) {
            //todo
            num = 7;
            int res = order - 15;
            rep = (res%4 == 0)? 4: res%4;
            // System.out.println("order: " + order + " rep: " + rep);
          }else if (order <= 22) {
            num = 8;
            int res = order - 19;
            rep = (res%3 == 0)? 3: res%3;
            // System.out.println("order: " + order + " rep: " + rep);
          } else {
            num = 9;
            int res = order - 22;
            rep = (res%4 == 0)? 4: res%4;
            // System.out.println("order: " + order + " rep: " + rep);
          }
          if (num == prev) {
            sb.append(" ");
          }
          prev = num;
          for (int k = 0; k<rep; k++) {
            sb.append(num);
          }
        }
      }
      pw.println(sb.toString());
    }
    pw.close();
    br.close();
  }
}
