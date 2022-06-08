package TP6;

// EJERCICIO 6

public class QueueLL<T> extends Queue<T> {

  protected NodoS<T> first;
  protected NodoS<T> last;

  public boolean push(T dato) {
    if (isEmpty()) {
      this.first = this.last = new NodoS<T>(dato);
    } else {
      this.last.setNext(new NodoS<T>(dato));
      this.last = this.last.getNext();
    }
    return true;
    // La operación podría fallar?
  }

  public T pop() {
    if (isEmpty()) return null;
    T dato = this.first.getDato();
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
