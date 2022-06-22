package TP8;

import TP7.List;
import TP7.ColaP;
import TP7.Set;
import TP7.UList;
import java.util.Comparator;
import java.util.Scanner;

public class GrafoND extends Grafo {

  // Mensajes extra durante la ejecución
  private static boolean INFO = false;

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

  /**
   * El algoritmo de Prim determina un árbol recubridor mínimo de un grafo
   * conexo, no dirigido y ponderado. Encuentra un subconjunto de aristas que
   * forman un árbol con todos los vértices del grafo y donde el peso total de
   * todas las aristas es el mínimo posible.
   */
  public class Prim {

    private UList arbol = new UList();
    private double costoTotal = 0;

    /**
     * Ejecuta el algoritmo de Prim.
     * @param inicio Vértice inicial.
     */
    public Prim(int inicio) {
      if (INFO) System.out.println("[Prim]");

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

        // Se incluye la "conexión" al árbol recubridor mínimo
        arbol.insert(
          new Conexion(padres[verticeMin], verticeMin, costos[verticeMin])
        );
        costoTotal += costos[verticeMin];

        if (INFO) System.out.printf(
          "%d <-> %d = %.2f\n",
          padres[verticeMin],
          verticeMin,
          costos[verticeMin]
        );

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

    public void print() {
      System.out.println("[Prim]");
      printARM(arbol, costoTotal);
    }
  }

  /**
   * El algoritmo de Kruskal determina un árbol recubridor mínimo de un grafo
   * conexo y ponderado. Encuentra un subconjunto de aristas que
   * forman un árbol con todos los vértices del grafo y donde el peso total de
   * todas las aristas es el mínimo posible.
   */
  public class Kruskal {

    private UList arbol = new UList();
    private double costoTotal = 0;

    /**
     * Ejecuta el algoritmo de Kruskal.
     */
    public Kruskal() {
      if (INFO) System.out.println("[Kruskal]");
      // Comparador para vértices (números enteros)
      Comparator<Object> compVertices = new Comparator<Object>() {
        public int compare(Object a, Object b) {
          return ((int) a) - ((int) b);
        }
      };
      // Comparador para "conexiones"
      Comparator<Object> compConexiones = new Comparator<Object>() {
        public int compare(Object a, Object b) {
          // Se invierte el orden para ordenar de menor a mayor
          return ((Conexion) b).comparar((Conexion) a);
        }
      };
      // Inicialización de lista de conjuntos
      UList sets = new UList();
      for (int i = 0; i < orden; i++) {
        Set set = new Set(compVertices);
        set.add(i);
        sets.insert(set, i);
      }
      // Creación de la cola de prioridad
      ColaP conexiones = new ColaP(compConexiones);
      for (int i = 0; i < orden; i++) {
        for (int j = i + 1; j < orden; j++) {
          double costo = matriz.getCosto(i, j);
          if (costo != inf) {
            conexiones.push(new Conexion(i, j, costo));
          }
        }
      }
      // Creación del árbol recubridor mínimo
      while (arbol.length() < orden - 1) {
        Conexion c = (Conexion) conexiones.pop();
        double costo = c.getCosto();
        int a = c.getA();
        int b = c.getB();
        if (INFO) System.out.printf("%d <-> %d = %.2f\n", a, b, costo);
        // Búsqueda de los conjuntos que contienen a los vértices 'a' y 'b'
        int posA = -1;
        int posB = -1;
        int posSet = 0;
        while (
          (posSet <= sets.length() - 1) &&
          (posA == -1 || posB == -1 || posA != posB)
        ) {
          Set set = (Set) sets.get(posSet);
          posA = set.esElemento(a) ? posSet : posA;
          posB = set.esElemento(b) ? posSet : posB;
          posSet += 1;

          if (INFO) System.out.print(set + " ");
        }
        if (INFO) System.out.println();
        // Si los conjuntos son distintos, se unen
        if (posA != posB) {
          Set setA = (Set) sets.get(posA);
          Set setB = (Set) sets.get(posB);
          Set setU = setA.union(setB, compVertices);
          sets.replace(setU, posA);
          sets.del(posB);
          // Y se incluye la "conexión" al árbol
          arbol.insert(c);
          costoTotal += costo;

          if (INFO) {
            System.out.printf("Árbol mínimo: %d <-> %d = %.2f\n", a, b, costo);
          }
        }
      }
    }

    public void print() {
      System.out.println("[Kruskal]");
      printARM(arbol, costoTotal);
    }
  }

  private void printARM(UList arbol, double costo) {
    System.out.printf("Arbol recubridor mínimo (costo total: %.2f)\n", costo);
    List.Iterador it = arbol.iterador(0);
    while (it.hasNext()) {
      Conexion c = (Conexion) it.next();
      System.out.printf("%d <-> %d = %.2f\n", c.getA(), c.getB(), c.getCosto());
    }
  }
}
