package TP8;

import TP6.ColaLL;
import TP7.UList;
import Utils.Par;

public abstract class Grafo implements OperacionesG {

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

  protected Grafo() {}

  public void setCosto(double costo, int vertice, int destino) {
    matriz.set(costo, vertice, destino);
  }

  public void setArista(boolean conectado, int vertice, int destino) {
    if (conectado) {
      matriz.set(1, vertice, destino);
    } else {
      matriz.set(inf, vertice, destino);
    }
  }

  public int getOrden() {
    return orden;
  }

  public void printCostos() {
    System.out.println("Costos de las aristas:");
    printCostosP(matriz);
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

  public void printBusqEnProfundidad2() {
    UList bep = busqEnProf2();
    UList.Iterador iter = bep.iterador(0);
    System.out.println("Busqueda en profundidad");
    for (int i = 0; i < bep.length(); i++) {
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

  protected void printCostosP(MatrizG costos) {
    for (int f = 0; f < orden; f++) {
      for (int c = 0; c < orden; c++) {
        if (f != c) {
          double costo = costos.getCosto(f, c);
          if (costo != inf) {
            System.out.printf("%d -> %d = %.2f\n", f, c, costo);
          }
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

  protected String quitarCeros(double n) {
    if (n == (double) (int) n) {
      return String.valueOf((int) n);
    }
    return Double.toString(n);
    // Alternativa
    // DecimalFormat formato = new DecimalFormat("0.#");
    // return formato.format(n);
  }

  private void busqEnProfRec(boolean[] visitado, int v) {
    visitado[v] = true;
    if (todoVisitado(v, visitado)) {
      System.out.println(v);
      return;
    }
    for (int i = 0; i < orden; i++) {
      if (matriz.getCosto(v, i) != inf && !visitado[i]) {
        System.out.printf("%d -> ", v);
        busqEnProfRec(visitado, i);
      }
    }
  }

  private boolean todoVisitado(int v, boolean[] visitado) {
    for (int i = 0; i < orden; i++) {
      if (matriz.getCosto(v, i) != inf && !visitado[i]) {
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
      if (matriz.getCosto(v, i) != inf && !visitado[i]) {
        Par par = new Par(i, busqEnProf2Rec(visitado, i));
        caminos.insert(par);
      }
    }
    return caminos;
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

  private void busqEnAmpl(boolean[] visitado, int v) {
    ColaLL cola = new ColaLL();

    System.out.println("Inicio: " + v);
    visitado[v] = true;
    cola.push(v);

    while (!cola.isEmpty()) {
      int e = (int) cola.pop();
      boolean nuevoEncargado = false;
      for (int i = 0; i < orden; i++) {
        if (matriz.getCosto(e, i) != inf && !visitado[i]) {
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

  // PRUEBAS ///////////////////////////////////////////////////////////////////
  public static void main(String[] args) {}
}
