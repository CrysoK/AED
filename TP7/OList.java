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
      // Insertar en lista vacía
      this.first = this.last = new NodoD(dato);
    } else if (this.c.compare(dato, this.first.getDato()) < 0) {
      // Insertar al principio
      this.first = new NodoD(dato, this.first);
      this.first.getNext().setPrev(this.first);
    } else if (this.c.compare(dato, this.last.getDato()) >= 0) {
      // Insertar al final
      this.last = new NodoD(dato, this.last, null);
      this.last.getPrev().setNext(this.last);
    } else {
      // Insertar en medio
      Object[] aux = searchNodePos(dato, this.c);
      NodoD nodo = (NodoD) aux[0];
      NodoD nuevo = new NodoD(dato, nodo, nodo.getNext());
      nuevo.getNext().setPrev(nuevo);
      nodo.setNext(nuevo);
    }
    this.lastPos++;
    return true;
  }

  public int search(Object dato) {
    return search(dato, this.c);
  }

  public int search(Object dato, Comparator<Object> comparador) {
    return (int) searchNodePos(dato, comparador)[1];
  }

  private Object[] searchNodePos(Object dato, Comparator<Object> comp) {
    if (this.isEmpty()) {
      return new Object[] { null, -1 };
    }
    NodoD nodo = this.first;
    int pos = 0;
    while (nodo != null) {
      if (comp.compare(dato, nodo.getDato()) == 0) {
        return new Object[] { nodo, pos };
      }
      nodo = nodo.getNext();
      pos += 1;
    }
    return new Object[] { null, -1 };
  }

  // Solo para probar rendimiento, que resultó peor que la búsqueda lineal
  public int searchBin(Object dato) {
    if (this.isEmpty()) {
      return -1;
      // return new Object[] { null, -1 };
    }
    // Búsqueda binaria
    int izqPos = 0;
    int derPos = this.lastPos;
    NodoD izq = this.first;
    NodoD der = this.last;

    while (izqPos != derPos) {
      int midPos = (int) Math.ceil((float) (izqPos + derPos) / 2.0);
      NodoD mid = getNodo(midPos - izqPos, derPos - izqPos, izq, der);

      // System.out.println(
      //   "izqPos: " + izqPos + " midPos: " + midPos + " derPos: " + derPos
      // );
      // System.out.println("izq: " + izq + " mid: " + mid + " der: " + der);

      if (c.compare(dato, mid.getDato()) < 0) {
        derPos = midPos - 1;
        der = mid.getPrev();
      } else {
        izqPos = midPos;
        izq = mid;
      }
    }
    if (c.compare(dato, izq.getDato()) != 0) {
      izqPos = -1;
    }
    return izqPos;
    // return new Object[] { izq, izqPos };
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
}
