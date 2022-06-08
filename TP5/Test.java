package TP5;

public class Test {

  public static void main(String[] args) {
    Complejo a = new Complejo(1, 2);
    Complejo b = new Complejo(2, 3);
    Complejo res = (Complejo) a.suma(b);
    System.out.println(res);
  }

  public static Complejo sumar(Complejo a, Complejo b) {
    return (Complejo) a.suma(b);
  }
}
