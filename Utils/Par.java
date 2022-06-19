package Utils;

public class Par {

  Object componente1;
  Object componente2;

  public Par(Object a, Object b) {
    componente1 = a;
    componente2 = b;
  }

  public Object get1() {
    return componente1;
  }

  public Object get2() {
    return componente2;
  }

  public Object c1() {
    return componente1;
  }

  public Object c2() {
    return componente2;
  }

  public Object get(int componente) {
    if (componente == 1) return componente1;
    if (componente == 2) return componente2;
    return 0;
  }

  public String toString() {
    return "(" + componente1.toString() + ", " + componente2.toString() + ")";
  }
}
