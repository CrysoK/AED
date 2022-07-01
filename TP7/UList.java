package TP7;

import java.util.Comparator;

public class UList extends List implements CLineal3 {

  public int search(Object dato, Comparator<Object> c) {
    int i = 0;
    NodoD current = this.first;
    while (i <= this.lastPos && c.compare(dato, current.getDato()) != 0) {
      current = current.getNext();
      i++;
    }
    return current == null ? -1 : i;
  }

  public boolean insert(Object dato, int pos) {
    if (pos < 0 || pos > this.lastPos + 1) {
      System.out.println("Posición inválida");
      return false;
    }
    NodoD newNode = new NodoD(dato);
    if (pos == 0) {
      newNode.setNext(this.first);
      this.first = newNode;
      if (this.lastPos == -1) this.last = newNode;
    } else if (pos == this.lastPos + 1) {
      newNode.setPrev(this.last);
      this.last.setNext(newNode);
      this.last = newNode;
    } else {
      NodoD current = getNodo(pos);
      newNode.setNext(current.getNext());
      newNode.setPrev(current);
      current.getNext().setPrev(newNode);
      current.setNext(newNode);
    }
    this.lastPos++;
    return true;
  }

  public boolean insert(Object dato) {
    return insert(dato, this.lastPos + 1);
  }

  public boolean replace(Object dato, int pos) {
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
      NodoD current = getNodo(pos);
      current.setDato(dato);
    }
    return true;
  }
}
