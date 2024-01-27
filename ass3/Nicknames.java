import java.util.*;
import java.io.*;

public class Nicknames {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int nNames = Integer.parseInt(br.readLine());
    HashMap<Character, AVL> names = new HashMap<Character, AVL>();

    for (int i = 0; i < nNames; i++) {
      String name = br.readLine();
      char first = name.charAt(0);
      if (names.containsKey(first)) {
        names.get(first).insert(name);
      } else {
        AVL tree = new AVL();
        tree.insert(name);
        names.put(first, tree);
      }
    }

    int nNicknames = Integer.parseInt(br.readLine());
    for (int i = 0; i < nNicknames; i++) {
      String nickname = br.readLine();
      char first = nickname.charAt(0);
      if (names.containsKey(first)) {
        AVL tree = names.get(first);
        pw.println(tree.find(nickname));
      } else {
        pw.println(0);
      }
    }
  pw.close();
  br.close();
  }
}

class Vertex {
  String key;
  Vertex parent;
  Vertex left;
  Vertex right;
  int height;
  int size;

  Vertex(String v) {
    this.key = v;
    this.parent = null;
    this.left = null;
    this.right = null;
    this.height = 0;
    this.size = 1;
  }
}

class AVL {
  Vertex root;

  AVL() {
    this.root = null;
  }

  public void insert(String v) {
    this.root = insert(this.root, v);
  }

  Vertex insert(Vertex T, String v) {
    if (T == null) {
      return new Vertex(v);
    }
    if (v.compareTo(T.key) < 0) {
      T.left = insert(T.left, v);
      T.left.parent = T;
    } else {
      T.right = insert(T.right, v);
      T.right.parent = T;
    }
    T.height = Math.max(height(T.left), height(T.right)) + 1;
    T.size = size(T.left) + size(T.right) + 1;
    return balance(T);
  }

  public int height(Vertex T) {
    return T == null ? -1 : T.height;
  }

  public int size(Vertex T) {
    return T == null ? 0 : T.size;
  }

  public int balanceFactor(Vertex T) {
    return height(T.left) - height(T.right);
  }

  public Vertex balance(Vertex T) {
    if (balanceFactor(T) < -1) {
      if (balanceFactor(T.right) > 0) {
        T.right = rotateRight(T.right);
      }
      T = rotateLeft(T);
    } else if (balanceFactor(T) > 1) {
      if (balanceFactor(T.left) < 0) {
        T.left = rotateLeft(T.left);
      }
      T = rotateRight(T);
    }
    return T;
  }

  public Vertex rotateLeft(Vertex T) {
    Vertex w = T.right;
    w.parent = T.parent;
    T.parent = w;
    T.right = w.left;
    if (w.left != null) {
      w.left.parent = T;
    }
    w.left = T;
    T.height = Math.max(height(T.left), height(T.right)) + 1;
    T.size = size(T.left) + size(T.right) + 1;
    w.height = Math.max(height(w.left), height(w.right)) + 1;
    w.size = size(w.left) + size(w.right) + 1;
    return w;
  }

  public Vertex rotateRight(Vertex T) {
    Vertex w = T.left;
    w.parent = T.parent;
    T.parent = w;
    T.left = w.right;
    if (w.right != null) {
      w.right.parent = T;
    }
    w.right = T;
    T.height = Math.max(height(T.left), height(T.right)) + 1;
    T.size = size(T.left) + size(T.right) + 1;
    w.height = Math.max(height(w.left), height(w.right)) + 1;
    w.size = size(w.left) + size(w.right) + 1;
    return w;
  }

  public int find(String v) {
    Vertex firstmatch = find(this.root, v);
    if (firstmatch == null) {
      return 0;
    }
    return 1 + findLeft(firstmatch.left, v) + findRight(firstmatch.right, v);
  }

  public Vertex find(Vertex T, String v) {
    if (T == null) {
      return null;
    }
    if (T.key.startsWith(v)) {
      return T;
    }
    if (v.compareTo(T.key) < 0) {
      return find(T.left, v);
    } else {
      return find(T.right, v);
    }
  }

  public int findLeft(Vertex T, String v) {
    if (T == null) {
      return 0;
    }
    if (T.key.startsWith(v)) {
      return 1 + findLeft(T.left, v) + size(T.right);
    } else {
      return findLeft(T.right, v);
    }
  }

  public int findRight(Vertex T, String v) {
    if (T == null) {
      return 0;
    }
    if (T.key.startsWith(v)) {
      return 1 + findRight(T.right, v) + size(T.left);
    } else {
      return findRight(T.left, v);
    }
  }
}


