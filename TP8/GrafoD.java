package TP8;

import Utils.Matriz;

public class GrafoD extends Grafo {

  public GrafoD(int orden) {
    super(orden);
  }

  public GrafoD(String archivo) {
    super(archivo);
  }

  public class Dijkstra {

    private double[] costos = new double[orden];
    private int[] caminos = new int[orden];
    private int inicio;

    public Dijkstra(int inicio) {
      boolean[] visitados = new boolean[orden];
      this.inicio = inicio;
      double costoMin;
      double costoSig;
      int verticeMin;

      // Calcular distancias y caminos iniciales
      for (int i = 0; i < orden; i++) {
        if (i == inicio) continue;
        costos[i] = matriz.getDouble(inicio, i);
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
          costoSig = matriz.getDouble(verticeMin, v); // Posible infinito
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

    public void print(int hasta) {
      System.out.println("Dijkstra:");
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
      if (c != -1) {
        caminoInverso(c);
        System.out.printf(
          "%d -> %d = %.2f (%.2f)\n",
          c,
          v,
          matriz.getDouble(c, v),
          costos[v]
        );
      }
      // System.out.printf("%d -> %d (%.2f) \n", caminos[v], v, costo);
    }
  }

  public class Floyd {

    private MatrizG costos;
    private Matriz caminos;

    public Floyd() {
      costos = new MatrizG(orden);
      caminos = new MatrizG(orden);

      for (int f = 0; f < orden; f++) {
        for (int c = 0; c < orden; c++) {
          if (f != c) {
            costos.set(matriz.getInt(f, c), f, c);
          } else {
            costos.set(0, f, c);
          }
        }
      }

      for (int k = 0; k < orden; k++) {
        for (int i = 0; i < orden; i++) {
          for (int j = 0; j < orden; j++) {
            int costoA = (int) costos.get(i, k);
            int costoB = (int) costos.get(k, j);
            int costoC = (int) costos.get(i, j);
            if (costoA + costoB < costoC) {
              costos.set(costoA + costoB, i, j);
              caminos.set(k, i, j);
            }
          }
        }
      }
    }

    public void print() {
      System.out.println("Floyd:");
      printCostosP(costos);
    }

    public MatrizG getCostos() {
      return costos;
    }

    public Matriz getCaminos() {
      return caminos;
    }
  }
}
