import java.util.*;
import java.lang.*;
import java.io.*;

class Card implements Comparable<Card> {
  int count = 0;
  long sellPrice = 0;
  long buyPrice = 0;
  long value = 0;

  public Card(int count, long buyPrice, long sellPrice) {
    this.count = count;
    this.buyPrice = buyPrice;
    this.sellPrice = sellPrice;
    this.value = (2 - this.count) * this.buyPrice + this.count * sellPrice;
  }

  @Override
  public int compareTo(Card o) {
    long x = this.value;
    long y = o.value;
    if (x < y)
      return -1;
    if (x > y)
      return 1;
    if (this.buyPrice < o.buyPrice)
      return -1;
    if (this.buyPrice > o.buyPrice)
      return 1;
    return 0;
  }

  @Override
  public String toString() {
    return "Card " + this.count + ", " + this.sellPrice + ", " + this.buyPrice;
  }
}

public class CardTrading {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int t = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int[] counts = new int[t];
    for (int i = 1; i <= n; i++) {
      counts[Integer.parseInt(st.nextToken()) - 1]++;
    }

    Card[] cards = new Card[t];
    for (int i = 0; i < t; i++) {
      st = new StringTokenizer(br.readLine());
      cards[i] = new Card(counts[i], Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    Arrays.sort(cards);
    // System.out.println(Arrays.toString(cards));

    long result = 0;
    for (int i = 0; i < k; i++) {
      result -= (2 - cards[i].count) * cards[i].buyPrice;
      // System.out.println("Buying " + cards[i].toString());
    }
    for (int i = k; i < t; i++) {
      result += cards[i].count * cards[i].sellPrice;
      // System.out.println("Selling " + cards[i].toString());
    }

    pw.println(result);

    pw.close();
    br.close();
  }
}
