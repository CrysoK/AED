package TP8;

import java.util.Scanner;

public class GrafoND extends Grafo {

  public GrafoND(String archivo) {
    super(archivo);
  }

  public GrafoND(int orden) {
    super(orden);
  }

  public GrafoND(Scanner input) {
    System.out.print("Ingrese el orden del grafo: ");
    this.orden = input.nextInt();
    this.matriz = new MatrizG(orden);
    // mitad inferior (f >= c)
    for (int v = 0; v < orden; v++) {
      for (int d = 0; d < (v + 1); d++) {
        if (v != d) {
          System.out.printf("%d <-> %d = ", d, v);
          String costo = quitarCeros(input.nextDouble());
          matriz.set(costo, d, v);
          matriz.set(costo, v, d);
        } else {
          matriz.set("-1", d, v);
        }
      }
    }
  }

  public void toFile(String archivo) {
    matriz.writeToFile(archivo, "ND");
  }

  public class Prim {

    public Prim(int inicio) {
      // Inicialización
      double[] costos = new double[orden];
      int[] padres = new int[orden];

      for (int i = 0; i < orden; i++) {
        costos[i] = matriz.getCosto(inicio, i);
        padres[i] = inicio;
      }

      padres[inicio] = -1;

      for (int i = 1; i < orden; i++) {
        double costoMin = inf; // posible infinito
        int verticeMin = -1;

        // Búsqueda del vértice con costo mínimo

        for (int j = 0; j < orden; j++) {
          double costoAct = costos[j];
          if (
            padres[j] != -1 && costoAct != inf && esMenor(costoAct, costoMin)
          ) {
            costoMin = costoAct;
            verticeMin = j;
          }
        }

        System.out.printf("%d <-> %d\n", padres[verticeMin], verticeMin);
        costos[verticeMin] = inf;
        padres[verticeMin] = -1;

        // Actualización de costos

        for (int j = 0; j < orden; j++) {
          if (j == verticeMin) continue;
          double costoAct = costos[j];
          double costoNuevo = matriz.getCosto(verticeMin, j);
          int padre = padres[j];
          if (padre > -1 && esMenor(costoNuevo, costoAct)) {
            costos[j] = costoNuevo;
            padres[j] = verticeMin;
          }
        }
      }
    }

    public void print() {}
  }

  public class Kruskal {

    public Kruskal() {}
  }
}
