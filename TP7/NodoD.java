package TP7;

public class NodoD<Tipo> {

  private Tipo dato;
  private NodoD<Tipo> prev, next;

  public NodoD(Tipo dato) {
    this(dato, null, null);
  }

  public NodoD(Tipo dato, NodoD<Tipo> next) {
    this(dato, null, next);
  }

  public NodoD(Tipo dato, NodoD<Tipo> prev, NodoD<Tipo> next) {
    this.dato = dato;
    this.prev = prev;
    this.next = next;
  }

  public Tipo getDato() {
    return dato;
  }

  public NodoD<Tipo> getPrev() {
    return prev;
  }

  public NodoD<Tipo> getNext() {
    return next;
  }

  public NodoD<Tipo> getByDir(int dir) {
    return dir == 1 ? next : prev;
  }

  public void setDato(Tipo dato) {
    this.dato = dato;
  }

  public void setPrev(NodoD<Tipo> prev) {
    this.prev = prev;
  }

  public void setNext(NodoD<Tipo> next) {
    this.next = next;
  }

  public String toString() {
    return this.dato.toString();
  }
}
