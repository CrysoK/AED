package TP9;

import java.util.Comparator;

public abstract class BTree implements OperacionesBT {

  // Para cuando se necesita buscar un dato
  protected Comparator<Object> c;
  protected NodoBT raiz;

  public BTree(Comparator<Object> comparador) {
    c = comparador;
    raiz = null;
  }

  public BTree(Object dato, Comparator<Object> comparador) {
    c = comparador;
    raiz = new NodoBT(dato);
  }

  @Override
  public void insertar(Object dato) {}

  @Override
  public boolean contiene(Object dato) {
    return false;
  }

  @Override
  public void eliminar(Object dato) {}

  @Override
  public boolean isEmpty() {
    return raiz == null;
  }

  @Override
  public void clear() {
    raiz = null;
  }

  @Override
  public NodoBT getNodo(Object dato) {
    return null;
  }

  @Override
  public void printEnOrden() {
    System.out.println("Recorrido \"inorden\" (izq, dato, der)");
    enOrden(raiz);
  }

  private void enOrden(NodoBT nodo) {
    if (nodo == null) return;
    enOrden(nodo.getIzq());
    System.out.print(nodo.getDato() + " ");
    enOrden(nodo.getDer());
  }

  @Override
  public void printPreOrden() {
    System.out.println("Recorrido \"preorden\" (dato, izq, der)");
    preOrden(raiz);
  }

  private void preOrden(NodoBT nodo) {
    if (nodo == null) return;
    System.out.print(nodo.getDato() + " ");
    preOrden(nodo.getIzq());
    preOrden(nodo.getDer());
  }

  @Override
  public void printPostOrden() {
    System.out.println("Recorrido \"postorden\" (izq, der, dato)");
    postOrden(raiz);
  }

  private void postOrden(NodoBT nodo) {
    if (nodo == null) return;
    postOrden(nodo.getIzq());
    postOrden(nodo.getDer());
    System.out.print(nodo.getDato() + " ");
  }

  @Override
  public int cntHojas() {
    return cntHojas(raiz);
  }

  public static int cntHojas(NodoBT nodo) {
    if (nodo == null) return 0;
    if (nodo.getIzq() == null && nodo.getDer() == null) return 1;
    return cntHojas(nodo.getIzq()) + cntHojas(nodo.getDer());
  }

  @Override
  public int getAltura() {
    return getAltura(raiz);
  }

  public static int getAltura(NodoBT nodo) {
    if (nodo == null) return -1;
    int izq = getAltura(nodo.getIzq());
    int der = getAltura(nodo.getDer());
    return izq > der ? izq + 1 : der + 1;
  }

  public int cntHijos() {
    return 0;
  }

  public boolean esCompleto() {
    return false;
  }

  public int getDifAlturas() {
    return getAltura(raiz.getIzq()) - getAltura(raiz.getDer());
  }
}
