package TP6;

// EJERCICIO 6

public class ColaLL extends Cola {

  protected NodoS first;
  protected NodoS last;

  public boolean push(Object dato) {
    if (isEmpty()) {
      this.first = this.last = new NodoS(dato);
    } else {
      this.last.setNext(new NodoS(dato));
      this.last = this.last.getNext();
    }
    return true;
    // La operación podría fallar?
  }

  public Object pop() {
    if (isEmpty()) return null;
    Object dato = this.first.getDato();
    this.first = this.first.getNext();
    if (this.first == null) this.last = null;
    return dato;
  }

  public void clear() {
    this.first = this.last = null;
  }

  public boolean isEmpty() {
    if (this.first == null || this.last == null) return true; else return false;
  }
}
