package TP7.Tests;

import TP7.ColaP;

public class Test2 {

  public static void main(String[] args) {
    ColaP colaPrioridad = new ColaP((a, b) ->
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
