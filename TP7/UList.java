package TP7;

import java.util.Comparator;

public class UList<T> extends List<T> implements CLineal3<T> {

  public int search(T dato, Comparator<T> c) {
    int i = 0;
    NodoD<T> current = this.first;
    while (i <= this.lastPos && c.compare(dato, current.getDato()) != 0) {
      current = current.getNext();
      i++;
    }
    return current == null ? -1 : i;
  }

  public boolean insert(T dato, int pos) {
    if (pos < 0 || pos > this.lastPos + 1) {
      System.out.println("Posición inválida");
      return false;
    }
    NodoD<T> newNode = new NodoD<T>(dato);
    if (pos == 0) {
      newNode.setNext(this.first);
      this.first = newNode;
      if (this.lastPos == -1) this.last = newNode;
    } else if (pos == this.lastPos + 1) {
      newNode.setPrev(this.last);
      this.last.setNext(newNode);
      this.last = newNode;
    } else {
      NodoD<T> current = getNodo(pos);
      newNode.setNext(current.getNext());
      newNode.setPrev(current);
      current.getNext().setPrev(newNode);
      current.setNext(newNode);
    }
    this.lastPos++;
    return true;
  }

  public boolean insert(T dato) {
    return insert(dato, this.lastPos + 1);
  }

  public boolean replace(T dato, int pos) {
    if (this.isEmpty()) {
      System.out.println("Lista vacía");
      return false;
    }
    if (pos < 0 || pos > this.lastPos) {
      System.out.println("Posición inválida");
      return false;
    }
    if (pos == 0) {
      this.first.setDato(dato);
    } else if (pos == this.lastPos) {
      this.last.setDato(dato);
    } else {
      NodoD<T> current = getNodo(pos);
      current.setDato(dato);
    }
    return true;
  }

  // PRUEBAS ///////////////////////////////////////////////////////////////////

  public static void main(String[] args) {
    UList<Integer> l = new UList<Integer>();
    l.insert(1);
    l.insert(2);
    l.insert(3);

    System.out.println(l.get(0));
    System.out.println(l.get(1));
    System.out.println(l.get(2));
  }
}
