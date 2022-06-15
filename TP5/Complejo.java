package TP5;

// EJERCICIO 4 /////////////////////////////////////////////////////////////////

// TODO
public class Complejo implements Operaciones {

  private double r;
  private double i;

  public Complejo(double real, double imaginaria) {
    this.r = real;
    this.i = imaginaria;
  }

  public double getReal() {
    return this.r;
  }

  public void setReal(double real) {
    this.r = real;
  }

  public double getImaginaria() {
    return this.i;
  }

  public void setImaginaria(double imaginaria) {
    this.i = imaginaria;
  }

  public String toString() {
    return new StringBuilder("")
      .append(this.r)
      .append(" + ")
      .append(this.i)
      .append("i")
      .toString();
  }

  public Object suma(Object a) {
    Complejo A = (Complejo) a;
    return new Complejo(this.r + A.r, this.i + A.i);
  }

  public Object resta(Object a) {
    return null;
  }

  public Object producto(Object a) {
    return null;
  }

  public Object division(Object b) {
    return null;
  }

  public Object potencia(Object a) {
    return null;
  }

  public boolean iguales(Object a) {
    return false;
  }

  public Object conjugado() {
    return null;
  }

  public double modulo() {
    return 0;
  }

  // PRUEBAS ///////////////////////////////////////////////////////////////////
  public static void main(String[] args) {
    Complejo a = new Complejo(1, 2);
    Complejo b = new Complejo(2, 3);
    Complejo res = (Complejo) a.suma(b);
    System.out.println(res);
  }
}
