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

  @Override
  public void eliminar(Object dato) {
    raiz = elimRec(raiz, dato);
    // elimIter(dato);
  }

  // Eliminación sin recursión, basado en lo dado por la cátedra
  private void elimIter(Object dato) {
    if (this.isEmpty()) {
      return;
    }
    NodoBT actual = this.raiz;
    NodoBT padre = null;
    int lado = 0;

    while (actual != null) {
      int comp = c.compare(actual.getDato(), dato);
      if (comp == 0) {
        break;
      }
      padre = actual;
      if (comp > 0) {
        actual = actual.getIzq();
        lado = -1;
      } else {
        actual = actual.getDer();
        lado = 1;
      }
    }
    // El dato no existe
    if (actual == null) {
      return;
    }
    if (actual.getIzq() == null && actual.getDer() == null) {
      // No tiene hijos

      if (padre == null) {
        clear();
      } else {
        padre.setHijo(lado, null);
      }
    } else if (actual.getIzq() != null && actual.getDer() != null) {
      // Tiene dos hijos

      // Buscar el sucesor del nodo actual (mayor del subárbol menor)
      padre = actual;
      NodoBT sucesor = actual.getIzq();
      while (sucesor.getDer() != null) {
        padre = sucesor;
        sucesor = sucesor.getDer();
      }

      actual.setDato(sucesor.getDato());
      if (padre == actual) {
        padre.setIzq(sucesor.getIzq());
      } else {
        padre.setDer(sucesor.getIzq());
      }
    } else {
      // Solo tiene un hijo

      int ladoHijo = actual.getIzq() != null ? -1 : 1;
      if (padre == null) {
        raiz = actual.getHijo(ladoHijo);
      } else {
        padre.setHijo(lado, actual.getHijo(ladoHijo));
      }
    }
  }

  // Eliminación recursiva
  private NodoBT elimRec(NodoBT actual, Object dato) {
    if (actual == null) {
      return null;
    }
    int comp = c.compare(dato, actual.getDato());
    if (comp < 0) {
      actual.setIzq(elimRec(actual.getIzq(), dato));
    } else if (comp > 0) {
      actual.setDer(elimRec(actual.getDer(), dato));
    } else {
      if (actual.getIzq() == null) {
        return actual.getDer();
      } else if (actual.getDer() == null) {
        return actual.getIzq();
      }
      // Buscar el sucesor del nodo actual (mayor del subárbol menor)
      actual.setDato(maximo(actual.getIzq()));
      actual.setIzq(elimRec(actual.getIzq(), actual.getDato()));
      // actual.setDato(minimo(actual.getDer()));
      // actual.setDer(elimRec(actual.getDer(), actual.getDato()));
    }
    return actual;
  }

  private Object maximo(NodoBT actual) {
    while (actual.getDer() != null) {
      actual = actual.getDer();
    }
    return actual.getDato();
  }

  private Object minimo(NodoBT actual) {
    while (actual.getIzq() != null) {
      actual = actual.getIzq();
    }
    return actual.getDato();
  }
}
