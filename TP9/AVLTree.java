package TP9;

import java.util.Comparator;

public class AVLTree {

  private static final boolean INFO = true;
  private NodoAVL raiz;
  private Comparator<Object> c;

  public AVLTree(Comparator<Object> comparador) {
    raiz = null;
    c = comparador;
  }

  public void insertar(Object dato) {
    if (INFO) System.out.println("Insertando " + dato);
    raiz = insertarRec(raiz, dato);
  }

  private NodoAVL insertarRec(NodoAVL nodo, Object dato) {
    if (nodo == null) {
      return new NodoAVL(dato);
    }
    int comp = c.compare(nodo.getDato(), dato);
    if (comp > 0) {
      nodo.setIzq(insertarRec(nodo.getIzq(), dato));
    } else nodo.setDer(insertarRec(nodo.getDer(), dato));

    nodo = balancear(nodo);
    updateAltura(nodo);
    return nodo;
  }

  private NodoAVL balancear(NodoAVL nodo) {
    int b = getBalance(nodo);
    if (b > 1) {
      if (getBalance(nodo.getIzq()) >= 0) {
        if (INFO) System.out.println("Rotacion simple a derecha");
        return rotar(nodo, 1);
      } else {
        if (INFO) System.out.println("Rotacion doble a derecha (izq - der)");
        nodo.setIzq(rotar(nodo.getIzq(), -1));
        return rotar(nodo, 1);
      }
    } else if (b < -1) {
      if (getBalance(nodo.getDer()) <= 0) {
        if (INFO) System.out.println("Rotacion simple a izquierda");
        return rotar(nodo, -1);
      } else {
        if (INFO) System.out.println("Rotacion doble a izquierda (der - izq)");
        nodo.setDer(rotar(nodo.getDer(), 1));
        return rotar(nodo, -1);
      }
    }
    return nodo;
  }

  private NodoAVL rotar(NodoAVL nodo, int sentido) {
    int contrario = -sentido;
    NodoAVL aux = nodo.getHijo(contrario);
    nodo.setHijo(aux.getHijo(sentido), contrario);
    aux.setHijo(nodo, sentido);

    updateAltura(nodo);
    updateAltura(aux);
    return aux;
  }

  private void updateAltura(NodoAVL nodo) {
    int izq = nodo.getIzq() != null ? nodo.getIzq().getAltura() : -1;
    int der = nodo.getDer() != null ? nodo.getDer().getAltura() : -1;
    nodo.setAltura(max(izq, der) + 1);
  }

  private int getBalance(NodoAVL nodo) {
    if (nodo == null) {
      return 0;
    }
    int izq = nodo.getIzq() != null ? nodo.getIzq().getAltura() : -1;
    int der = nodo.getDer() != null ? nodo.getDer().getAltura() : -1;
    return izq - der;
  }

  private int max(int a, int b) {
    return a > b ? a : b;
  }

  public void printEnOrden() {
    System.out.println("Recorrido \"inorden\" (izq, dato, der)");
    enOrden(raiz);
    System.out.println();
  }

  private void enOrden(NodoAVL nodo) {
    if (nodo == null) return;
    enOrden(nodo.getIzq());
    System.out.print(nodo.getDato() + " ");
    enOrden(nodo.getDer());
  }

  public void printPreOrden() {
    System.out.println("Recorrido \"preorden\" (dato, izq, der)");
    preOrden(raiz);
    System.out.println();
  }

  private void preOrden(NodoAVL nodo) {
    if (nodo == null) return;
    System.out.print(nodo.getDato() + " ");
    preOrden(nodo.getIzq());
    preOrden(nodo.getDer());
  }

  public void printPostOrden() {
    System.out.println("Recorrido \"postorden\" (izq, der, dato)");
    postOrden(raiz);
    System.out.println();
  }

  private void postOrden(NodoAVL nodo) {
    if (nodo == null) return;
    postOrden(nodo.getIzq());
    postOrden(nodo.getDer());
    System.out.print(nodo.getDato() + " ");
  }
}
