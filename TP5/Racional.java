package TP5;

// EJERCICIO 3

public class Racional implements Operaciones {

  private Entero num;
  private Entero den;

  public Racional(Entero a, Entero b) {
    num = a;
    den = b;
  }

  // Casteo pero más "corto"
  public static Racional Q(Object a) {
    return (Racional) a;
  }

  // Casteo pero más "corto"
  private Entero Z(Object a) {
    return Entero.Z(a);
  }

  public Entero getNum() {
    return num;
  }

  public Entero getDen() {
    return den;
  }

  public Object suma(Object b) {
    Racional r = Q(b);
    Entero t1 = Z(num.producto(r.den));
    Entero t2 = Z(den.producto(r.num));
    Entero resNum = Z(t1.suma(t2));
    Entero resDen = Z(den.producto(r.den));
    return new Racional(resNum, resDen).reducir();
  }

  public Object resta(Object b) {
    Racional r = Q(b);
    Entero cero = new Entero(0);
    r.num = Z(cero.resta(r.num));
    return this.suma(r);
  }

  public Object producto(Object b) {
    Racional r = Q(b);
    Entero resNum = Z(num.producto(r.num));
    Entero resDen = Z(den.producto(r.den));
    return new Racional(resNum, resDen).reducir();
  }

  public Object potencia(Object exp) {
    return null;
  }

  public boolean iguales(Object b) {
    return false;
  }

  public Racional reducir() {
    Entero mcd = Z(num.mcd(den));
    this.num = Z(num.division(mcd));
    this.den = Z(den.division(mcd));
    return this;
  }
}
