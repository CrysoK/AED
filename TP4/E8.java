package TP4;

public class E8 {

  /*
   * Escribir un método recursivo que calcule el promedio de un vector de
   * números enteros.
   */
  public static float promedioVec(int[] vec) {
    if (vec.length == 0) return 0;
    return promedioRec(vec, 0, 0);
  }

  private static float promedioRec(int[] vec, int i, int sum) {
    if (i == vec.length) return (float) sum / (float) vec.length;
    return promedioRec(vec, i + 1, sum + vec[i]);
  }

  // PRUEBAS

  public static void main(String[] args) {
    int[] vec = { 6, 8, 7, 9 };
    System.out.println(promedioVec(vec));
  }
}
