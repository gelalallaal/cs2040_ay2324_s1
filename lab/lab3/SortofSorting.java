import java.util.*;
import java.io.*;

public class SortofSorting {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    int numNames = Integer.parseInt(br.readLine());

    while (numNames != 0) {
      String[] names = new String[numNames];
      for (int i = 0; i < numNames; i++) {
        names[i] = br.readLine();
      }

      sortOfSort(names);

      for (int i = 0; i < numNames; i++) {
        pw.println(names[i]);
     }
      pw.println();
      numNames = Integer.parseInt(br.readLine());
    }
    pw.close();
    br.close();
  }

  private static void sortOfSort(String[] arr) {
    System.setProperty("java.util.Arrays.useLegacyMergeSort","true");
    Arrays.sort(arr, (s1, s2) -> {
      int cmp = s1.charAt(0) - s2.charAt(0);
      if (cmp == 0) {
        cmp = s1.charAt(1) - s2.charAt(1);
      }
      return cmp;
    });
  }
}

