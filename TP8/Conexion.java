package TP8;

public class Conexion {

  private int a;
  private int b;
  private double costo;

  public Conexion(int a, int b, double costo) {
    this.a = a;
    this.b = b;
    this.costo = costo;
  }

  public int getA() {
    return a;
  }

  public int getB() {
    return b;
  }

  public double getCosto() {
    return costo;
  }

  public int comparar(Conexion b) {
    return (int) (this.costo - b.costo);
  }
}
