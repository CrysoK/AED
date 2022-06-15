package TP7;

import java.util.Comparator;

// EJERCICIO 1

/*
 * Implementar el TAD Lista Ordenada a partir del código propuesto por la
 * Cátedra (lista con implementado con lista enlazada doble). Reutilice código,
 * use interfaz y sea genérico en la propuesta cumpliendo con la funcionalidad
 * del contenedor.
 */

public class OList extends List implements CLineal4 {

  private Comparator<Object> c;

  public OList(Comparator<Object> c) {
    super();
    this.c = c;
  }

  public boolean insert(Object dato) {
    if (this.isEmpty()) {
      this.first = this.last = new NodoD(dato);
    } else if (this.c.compare(dato, this.first.getDato()) < 0) {
      this.first = new NodoD(dato, this.first);
      this.first.getNext().setPrev(this.first);
    } else if (this.c.compare(dato, this.last.getDato()) >= 0) {
      this.last = new NodoD(dato, this.last, null);
      this.last.getPrev().setNext(this.last);
    } else {
      NodoD current = this.first;
      while (this.c.compare(dato, current.getDato()) >= 0) {
        current = current.getNext();
      }
      current.getPrev().setNext(new NodoD(dato, current, current.getNext()));
      current.getNext().setPrev(current.getPrev().getNext());
    }
    this.lastPos++;
    return true;
  }

  public int search(Object dato) {
    return search(dato, this.c);
  }

  public int search(Object dato, Comparator<Object> c) {
    // Búsqueda binaria
    int startPos = 0;
    int endPos = this.lastPos;
    int midPos = endPos / 2;
    NodoD start = this.first;
    NodoD end = this.last;
    NodoD mid = getNodo(midPos);

    while (startPos != endPos) {
      if (c.compare(dato, mid.getDato()) == 0) {
        return midPos;
      }
      if (c.compare(dato, mid.getDato()) < 0) {
        endPos = midPos;
        end = mid;
      } else {
        startPos = midPos;
        start = mid;
      }
      midPos = (startPos + endPos) / 2;
      mid = getNodo(midPos, endPos, start, end);
    }
    return -1;
  }

  public String toString() {
    StringBuilder s = new StringBuilder("");
    NodoD current = this.first;
    while (current.getNext() != null) {
      s.append(current.getDato()).append("\n");
      current = current.getNext();
    }
    s.append(current.getDato());
    return s.toString();
  }

  public static void main(String[] args) {
    OList lista2 = new OList((a, b) ->
      ((Integer) a).intValue() - ((Integer) b).intValue()
    );

    lista2.insert(100);
    lista2.insert(90);
    lista2.insert(80);
    lista2.insert(70);

    System.out.println(lista2);
    System.out.println();

    lista2.insert(55);
    lista2.insert(77);
    System.out.println(lista2);

    System.out.println();
    System.out.println("90 en que posicion esta? " + lista2.search(90));
    System.out.println("-90 en que posicion esta? " + lista2.search(-90));
  }
}
