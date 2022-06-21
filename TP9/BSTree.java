package TP9;

import java.util.Comparator;

public class BSTree extends BTree {

  public BSTree(Comparator<Object> comparador) {
    super(comparador);
  }

  public BSTree(Object dato, Comparator<Object> comparador) {
    super(dato, comparador);
  }

  @Override
  public void insertar(Object dato) {
    NodoBT nuevo = new NodoBT(dato);
    if (this.isEmpty()) {
      this.raiz = nuevo;
      return;
    }
    NodoBT actual = this.raiz;
    NodoBT padre = null;
    boolean menor = false;
    while (actual != null) {
      padre = actual;
      if (this.c.compare(dato, actual.getDato()) < 0) {
        actual = actual.getIzq();
        menor = true;
      } else {
        actual = actual.getDer();
        menor = false;
      }
    }
    if (menor) {
      padre.setIzq(nuevo);
    } else {
      padre.setDer(nuevo);
    }
  }
}
