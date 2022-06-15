package TP4;

import TP1.Primos;
import TP7.UList;

public class E6 {

  /*
   * Escribir un método recursivo que liste los factores primos de un número
   * natural n.
   */
  public static int[] factoresPrimos(int n) {
    if (n <= 0) return new int[0];
    UList fac = new UList();
    int max = (int) Math.round(Math.sqrt(n));
    int[] primos = Primos.elimCeros(Primos.criba(max));
    int i = 0;
    fac = factoresRec(n, fac, primos, i);
    // ¿Qué devolver cuando es primo?
    if (fac.length() == 0) {
      fac.insert(1);
      fac.insert(n);
    }
    return fac.toIntArray();
  }

  public static UList factoresRec(int n, UList fac, int[] primos, int i) {
    if (n <= 1 || i >= primos.length) {
      return fac;
    }
    int d = primos[i];
    if (TP1.Div.restoNat(n, d) == 0) {
      fac.insert(d);
      return factoresRec(TP1.Div.cocienteNat(n, d), fac, primos, 0);
    }
    return factoresRec(n, fac, primos, i + 1);
  }

  public static void main(String[] args) {
    int[] res = factoresPrimos(2 * 3 * 5);
    for (int i = 0; i < res.length; i++) {
      System.out.print(res[i] + " ");
    }
  }
  // public static int[] factoresPositivos(int n) {
  // if (n <= 0)
  // return new int[0];

  // // List<Integer> factores = new ArrayList<Integer>();
  // int max = (int) Math.round(Math.sqrt(n));
  // int[] primos = TP1.Primos.criba(max);
  // int i = 0;

  // while (n >= 1 && i < primos.length) {
  // int d = primos[i];
  // if (TP1.Div.restoNat(n, d) == 0) {
  // factores.add(d);
  // n = TP1.Div.cocienteNat(n, d);
  // i = 0;
  // } else {
  // i++;
  // }
  // }
  // // ¿Qué devolver cuando es primo?
  // if (factores.size() == 0) {
  // factores.add(1);
  // factores.add(n);
  // }
  // return factores.stream().mapToInt(x -> x).toArray();
  // }

}
