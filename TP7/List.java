package TP7;

import java.util.Iterator;

// import java.util.Comparator;

public abstract class List<T> implements CLineal2<T> {

  protected NodoD<T> first, last;
  protected int lastPos;

  public List() {
    this.clear();
  }

  public void clear() {
    this.first = this.last = null;
    this.lastPos = -1;
  }

  public boolean isEmpty() {
    return this.first == null;
  }

  public int length() {
    return this.lastPos + 1;
  }

  public T get(int pos) {
    if (this.isEmpty()) {
      System.out.println("Lista vacía");
      return null;
    }
    if (pos < 0 || pos > this.lastPos) {
      System.out.println("Posición inválida");
      return null;
    }
    NodoD<T> current = getNodo(pos);
    return current.getDato();
  }

  public boolean del(int pos) {
    if (this.isEmpty()) {
      System.out.println("Lista vacía");
      return false;
    }
    if (pos < 0 || pos > this.lastPos) {
      System.out.println("Posición inválida");
      return false;
    }
    if (pos == 0) {
      // Eliminar primer elemento
      if (this.first == this.last)
        this.clear();
      else {
        this.first = this.first.getNext();
        this.first.setPrev(null);
      }
    } else if (pos == this.lastPos) {
      // Eliminar último elemento
      this.last = this.last.getPrev();
      this.last.setNext(null);
    } else {
      // Eliminar elemento intermedio
      NodoD<T> current = getNodo(pos);
      current.getPrev().setNext(current.getNext());
      current.getNext().setPrev(current.getPrev());
    }
    this.lastPos--;
    return true;
  }

  protected NodoD<T> getNodo(int pos) {
    return getNodo(pos, this.lastPos, this.first, this.last);
  }

  /**
   * Devuelve el nodo de la posición pos aprovechando la capacidad de una lista
   * doblemente enlazada de avanzar y retroceder.
   *
   * @param pos posición del nodo a devolver
   * @param max última posición de la lista
   * @param start nodo inicial de la lista
   * @param end nodo final de la lista
   * @return nodo de la posición pos
   */
  protected NodoD<T> getNodo(int pos, int max, NodoD<T> start, NodoD<T> end) {
    int midP = max / 2;
    int step = pos <= midP ? 1 : -1;
    int startP = pos <= midP ? 0 : this.lastPos;
    NodoD<T> current = pos <= midP ? start : end;
    for (int i = startP; i != pos; i += step) {
      current = current.getByDir(step);
    }
    return current;
  }

  // public abstract int search(T dato, Comparator<T> c);

  /**
   * El iterador permite recorrer la lista desde fuera de la clase de forma más
   * eficiente.
   */
  public Iterador iterador(int inicio) {
    if (this.length() == 0)
      return null;
    return new Iterador(this.getNodo(inicio));
  }

  public class Iterador implements Iterator<T> {

    private NodoD<T> actual;
    private int pos;

    public Iterador(NodoD<T> inicio) {
      this.actual = inicio;
    }

    public boolean hasNext() {
      return this.actual.getNext() != null;
    }

    public T next() {
      T dato = this.actual.getDato();
      this.actual = this.actual.getNext();
      this.pos++;
      return dato;
    }

    public boolean hasPrevious() {
      return this.actual.getPrev() != null;
    }

    public T previous() {
      T dato = this.actual.getDato();
      this.actual = this.actual.getPrev();
      this.pos--;
      return dato;
    }
  }
}
