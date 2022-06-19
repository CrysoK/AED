package TP5;

import TP1.Div;
import TP2.MCD;

// EJERCICIO 2

public class Entero implements Operaciones {

  private int n;

  public Entero(int num) {
    n = num;
  }

  // Casteo pero más "corto"
  public static Entero Z(Object a) {
    return (Entero) a;
  }

  /* Método auxiliar para obtener int de un Object */
  private static int Int(Object a) {
    return ((Entero) a).n;
  }

  public Object suma(Object b) {
    return new Entero(n + Int(b));
  }

  public Object resta(Object b) {
    return new Entero(n - Int(b));
  }

  public Object producto(Object b) {
    return new Entero(n * Int(b));
  }

  public Object potencia(Object exp) {
    int res = 0;
    for (int i = 0; i < Int(exp); i++) {
      res = res * n;
    }
    return new Entero(res);
  }

  public boolean iguales(Object b) {
    return n == Int(b);
  }

  public Object division(Object b) {
    return new Entero(Div.cocienteEnt(this.n, Int(b)));
  }

  public Object resto(Object b) {
    return new Entero(Div.restoEnt(this.n, Int(b)));
  }

  public Object mcd(Object b) {
    return new Entero(MCD.mcd(this.n, Int(b)));
  }
}
