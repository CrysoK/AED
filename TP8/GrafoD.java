package TP8;

import Utils.Matriz;
import java.util.Scanner;

public class GrafoD extends Grafo {

  public GrafoD(int orden) {
    super(orden);
  }

  public GrafoD(String archivo) {
    super(archivo);
  }

  public GrafoD(Scanner input) {
    // El cambio de Locale sirve para usar el punto como separador decimal
    System.out.print("Ingrese el orden del grafo: ");
    this.orden = input.nextInt();
    this.matriz = new MatrizG(orden);
    for (int v = 0; v < orden; v++) {
      for (int d = 0; d < orden; d++) {
        if (v != d) {
          System.out.printf("%d <-> %d = ", v, d);
          String costo = quitarCeros(input.nextDouble());
          matriz.set(costo, v, d);
        } else {
          matriz.set("-1", v, d);
        }
      }
    }
  }

  public void toFile(String archivo) {
    matriz.writeToFile(archivo, "D");
  }

  /**
   * El algoritmo de Dijkstra determina el camino más corto (según el costo de
   * las aristas) entre un vértice dado y el resto de vértices del grafo.
   */
  public class Dijkstra {

    private double[] costos = new double[orden];
    private int[] caminos = new int[orden];
    private int inicio;

    /**
     * Ejecuta el algoritmo de Dijkstra.
     * @param inicio Vértice inicial.
     */
    public Dijkstra(int inicio) {
      boolean[] visitados = new boolean[orden];
      this.inicio = inicio;
      double costoMin;
      double costoSig;
      int verticeMin;

      // Calcular distancias y caminos iniciales
      for (int i = 0; i < orden; i++) {
        if (i == inicio) continue;
        costos[i] = matriz.getCosto(inicio, i);
        caminos[i] = inicio;
      }

      costos[inicio] = inf;
      caminos[inicio] = -1;
      visitados[inicio] = true;

      for (int i = 1; i < orden; i++) {
        // Buscar el vértice con el costo mínimo

        costoMin = inf; // Posible infinito
        verticeMin = -1;

        for (int w = 0; w < orden; w++) {
          if (w != inicio && esMenor(costos[w], costoMin) && !visitados[w]) {
            costoMin = costos[w];
            verticeMin = w;
          }
        }
        if (verticeMin == -1) continue;

        visitados[verticeMin] = true;
        costos[verticeMin] = costoMin;

        // Visitar el vértice y actualizar los caminos

        for (int v = 0; v < orden; v++) {
          if (visitados[v]) continue;
          costoSig = matriz.getCosto(verticeMin, v); // Posible infinito
          if (esMenor(sumar(costoMin, costoSig), costos[v])) {
            costos[v] = costoMin + costoSig;
            caminos[v] = verticeMin;
          }
        }
      }
    }

    public double[] getCostos() {
      return costos;
    }

    public int[] getCaminos() {
      return caminos;
    }

    /**
     * Muestra el camino más corto entre el vértice inicial y un vértice dado.
     * @param hasta Vértice final.
     */
    public void print(int hasta) {
      System.out.println("[Dijkstra]");
      System.out.printf(
        "Camino con menor costo desde %d hasta %d (total: %.2f)\n",
        inicio,
        hasta,
        costos[hasta]
      );
      if (caminos[hasta] == -1) {
        System.out.println("No hay camino");
        return;
      }
      caminoInverso(hasta);
      // System.out.println(hasta);
      // caminoInverso(hasta, costos[hasta]);
    }

    private void caminoInverso(int v) {
      if (v == -1/*  || caminos[v] == -1 */) return;
      int c = caminos[v];
      if (c == -1) return;
      caminoInverso(c);
      System.out.printf(
        "%d -> %d = %.2f (%.2f)\n",
        c,
        v,
        matriz.getCosto(c, v),
        costos[v]
      );
      // System.out.printf("%d -> %d (%.2f) \n", caminos[v], v, costo);
    }
  }

  /**
   * El algoritmo de Floyd determina el camino mínimo entre todos los pares de
   * vértices de un grafo dirigido ponderado.
   */
  public class Floyd {

    private MatrizG costos = new MatrizG(orden);
    private MatrizG caminos = new MatrizG(orden);

    /**
     * Ejecuta el algoritmo de Floyd.
     */
    public Floyd() {
      // Inicialización de costos y caminos
      for (int i = 0; i < orden; i++) {
        for (int j = 0; j < orden; j++) {
          if (i != j) {
            double costo = matriz.getCosto(i, j);
            costos.set(costo, i, j);
          } else {
            costos.set((double) 0, i, j);
          }
          caminos.set(-1, i, j);
        }
      }

      for (int v = 0; v < orden; v++) {
        for (int f = 0; f < orden; f++) {
          for (int c = 0; c < orden; c++) {
            double costoA = costos.getCosto(f, v);
            double costoB = costos.getCosto(v, c);
            double costoC = costos.getCosto(f, c);
            if (esMenor(sumar(costoA, costoB), costoC)) {
              costos.set(costoA + costoB, f, c);
              caminos.set(v, f, c);
            }
          }
        }
      }
    }

    public MatrizG getCostos() {
      return costos;
    }

    public Matriz getCaminos() {
      return caminos;
    }

    /**
     * Muestra los costos mínimos entre todos los pares de vértices.
     */
    public void printCostos() {
      System.out.println("[Floyd]");
      System.out.println("Costos mínimos entre pares de vértices:");
      printCostosP(costos);
    }

    /**
     * Muestra los caminos mínimos entre todos los pares de vértices.
     */
    public void printCaminos() {
      System.out.println("[Floyd]");
      System.out.println("Caminos con costo mínimo entre pares de vértices:");
      for (int i = 0; i < orden; i++) {
        for (int j = 0; j < orden; j++) {
          if (i != j) {
            printCaminoP(i, j);
          }
        }
      }
    }

    /**
     * Muestra el camino mínimo entre dos vértices.
     * @param desde Vértice inicial.
     * @param hasta Vértice final.
     */
    public void printCamino(int desde, int hasta) {
      System.out.println("Floyd");
      printCaminoP(desde, hasta);
    }

    private void printCaminoP(int desde, int hasta) {
      double costo = costos.getCosto(desde, hasta);
      if (costo == inf) {
        System.out.println("No hay camino entre " + desde + " y " + hasta);
        return;
      }
      System.out.printf("%d --> %d = %.2f\n", desde, hasta, costo);
      System.out.printf("(%d)", desde);
      printCaminoRec(desde, hasta);
      System.out.printf("(%d)\n", hasta);
    }

    private void printCaminoRec(int desde, int hasta) {
      int camino = caminos.getVertice(desde, hasta);
      if (camino == -1) {
        System.out.print(" -> ");
        return;
      }
      printCaminoRec(desde, camino);
      System.out.print(camino);
      printCaminoRec(camino, hasta);
    }
  }
}
