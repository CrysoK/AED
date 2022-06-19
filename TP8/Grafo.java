package TP8;

import TP6.QueueLL;
import TP7.UList;
import Utils.Par;

public class Grafo implements OperacionesG {

  protected MatrizG matriz;
  protected int orden;
  // protected double infinito = Double.POSITIVE_INFINITY;
  protected double inf = -1;

  public Grafo(int orden) {
    this.orden = orden;
    matriz = new MatrizG(orden);
  }

  public Grafo(String archivo) {
    matriz = new MatrizG(archivo);
    orden = matriz.getFilas();
  }

  public int getOrden() {
    return orden;
  }

  public void printCostos() {
    printCostosP(matriz);
  }

  protected void printCostosP(MatrizG costos) {
    System.out.println("Costos de las aristas:");
    for (int f = 0; f < orden; f++) {
      for (int c = 0; c < orden; c++) {
        if (f != c) {
          double costo = costos.getDouble(f, c);
          if (costo != inf) {
            System.out.printf("%d -> %d = %.2f\n", f, c, costo);
          }
        }
      }
    }
  }

  public void printMatriz() {
    System.out.println("Matriz de adyacencia:");
    System.out.println(matriz);
  }

  public void printBusqEnProfundidad() {
    boolean visitado[] = new boolean[orden];
    System.out.println("Busqueda en profundidad");
    for (int v = 0; v < orden; v++) {
      if (!visitado[v]) {
        busqEnProfRec(visitado, v);
      }
    }
  }

  private void busqEnProfRec(boolean[] visitado, int v) {
    visitado[v] = true;
    if (todoVisitado(v, visitado)) {
      System.out.println(v);
      return;
    }
    for (int i = 0; i < orden; i++) {
      if (matriz.getDouble(v, i) != inf && !visitado[i]) {
        System.out.printf("%d -> ", v);
        busqEnProfRec(visitado, i);
      }
    }
  }

  private boolean todoVisitado(int v, boolean[] visitado) {
    for (int i = 0; i < orden; i++) {
      if (matriz.getDouble(v, i) != inf && !visitado[i]) {
        return false;
      }
    }
    return true;
  }

  private UList busqEnProf2() {
    // Inicio contiene al nodo inicial y otros nodos que no
    // pueden ser alcanzados por este.
    UList inicios = new UList();

    boolean visitado[] = new boolean[orden];

    // Ciclo que busca "nodos iniciales"
    for (int v = 0; v < orden; v++) {
      if (!visitado[v]) {
        Par par = new Par(v, busqEnProf2Rec(visitado, v));
        inicios.insert(par);
      }
    }
    return inicios;
  }

  private UList busqEnProf2Rec(boolean[] visitado, int v) {
    UList caminos = new UList();
    visitado[v] = true;
    for (int i = 0; i < orden; i++) {
      if (matriz.getDouble(v, i) != inf && !visitado[i]) {
        Par par = new Par(i, busqEnProf2Rec(visitado, i));
        caminos.insert(par);
      }
    }
    return caminos;
  }

  public void printBusqEnProfundidad2() {
    UList bep = busqEnProf2();
    UList.Iterador iter = bep.iterador(0);
    System.out.println("Busqueda en profundidad");
    for (int i = 0; i < bep.length(); i++) {
      printBusqEnProfRec((Par) iter.next());
    }
  }

  private void printBusqEnProfRec(Par par) {
    int v = (int) par.get1();
    UList caminos = (UList) par.get2();
    if (caminos.length() == 0) {
      System.out.println(v);
      return;
    }
    UList.Iterador iter = caminos.iterador(0);
    for (int i = 0; i < caminos.length(); i++) {
      if (i > 0) {
        System.out.print("... ");
      }
      System.out.print(v + " -> ");
      printBusqEnProfRec((Par) iter.next());
    }
  }

  public void printBusqEnAmplitud() {
    boolean visitado[] = new boolean[orden];
    System.out.println("Busqueda en amplitud");
    for (int v = 0; v < orden; v++) {
      if (!visitado[v]) {
        busqEnAmpl(visitado, v);
      }
    }
  }

  private void busqEnAmpl(boolean[] visitado, int v) {
    QueueLL cola = new QueueLL();

    System.out.println("Inicio: " + v);
    visitado[v] = true;
    cola.push(v);

    while (!cola.isEmpty()) {
      int e = (int) cola.pop();
      boolean nuevoEncargado = false;
      for (int i = 0; i < orden; i++) {
        if (matriz.getDouble(e, i) != inf && !visitado[i]) {
          if (e != v && !nuevoEncargado) {
            System.out.println("(" + e + ")");
            nuevoEncargado = true;
          }
          System.out.println(e + " -> " + i);
          visitado[i] = true;
          cola.push(i);
        }
      }
    }
  }

  protected boolean esMenor(double a, double b) {
    if (a == inf && b != inf) return false;
    if (a != inf && b == inf) return true;
    return a < b;
  }

  protected boolean esMayorIgual(double a, double b) {
    if (a == inf && b != inf) return true;
    if (a != inf && b == inf) return false;
    if (a == inf && b == inf) return true;
    return a >= b;
  }

  protected double sumar(double a, double b) {
    if (a == inf || b == inf) return inf;
    return a + b;
  }

  // PRUEBAS ///////////////////////////////////////////////////////////////////
  public static void main(String[] args) {
    Grafo g = new Grafo("G1.txt");
    g.printBusqEnProfundidad();
    System.out.println();
    g.printBusqEnAmplitud();
  }
}
