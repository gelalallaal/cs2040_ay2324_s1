/**
 * @author Alif Naufal Farrashady A0218302U
 */

import java.util.*;
import java.io.*;

public class AlmostUnion {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in, System.out);
        
        while (io.hasMoreTokens()) {
            int n = io.getInt();
            int m = io.getInt();
    
            UFDS ufds = new UFDS(n);
            System.out.println(ufds);
            for (int i = 0; i < m; i++) {
                int command = io.getInt();
    
                if (command == 1) {
                    int p = io.getInt();
                    int q = io.getInt();
                    ufds.unionSet(p, q);
                } else if (command == 2) {
                    int p = io.getInt();
                    int q = io.getInt();
                    ufds.moveSet(p, q);
                } else {
                    int p = io.getInt();
                    int numElements = ufds.numElements(p);
                    long sumElements = ufds.sumElements(p);
                    io.write(numElements + " " + sumElements + "\n");
                }
        System.out.println(ufds);
            }
        }
        io.close();
    }
}

class UFDS {
    int[] parent;
    int[] next;
    int[] count;
    long[] sum;

    UFDS(int n) {
        this.parent = new int[n + 1];
        this.next = new int[n + 1];
        this.count = new int[n + 1];
        this.sum = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
            next[i] = i;
            count[i] = 1;
            sum[i] = i;
        }
    }

    int findSet(int p) {
        int nextParent = next[p];
        while (nextParent != parent[nextParent]) {
            nextParent = parent[nextParent];
        }
        next[p] = nextParent;
        return nextParent;
    }

    boolean isSameSet(int p, int q) {
        return findSet(p) == findSet(q);
    }

    void unionSet(int p, int q) {
        if (!isSameSet(p, q)) {
            int x = findSet(p);
            int y = findSet(q);
            parent[x] = y;
            next[p] = y;
            count[y] += count[x];
            sum[y] += sum[x];
        }
    }

    void moveSet(int p, int q) {
        if (!isSameSet(p, q)) {
            int x = findSet(p);
            int y = findSet(q);

            next[p] = y;

            count[x] -= 1;
            count[y] += 1;

            sum[x] -= p;
            sum[y] += p;
        }
    }

    int numElements(int p) {
        return count[findSet(p)];
    }

    long sumElements(int p) {
        return sum[findSet(p)];
    }
    
  public String toString() {
    return "parent: " + Arrays.toString(parent) + "\n" +
           "next: " + Arrays.toString(next) + "\n" +
           "count: " + Arrays.toString(count) + "\n" +
           "sum: " + Arrays.toString(sum) + "\n";
  }
}
