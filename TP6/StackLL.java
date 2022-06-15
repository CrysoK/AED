package TP6;

// EJERCICIO 5

public class StackLL extends Stack {

  private NodoS head;

  public StackLL() {
    this.head = null;
  }

  public boolean push(Object dato) {
    this.head = new NodoS(dato, this.head);
    return true;
    // La operación podría fallar?
  }

  public Object pop() {
    if (isEmpty()) return null;
    Object dato = this.head.getDato();
    this.head = this.head.getNext();
    return dato;
  }

  public void clear() {
    this.head = null;
  }

  public boolean isEmpty() {
    return this.head == null;
  }
}
