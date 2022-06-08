package TP6;

// EJERCICIO 5

public class StackLL<T> extends Stack<T> {

  private NodoS<T> head;

  public StackLL() {
    this.head = null;
  }

  public boolean push(T dato) {
    this.head = new NodoS<T>(dato, this.head);
    return true;
    // La operación podría fallar?
  }

  public T pop() {
    if (isEmpty()) return null;
    T dato = this.head.getDato();
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
