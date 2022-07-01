package TP7.Tests;

import TP7.OList;

public class Test1 {

  /**
   * Rendimiento de búsqueda binaria y búsqueda lineal en una lista
   * doblemente enlazada
   * */
  public static void main(String[] args) {
    int n = 1000000;
    OList lista = new OList((a, b) -> ((int) a) - ((int) b));
    int[] buscar = new int[] { 0, 499999, 500000, 500001, 999999 };
    int reps = 1000;

    for (int i = 0; i < n; i++) {
      lista.insert(i);
    }

    System.out.printf(
      "Se buscaron %,d, %,d, %,d y %,d entre %,d elementos ordenados\n",
      buscar[0],
      buscar[1],
      buscar[2],
      buscar[3],
      n
    );
    System.out.printf("Resultados de %,d repeticiones por cada tipo:\n", reps);

    for (int tipoBusq = 0; tipoBusq < 2; tipoBusq++) {
      long peor = 0;
      long mejor = Long.MAX_VALUE;
      long prom = 0;
      long sum = 0;
      long ini;
      long fin;
      long dur;
      int buscado = 0;
      for (int i = 0; i < reps; i++) {
        if (tipoBusq == 0) {
          ini = System.nanoTime();
          lista.searchBin(buscar[buscado]);
          fin = System.nanoTime();
          dur = fin - ini;
        } else {
          ini = System.nanoTime();
          lista.search(buscar[buscado]);
          fin = System.nanoTime();
          dur = fin - ini;
        }
        peor = dur > peor ? dur : peor;
        mejor = dur < mejor ? dur : mejor;
        sum += dur;
        // Esto hace que se busque otra vez desde el primer elemento de 'buscar'
        // una vez que se llegó al último
        buscado = (buscado + 1) % buscar.length;
      }
      prom = sum / reps;

      String tipo = tipoBusq == 0 ? "Búsqueda binaria" : "Búsqueda lineal";
      System.out.println("[" + tipo + "]");

      System.out.printf("Peor tiempo: %,f ms\n", nanoToMili(peor));
      System.out.printf("Mejor tiempo: %,f ms\n", nanoToMili(mejor));
      System.out.printf("Tiempo promedio: %,f ms\n", nanoToMili(prom));
    }
  }

  static double nanoToMili(long nano) {
    return ((double) nano) / 1000000.0;
  }
}
