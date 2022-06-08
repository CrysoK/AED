package TP4;

public class E10 {

  /*
   * Implementar un método recursivo que dada una lista de enteros positivos
   * determine para cada uno de los elementos, si es primo o no. Devuelva la
   * lista resultado.
   */
  public static boolean[] primos(int[] nums) {
    if (nums.length == 0) return new boolean[0];
    boolean[] res = new boolean[nums.length];
    primosRec(nums, res, 0);
    return res;
  }

  private static void primosRec(int[] lista, boolean[] res, int i) {
    if (i == lista.length) return;
    res[i] = E2.esPrimoNat(lista[i]);
    primosRec(lista, res, i + 1);
  }

  public static void main(String[] args) {
    int[] lista = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    boolean[] res = primos(lista);
    for (int i = 0; i < res.length; i++) {
      System.out.println(res[i]);
    }
  }
}
