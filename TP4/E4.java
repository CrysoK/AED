package TP4;

public class E4 {

  /*
   * Obtener de manera recursiva, el mcd de dos números enteros no negativos,
   * mediante el Algoritmo de Euclides.
   */
  public static int mcd(int a, int b) {
    if (a == 0 && b == 0) return -1;
    if (b == 0) return a;
    return mcd(b, TP1.Div.restoNat(a, b));
  }

  public static void main(String[] args) {
    System.out.println(mcd(150, 29));
  }
}
