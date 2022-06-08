package TP4;

public class E9 {

  /*
   * Implementar un método recursivo que dada una matriz de números enteros
   * devuelva la suma de sus elementos.
   */
  public static int sumaMatriz(int mat[][]) {
    if (mat.length == 0 || mat[0].length == 0) return 0;
    return sumaMatRec(mat, 0, 0);
  }

  public static int sumaMatRec(int mat[][], int i, int j) {
    // ultima columna completa (caso base)
    if (i == mat.length) return 0;
    // columna completa, sumar siguiente columna desde la primera fila
    if (j == mat[i].length) return sumaMatRec(mat, i + 1, 0);
    // sumar elemento actual con el de la siguiente fila
    return mat[i][j] + sumaMatRec(mat, i, j + 1);
  }

  // PRUEBAS

  public static void main(String[] args) {
    int[][] mat = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    System.out.println(sumaMatriz(mat)); // 45
  }
}
