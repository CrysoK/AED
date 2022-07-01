package TP7;

import java.util.Comparator;

// import java.util.Comparator;

/*
 * Conjunto Implemente el TAD Conjunto usando interfaz, superclase y subclase abstracta. La
 * implementación debe realizarse con lista enlazada doble. Las operaciones más importantes son:
 * Limpiar()
 * estaVacia()
 * esta()
 * meter()
 * sacar()
 */

public class Set {

  private Comparator<Object> c;
  private OList lista;

  public Set(Comparator<Object> comparador) {
    this.c = comparador;
    this.lista = new OList(c);
  }

  public Set(Comparator<Object> comparador, Object... elementos) {
    this(comparador);
    for (Object e : elementos) {
      this.add(e);
    }
  }

  public void clear() {
    this.lista.clear();
  }

  public Comparator<Object> getComparador() {
    return this.c;
  }

  @Override
  public String toString() {
    List.Iterador it = this.lista.iterador(0);
    if (it == null) {
      return "{ }";
    }
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    while (it.hasNext()) {
      sb.append(it.next());
      if (it.hasNext()) {
        sb.append(", ");
      }
    }
    sb.append("}");
    return sb.toString();
  }

  // Entrada y salida de datos

  public void add(Object elemento) {
    if (!this.esElemento(elemento)) {
      this.lista.insert(elemento);
    }
  }

  public Object pop(Object elemento) {
    return null;
  }

  public void remove(Object elemento) {}

  // Consultas

  public boolean esElemento(Object elemento) {
    return this.lista.search(elemento) >= 0 ? true : false;
  }

  public boolean esSubconjunto(Set b) {
    List.Iterador itB = b.lista.iterador(0);
    // Se busca cada elemento de B en A
    for (int i = 0; i < b.lista.length(); i++) {
      if (!this.esElemento(itB.next())) {
        return false;
      }
    }
    return true;
  }

  public boolean igual(Set b, boolean mismoComparador) {
    return mismoComparador ? igualMismoC(b) : igualDifC(b);
  }

  private boolean igualMismoC(Set b) {
    List.Iterador itA = this.lista.iterador(0);
    List.Iterador itB = b.lista.iterador(0);

    while (itA.hasNext() && itB.hasNext()) {
      Object eA = itA.next();
      Object eB = itB.next();
      if (this.c.compare(eA, eB) != 0) {
        return false;
      }
    }
    if (itA.hasNext() || itB.hasNext()) {
      return false;
    }
    return true;
  }

  private boolean igualDifC(Set b) {
    // Hay que buscar cada elemento de A en B y cada elemento de B en A
    List.Iterador itA = this.lista.iterador(0);
    List.Iterador itB = b.lista.iterador(0);

    while (itA.hasNext()) {
      if (!b.esElemento(itA.next())) {
        return false;
      }
    }
    while (itB.hasNext()) {
      if (!this.esElemento(itB.next())) {
        return false;
      }
    }
    return true;
  }

  public int size() {
    return this.lista.length();
  }

  public boolean isEmpty() {
    return false;
  }

  // Operaciones

  public Set union(Set b, Comparator<Object> comparador) {
    Set c = new Set(comparador);
    List.Iterador itA = this.lista.iterador(0);
    List.Iterador itB = b.lista.iterador(0);

    while (itA.hasNext()) {
      c.add(itA.next());
    }

    while (itB.hasNext()) {
      c.add(itB.next());
    }

    return c;
  }

  public Set interseccion(Set b, Comparator<Object> comparador) {
    // Se asume que los conjuntos están ordenados con criterios diferentes
    Set c = new Set(comparador);
    List.Iterador itA = this.lista.iterador(0);

    while (itA.hasNext()) {
      Object eA = itA.next();
      if (b.esElemento(eA)) {
        c.add(eA);
      }
    }

    return c;
  }

  public Set diferencia(Set b, Comparator<Object> comparador) {
    // Se asume que los conjuntos están ordenados con criterios diferentes
    Set c = new Set(comparador);
    List.Iterador itA = this.lista.iterador(0);

    while (itA.hasNext()) {
      Object eA = itA.next();
      if (!b.esElemento(eA)) {
        c.add(eA);
      }
    }

    return c;
  }
}
