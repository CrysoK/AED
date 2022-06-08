package TP4;

public class E1 {

  /*
   * Escribir un método recursivo que permita resolver la potencia de un número.
   * Los datos son a y b naturales, y lo que se desea es calcular a elevado a la
   * b.
   */
  public static int potenciaNat(int a, int b) {
    if (a <= 0 || b < 0) return -1;
    if (b == 0) {
      return 1;
    }
    return a * potenciaNat(a, b - 1);
  }

  public static void main(String[] args) {
    System.out.println(potenciaNat(2, 3));
  }
}
