package TP4;

public class E3 {

  /*
   * Desarrollar un algoritmo recursivo que permita calcular la cantidad de
   * dígitos que tiene un número positivo n.
   */
  public static int cantDigitos(int n) {
    if (n < 0) return 0;
    if (n < 10) {
      return 1;
    }
    return 1 + cantDigitos(TP1.Div.cocienteNat(n, 10));
  }

  public static void main(String[] args) {
    System.out.println(cantDigitos(1234567));
  }
}
