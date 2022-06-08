package Utils;

public class Par {

  int c1;
  int c2;

  public Par(int a, int b) {
    c1 = a;
    c2 = b;
  }

  public int get1() {
    return c1;
  }

  public int get2() {
    return c2;
  }

  public int c1() {
    return c1;
  }

  public int c2() {
    return c2;
  }

  public int get(int componente) {
    if (componente == 1) return c1;
    if (componente == 2) return c2;
    return 0;
  }

  public String toString() {
    return String.format("(%d, %d)", this.c1, this.c2);
  }
}
