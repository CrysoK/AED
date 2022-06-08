package TP3;

public class Mod {

  // EJERCICIO 10 //////////////////////////////////////////////////////////////

  /*
   * Diseñe un algoritmo que devuelva el inverso de un número (módulo m dado) si
   * existe; sino, que devuelva 0 (porque no existe tal inverso)
   */
  public static int inverso(int n, int mod) {
    MCDST mcdST = calcularST(n, mod);
    int mcd = mcdST.mcd;
    int inv = mcdST.s;

    if (mcd == 1) {
      while (inv < 0) inv += mod;
      while (inv > mod) inv -= mod;
    } else inv = 0;
    return inv;
  }

  private static MCDST calcularST(int a, int b) {
    int r = b, oldR = a;
    int s = 0, oldS = 1;
    int t = 1, oldT = 0;
    while (r != 0) {
      int c = TP1.Div.cocienteEnt(oldR, r);
      int aux = r;
      r = TP1.Div.restoEnt(oldR, aux);
      oldR = aux;
      aux = s;
      s = oldS - c * aux;
      oldS = aux;
      aux = t;
      t = oldT - c * aux;
      oldT = aux;
    }
    return new MCDST(oldR, oldS, oldT);
  }

  // calcular st requiere calcular mcd, por lo que aprovechamos esto y lo
  // retornamos también.
  private static class MCDST {

    public int mcd;
    public int s;
    public int t;

    public MCDST(int mcd, int s, int t) {
      this.mcd = mcd;
      this.s = s;
      this.t = t;
    }

    public String toString() {
      return "mcd: " + mcd + ", s: " + s + ", t: " + t;
    }
  }

  private static void mostrarInv(int n, int mod) {
    System.out.printf("Inv(%d) ≡ %d (mod %d)\n", n, inverso(n, mod), mod);
  }

  // EJERCICIO 11 //////////////////////////////////////////////////////////////

  /*
   * Diseñe un algoritmo que resuelve ax ≡ b (mod m) cuando a y m son coprimos.
   */
  public static int[] resEcLineal(int a, int b, int mod) {
    System.out.printf("%d X ≡ %d (mod %d)\n", a, b, mod);
    MCDST mcdST = calcularST(a, mod);
    int mcdAMod = mcdST.mcd;

    if (mcdAMod != 1) {
      System.out.println("No tiene solución");
      return new int[0];
    }

    int inv = mcdST.s;
    int mcdAB = TP2.MCD.mcd(a, b);
    int cant = 1;
    if (mcdAB != 1 && TP1.Div.restoEnt(b, mcdAMod) == 0) {
      cant = mcdAMod;
    }
    int[] conjSol = new int[cant];
    int sol = TP1.Div.restoEnt((b * inv), mod);
    // sol es la menor solución
    while (sol - mod > 0) {
      sol -= mod;
    }
    for (int i = 0; i < cant; i++) {
      conjSol[i] = sol;
      sol += mod;
    }
    System.out.printf("X ≡ {");
    for (int i = 0; i < cant - 1; i++) {
      System.out.printf("%d, ", conjSol[i]);
    }
    System.out.printf("%d} (mod %d)\n", conjSol[cant - 1], mod);
    return conjSol;
  }

  // EJERCICIO 14 //////////////////////////////////////////////////////////////

  /*
   * Implemente el algoritmo del Teorema Chino del Resto para que, dado k ⩾ 1, y
   * m1, m2, ..., mk enteros positivos, coprimos dos a dos, con b1, b2, ..., bk
   * enteros positivos, permita resolver el siguiente sistema de congruencias:
   */
  // x ≡ b1 (mod m1)
  // x ≡ b2 (mod m2)
  // ...
  // x ≡ bk (mod mk)

  public static int teoremaChino(int[] b, int[] mods) {
    mostrar_sist(b, mods);
    if (!primosDeADos(mods)) {
      System.out.println("No se puede aplicar el teorema.");
      return 0;
    }
    int m = calcM(mods);
    int[] mK = calcMK(mods, m);
    int[] invs = calcInvs(mods, mK);

    int x = 0;
    for (int i = 0; i < mK.length; i++) {
      x += b[i] * mK[i] * invs[i];
    }
    x = TP1.Div.restoEnt(x, m);
    System.out.printf("X ≡ %d (mod %d)\n", x, m);
    return x;
  }

  public static boolean primosDeADos(int[] n) {
    for (int i = 0; i < n.length; i++) {
      for (int j = i; j < n.length; j++) {
        if (i != j) {
          if (TP2.MCD.mcd(n[i], n[j]) != 1) {
            System.out.printf("%d y %d no son coprimos\n", n[i], n[j]);
            return false;
          }
        }
      }
    }
    return true;
  }

  private static int calcM(int[] mods) {
    int res = 1;
    for (int i = 0; i < mods.length; i++) {
      res *= mods[i];
    }
    return res;
  }

  private static int[] calcMK(int[] mods, int m) {
    int[] M = new int[mods.length];
    for (int i = 0; i < M.length; i++) {
      M[i] = TP1.Div.cocienteEnt(m, mods[i]);
    }
    return M;
  }

  private static int[] calcInvs(int[] mods, int[] M) {
    int[] invs = new int[mods.length];
    for (int i = 0; i < invs.length; i++) {
      invs[i] = inverso(M[i], mods[i]);
    }
    return invs;
  }

  private static void mostrar_sist(int[] b, int[] mods) {
    int n = mods.length;
    System.out.printf("⎧ X ≡ %d (mod %d)\n", b[0], mods[0]);
    for (int i = 1; i < n - 1; i++) {
      System.out.printf("⎪ X ≡ %d (mod %d)\n", b[i], mods[i]);
    }
    System.out.printf("⎩ X ≡ %d (mod %d)\n", b[n - 1], mods[n - 1]);
  }

  // PRUEBAS ///////////////////////////////////////////////////////////////////

  public static void main(String[] args) {
    testE10();
    testE11();
    testE14();
  }

  private static void testE10() {
    System.out.println();
    System.out.println("EJERCICIO 10");
    System.out.println();

    mostrarInv(2, 11);
    mostrarInv(6, 17);
    mostrarInv(7, 16);
    mostrarInv(5, 13);
    mostrarInv(3, 10);
  }

  private static void testE11() {
    System.out.println();
    System.out.println("EJERCICIO 11");
    System.out.println();

    resEcLineal(3, 9, 15);
    System.out.println();
    resEcLineal(3, 5, 13);
    System.out.println();
    resEcLineal(5, 1, 11);
    System.out.println();
    resEcLineal(8, 2, 10);
    System.out.println();
    resEcLineal(21, 18, 30);
  }

  private static void testE14() {
    System.out.println();
    System.out.println("EJERCICIO 14");
    System.out.println();

    int[] b = { 1, 4 };
    int[] m = { 7, 11 };

    teoremaChino(b, m);
    System.out.println();

    b = new int[] { 3, 8 };
    m = new int[] { 7, 25 };

    teoremaChino(b, m);
    System.out.println();

    b = new int[] { 2, 3, 2 };
    m = new int[] { 3, 5, 7 };

    teoremaChino(b, m);
    System.out.println();

    b = new int[] { 2, 3, 5 };
    m = new int[] { 4, 7, 9 };

    teoremaChino(b, m);
    System.out.println();
  }
}
