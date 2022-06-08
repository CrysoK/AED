package TP5;

// EJERCICIO 2

public class Entero implements Operaciones {

  private int n;

  public Entero(int num) {
    n = num;
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

  public Object resto(Object b) {
    return null;
  }
}
