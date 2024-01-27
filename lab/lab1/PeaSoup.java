import java.util.Scanner;
import java.lang.*;

class PeaSoup{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int nRestaurant = Integer.parseInt(sc.nextLine());
    for (int i = nRestaurant; i > 0; i--) {
      int nMenu = Integer.parseInt(sc.nextLine());
      String restaurantName = sc.nextLine();
      boolean peaSoup = false;
      boolean pancake = false;
      for (int j = nMenu; j > 0; j--) {
        String item = sc.nextLine();
        if (item.equals("pea soup")) {
          peaSoup = true;
        }
        if (item.equals("pancakes")) {
          pancake = true;
        }
      }
      if (peaSoup && pancake) {
        System.out.println(restaurantName);
        return;
      }
    }
    System.out.println("Anywhere is fine I guess");
  }
}
