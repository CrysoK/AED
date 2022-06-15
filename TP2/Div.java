package TP2;

import TP7.OList;

public class Div {

  // EJERCICIO 13 //////////////////////////////////////////////////////////////

  /*
   * Implemente un algoritmo que devuelva todos los factores positivos de un
   * entero positivo dado.
   */
  public static int[] factoresPositivos(int n) {
    if (n <= 0) return new int[0];

    OList factores = new OList((a, b) -> (Integer) a - (Integer) b);
    int max = (int) Math.ceil(Math.sqrt(n));
    for (int i = 1; i <= max; i++) {
      if (TP1.Div.restoNat(n, i) == 0) {
        factores.insert(i);
        factores.insert(TP1.Div.cocienteNat(n, i));
      }
    }
    return factores.toIntArray();
  }

  // PRUEBAS ///////////////////////////////////////////////////////////////////

  public static void main(String[] args) {
    int[] f = factoresPositivos(2 * 3 * 4 * 5);
    System.out.println("Cantidad: " + f.length);
    for (int i = 0; i < f.length; i++) {
      if (f[i] != 0) System.out.println(f[i]);
    }
  }
}
