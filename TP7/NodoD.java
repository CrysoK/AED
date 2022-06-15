package TP7;

public class NodoD {

  private Object dato;
  private NodoD prev, next;

  public NodoD(Object dato) {
    this(dato, null, null);
  }

  public NodoD(Object dato, NodoD next) {
    this(dato, null, next);
  }

  public NodoD(Object dato, NodoD prev, NodoD next) {
    this.dato = dato;
    this.prev = prev;
    this.next = next;
  }

  public Object getDato() {
    return dato;
  }

  public NodoD getPrev() {
    return prev;
  }

  public NodoD getNext() {
    return next;
  }

  public NodoD getByDir(int dir) {
    return dir == 1 ? next : prev;
  }

  public void setDato(Object dato) {
    this.dato = dato;
  }

  public void setPrev(NodoD prev) {
    this.prev = prev;
  }

  public void setNext(NodoD next) {
    this.next = next;
  }

  public String toString() {
    return this.dato.toString();
  }
}
