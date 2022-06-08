package TP4;

public class E2 {

  /*
   * Escribir un método recursivo que permita decidir si un número natural n es
   * primo o no.
   */
  private static boolean esPrimoRec(int n, int d) {
    if (d * d > n) return true;
    if (TP1.Div.restoNat(n, d) == 0) return false;
    return esPrimoRec(n, d + 2);
  }

  public static boolean esPrimoNat(int n) {
    if (n == 2) return true;
    if (n <= 0 || n == 1 || TP1.Div.restoNat(n, 2) == 0) return false;
    return esPrimoRec(n, 3);
  }

  public static void main(String[] args) {
    System.out.println(esPrimoNat(1));
    System.out.println(esPrimoNat(2));
    System.out.println(esPrimoNat(3));
    System.out.println(esPrimoNat(4));
    System.out.println(esPrimoNat(5));
    System.out.println(esPrimoNat(6));
    System.out.println(esPrimoNat(7));
    System.out.println(esPrimoNat(8));
    System.out.println(esPrimoNat(9));
  }
}
