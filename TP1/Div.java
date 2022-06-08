package TP1;

import Utils.Par;

public class Div {

  // Almacena valores previos para evitar cálculos innecesarios
  private static int ultA;
  private static int ultB;
  private static int ultC;
  private static int ultR;

  // EJERCICIO 10 //////////////////////////////////////////////////////////////

  /*
   * Diseñar un programa para calcular el cociente y resto para números
   * naturales.
   */
  public static Par crNat(int a, int b) {
    return new Par(cocienteNat(a, b), restoNat(a, b));
  }

  public static int cocienteNat(int a, int b) {
    if (a == ultA && b == ultB) {
      return ultC;
    }
    ultA = a;
    ultB = b;
    if (a >= 0 && b > 0) {
      int c = 0;
      while (a >= b) {
        c = c + 1;
        a = a - b;
      }
      ultR = a;
      return ultC = c;
    }
    // error
    return ultC = -1;
  }

  public static int restoNat(int a, int b) {
    if (a < 0 && b <= 0) {
      return ultR = -1;
    }
    if (a == ultA && b == ultB) {
      return ultR;
    }
    cocienteNat(a, b);
    return ultR;
    // return ult_r = (a - cociente_nat(a, b) * b);
  }

  // EJERCICIO 11 //////////////////////////////////////////////////////////////

  /*
   * Modifique el programa del inciso anterior para calcular cociente y resto de
   * los números enteros.
   */
  public static Par crEnt(int a, int b) {
    return new Par(cocienteEnt(a, b), restoEnt(a, b));
  }

  public static int cocienteEnt(int a, int b) {
    if (a >= 0 && b > 0) return cocienteNat(a, b);
    if (a >= 0 && b < 0) return -cocienteNat(a, -b);
    if (a < 0 && b > 0) return cocienteEnt(-a, -b) - 1;
    if (a < 0 && b < 0) return cocienteEnt(-a, -b) + 1;
    return ultC = -1;
  }

  public static int restoEnt(int a, int b) {
    if (abs(a) == ultA && abs(b) == ultB) {
      return ultR;
    }
    cocienteEnt(a, b);
    return ultR;
    // return ultR = (a - cocienteEnt(a, b) * b);
  }

  private static int abs(int a) {
    return a < 0 ? -a : a;
  }

  // PRUEBAS ///////////////////////////////////////////////////////////////////

  public static void main(String[] args) {
    System.out.println(cocienteEnt(5, 2));
    System.out.println(restoEnt(5, 2));
    System.out.println();
    System.out.println(cocienteEnt(-5, 2));
    System.out.println(restoEnt(-5, 2));
    System.out.println();
    System.out.println(cocienteEnt(9, 5));
    System.out.println(restoEnt(9, 5));
    System.out.println();
    System.out.println(cocienteEnt(-5, -2));
    System.out.println(restoEnt(-5, -2));
    System.out.println();
    System.out.println(cocienteEnt(5, -2));
    System.out.println(restoEnt(5, -2));
  }
}
