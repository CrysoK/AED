package TP7;

import TP6.NodoS;
import TP6.QueueLL;
import java.util.Comparator;

/*
 * Implementar el TAD Cola de Prioridad a partir del código propuesto por la
 * Cátedra (cola implementada con lista enlazada simple del TP6). Reutilice
 * código, use interfaz y sea genérico en la propuesta cumpliendo con la
 * funcionalidad del contenedor.
 */

public class PQueue extends QueueLL {

  private Comparator<Object> comparador;

  public PQueue(Comparator<Object> c) {
    super();
    comparador = c;
  }

  // Se inserta ordenando de mayor a menor.
  @Override
  public boolean push(Object dato) {
    if (isEmpty()) {
      first = last = new NodoS(dato);
      // Si es mayor al primero
    } else if (comparador.compare(dato, first.getDato()) > 0) {
      first = new NodoS(dato, first);
      // Si es menor al último
    } else if (comparador.compare(dato, last.getDato()) < 0) {
      last.setNext(new NodoS(dato));
      last = last.getNext();
      // Si está en el medio, se posiciona a la izquierda si es mayor y a la
      // derecha si es igual o menor.
    } else {
      NodoS current = first;
      NodoS prev = null;
      while (
        current != null && comparador.compare(dato, current.getDato()) <= 0
      ) {
        prev = current;
        current = current.getNext();
      }
      prev.setNext(new NodoS(dato, current));
    }
    return true;
  }

  public static void main(String[] args) {
    PQueue colaPrioridad = new PQueue((a, b) ->
      ((Proceso) a).getPrioridad() - ((Proceso) b).getPrioridad()
    );

    colaPrioridad.push(new Proceso("p1", 1));
    colaPrioridad.push(new Proceso("p2", 2));
    colaPrioridad.push(new Proceso("p3", 1));
    colaPrioridad.push(new Proceso("p4", 2));
    colaPrioridad.push(new Proceso("p5", 3));

    System.out.println(colaPrioridad.pop());
    System.out.println(colaPrioridad.pop());
    System.out.println(colaPrioridad.pop());
    System.out.println(colaPrioridad.pop());
    System.out.println(colaPrioridad.pop());
  }

  private static class Proceso {

    private int prioridad;
    private String nombre;

    public Proceso(String nombre, int prioridad) {
      this.prioridad = prioridad;
      this.nombre = nombre;
    }

    public String toString() {
      StringBuilder s = new StringBuilder("");
      s.append("Proceso \"").append(nombre);
      s.append("\" con prioridad ").append(prioridad);
      return s.toString();
    }

    public int getPrioridad() {
      return prioridad;
    }
  }
}
