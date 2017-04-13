
package consoletests;

import java.io.*;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */

public class Reader {

  static BufferedReader stdin =
     new BufferedReader(new InputStreamReader(System.in));

  public static String readLine() {
    String input = null;
    boolean ok;
    do {
      try {
        input = stdin.readLine();
        ok = true;
      } catch (Exception e) {
        System.out.println("Error, please try again");
        ok = false;
      }
    }
    while (!ok);
    return input;
  }

  public static int readInt() {
    int input = -1;
    boolean ok;
    do {
      try {
        input = Integer.parseInt(stdin.readLine());
        ok = true;
      } catch (Exception e) {
        System.out.println("Error, please try again");
        ok = false;
      }
    }
    while (!ok);
    return input;
  }

  public static double readDouble() {
    double input = -1;
    boolean ok;
    do {
      try {
        input = new Double(stdin.readLine()).doubleValue();
        ok = true;
      } catch (Exception e) {
        System.out.println("Error, please try again");
        ok = false;
      }
    }
    while (!ok);
    return input;
  }

  public static char readChar() {
    String line = readLine();
    try {
      return line.charAt(0);
    } catch (Exception e) {
      return ' ';
    }
  }
}

