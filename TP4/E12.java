package TP4;

public class E12 {

  /* Jaimito el cartero, oriundo de Tangamandapio, no desea sobrecargar su
   * mochila con paquetes pesados; a lo sumo, V kilos. Puede cargarla de
   * paquetes con pesos (enteros positivos) conocidos de antemano (p1, p2,
   * p3,..., pn).
   *
   * Se pide: implementar de manera recursiva, la selección de paquetes tal que
   * no se sobrecargue la mochila de Jaimito.
   *
   * Desarrollar un programa que cargue los pesos (generados en forma aleatoria)
   * en un vector y la capacidad de la mochila, invoque al módulo recursivo y
   * muestre (en detalle) los paquetes cargados en la mochila.
   *
   * Ejemplo: si V= 12 y los paquetes pesan 4,3,6,2,1, una buena selección se
   * logra eligiendo al primero, al tercero y al cuarto.
   */

  // TODO
  public static boolean mochila(int capacidad, int[] paquetes, int paquete) {
    int c = capacidad;
    int i = paquete;
    // se pasa de la capacidad máxima
    if (c < 0) return false;
    // mochila llena
    if (c == 0) return true;
    // quedan paquetes?
    if (i > paquetes.length - 1) return false;

    // si el paquete cabe, ver qué pasa al agregarlo
    if ((c - paquetes[i]) > c) {
      // el paquete cabe
      if (mochila(c - paquetes[i], paquetes, i++)) {
        return true;
      } else return false;
      // si no cabe, probar con el siguiente
    } else {
      // el paquete es demasiado pesado
      if (mochila(c, paquetes, i++)) {
        return true;
      } else return false;
    }
  }

  public static void main(String[] args) {
    int[] paquetes = { 4, 5, 6, 2, 1 };
    int capacidad = 12;
    boolean res = mochila(capacidad, paquetes, 0);
    System.out.println(res);
  }
}
