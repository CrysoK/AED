package TP8;

import TP7.PQueue;
import TP7.Set;
import TP7.UList;
import java.util.Comparator;
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

    public Kruskal() {
      Comparator<Object> cInts = new Comparator<Object>() {
        public int compare(Object a, Object b) {
          return ((int) a) - ((int) b);
        }
      };

      Comparator<Object> cConexiones = new Comparator<Object>() {
        public int compare(Object a, Object b) {
          // Se invierte el orden para ordenar de menor a mayor
          return ((Conexion) b).comparar((Conexion) a);
        }
      };

      double currCost;
      int counter;
      int n;
      int k;
      int posI;
      int posJ;
      boolean flag;
      Conexion conexion;
      PQueue colaP = new PQueue(cConexiones);
      Set conjuntoE;
      Set conjuntoU;
      UList sets = new UList();

      // Inicialización

      for (int i = 0; i < orden; i++) {
        conjuntoE = new Set(cInts);
        conjuntoE.add(i);
        sets.insert(conjuntoE, i);
      }

      // Creación de la cola de prioridad

      for (int i = 0; i < orden; i++) {
        for (int j = i + 1; j < orden; j++) {
          currCost = matriz.getCosto(i, j);
          if (currCost != inf) {
            colaP.push(new Conexion(i, j, currCost));
          }
        }
      }

      counter = orden;
      while (counter > 1) {
        conexion = (Conexion) colaP.pop();
        System.out.printf(
          "%d <-> %d = %.2f\n",
          conexion.getA(),
          conexion.getB(),
          conexion.getCosto()
        );
        n = sets.length() - 1;
        k = 0;
        flag = false;
        posI = posJ = -1;

        while (k <= n && !flag) {
          conjuntoE = (Set) sets.get(k);
          System.out.print(conjuntoE + " ");
          if (conjuntoE.esElemento(conexion.getA())) {
            posI = k;
          }
          if (conjuntoE.esElemento(conexion.getB())) {
            posJ = k;
          }
          if (posI > 0 && posJ > 0 && posI == posJ) {
            flag = true;
          } else {
            k++;
          }
        }
        System.out.println();

        if (!flag) {
          System.out.printf(
            "(Árbol mínimo) %d <-> %d = %.2f\n",
            conexion.getA(),
            conexion.getB(),
            conexion.getCosto()
          );
          conjuntoU = ((Set) sets.get(posI)).union((Set) sets.get(posJ), cInts);
          sets.replace(conjuntoU, posI);
          sets.del(posJ);
          counter--;
        }
      }
    }
  }
}
