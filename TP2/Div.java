package TP2;

import java.util.ArrayList;
import java.util.List;

public class Div {

  // EJERCICIO 13 //////////////////////////////////////////////////////////////

  /*
   * Implemente un algoritmo que devuelva todos los factores positivos de un
   * entero positivo dado.
   */
  public static int[] factoresPositivos(int n) { // TODO Corregir
    if (n <= 0) return new int[0];

    List<Integer> factores = new ArrayList<Integer>();
    int max = (int) Math.round(Math.sqrt(n));
    int[] primos = TP1.Primos.elimCeros(TP1.Primos.criba(max));
    int i = 0;

    while (n >= 1 && i < primos.length) {
      int d = primos[i];
      if (TP1.Div.restoNat(n, d) == 0) {
        factores.add(d);
        n = TP1.Div.cocienteNat(n, d);
        i = 0;
      } else {
        i++;
      }
    }
    // ¿Qué devolver cuando es primo?
    if (factores.size() == 0) {
      factores.add(1);
      factores.add(n);
    }
    return factores.stream().mapToInt(x -> x).toArray();
  }

  // PRUEBAS ///////////////////////////////////////////////////////////////////

  public static void main(String[] args) {
    int[] f = factoresPositivos(2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23);
    for (int i = 0; i < f.length; i++) {
      if (f[i] != 0) System.out.println(f[i]);
    }
  }
}
