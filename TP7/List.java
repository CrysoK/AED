package TP7;

// import java.util.Comparator;

public abstract class List implements CLineal2 {

  protected NodoD first, last;
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

  public Object get(int pos) {
    if (this.isEmpty()) {
      System.out.println("Lista vacía");
      return null;
    }
    if (pos < 0 || pos > this.lastPos) {
      System.out.println("Posición inválida");
      return null;
    }
    NodoD current = getNodo(pos);
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
      if (this.first == this.last) this.clear(); else {
        this.first = this.first.getNext();
        this.first.setPrev(null);
      }
    } else if (pos == this.lastPos) {
      // Eliminar último elemento
      this.last = this.last.getPrev();
      this.last.setNext(null);
    } else {
      // Eliminar elemento intermedio
      NodoD current = getNodo(pos);
      current.getPrev().setNext(current.getNext());
      current.getNext().setPrev(current.getPrev());
    }
    this.lastPos--;
    return true;
  }

  public int[] toIntArray() {
    NodoD current = this.first;
    int[] array = new int[this.length()];
    for (int i = 0; i < this.length(); i++) {
      array[i] = (int) current.getDato();
      current = current.getNext();
    }
    return array;
  }

  public char[] toCharArray() {
    NodoD current = this.first;
    char[] array = new char[this.length()];
    for (int i = 0; i < this.length(); i++) {
      array[i] = (char) current.getDato();
      current = current.getNext();
    }
    return array;
  }

  protected NodoD getNodo(int pos) {
    return getNodo(pos, this.lastPos, this.first, this.last);
  }

  /**
   * Devuelve el nodo de la posición pos aprovechando la capacidad de una lista
   * doblemente enlazada de avanzar y retroceder.
   *
   * @param pos posición del nodo a devolver
   * @param endPos última posición de la lista
   * @param start nodo inicial de la lista
   * @param end nodo final de la lista
   * @return nodo de la posición pos
   */
  protected NodoD getNodo(int pos, int endPos, NodoD start, NodoD end) {
    int midP = endPos / 2;
    int step = pos <= midP ? 1 : -1;
    int initP = pos <= midP ? 0 : endPos;
    NodoD current = pos <= midP ? start : end;
    for (int i = initP; i != pos; i += step) {
      current = current.getByDir(step);
    }
    return current;
  }

  /**
   * El iterador permite recorrer la lista desde fuera de la clase de forma más
   * eficiente.
   */
  public Iterador iterador(int inicio) {
    if (this.length() == 0) return null;
    return new Iterador(this.getNodo(inicio));
  }

  public class Iterador {

    private NodoD actual;

    public Iterador(NodoD inicio) {
      this.actual = inicio;
    }

    public boolean hasNext() {
      return this.actual != null;
    }

    public Object next() {
      Object dato = this.actual.getDato();
      this.actual = this.actual.getNext();
      return dato;
    }

    public boolean hasPrevious() {
      return this.actual.getPrev() != null;
    }

    public Object previous() {
      Object dato = this.actual.getDato();
      this.actual = this.actual.getPrev();
      return dato;
    }
  }
}
