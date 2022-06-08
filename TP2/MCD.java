package TP2;

public class MCD {

  // EJERCICIO 4 ///////////////////////////////////////////////////////////////

  /* Implementar el algoritmo de Euclides. */
  public static int mcd(int a, int b) {
    if (a == 0 && b == 0) return -1;
    // System.out.printf("%-10s %-10s %-10s\n", "Dividendo", "Divisor",
    // "Resto");
    while (b != 0) {
      int r = TP1.Div.restoEnt(a, b);
      // System.out.printf("%-10d %-10d %-10d\n", a, b, r);
      a = b;
      b = r;
    }
    return a;
  }

  public static int mcdPositivo(int a, int b) {
    a = a >= 0 ? a : -a;
    b = b >= 0 ? b : -b;
    return mcd(a, b);
  }

  // PRUEBAS ///////////////////////////////////////////////////////////////////

  public static void main(String[] args) {
    int a = 150;
    int b = -29;
    System.out.printf("MCD(%d, %d) = %d", a, b, mcdPositivo(a, b));
    System.out.println();
    System.out.printf("MCD(%d, %d) = %d", a, b, mcd(a, b));
  }
}
