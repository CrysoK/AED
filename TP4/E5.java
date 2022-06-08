package TP4;

// import TP1.E11;
import TP1.Div;
import Utils.Par;

public class E5 {

  /*
   * Obtener de manera recursiva, el st de dos números enteros dados
   */
  public static Par calcST(int a, int b) {
    if (b == 0) {
      int signo = a < 0 ? -1 : 1;
      return new Par(signo, 0);
    }
    int c = Div.cocienteEnt(a, b);
    int r = Div.restoEnt(a, b);
    Par st = calcST(b, r);
    int s = st.c1();
    int t = st.c2();
    return new Par(t, s - t * c);
  }

  public static void main(String[] args) {
    System.out.println(calcST(150, 29));
  }
}
