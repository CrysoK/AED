package TP6;

// EJERCICIO 3

// Nodo simplemente enlazado
public class NodoS {

  private Object dato;
  private NodoS next;

  public NodoS(Object dato) {
    this(dato, null);
  }

  public NodoS(Object dato, NodoS next) {
    this.dato = dato;
    this.next = next;
  }

  public Object getDato() {
    return this.dato;
  }

  public NodoS getNext() {
    return this.next;
  }

  public void setDato(Object dato) {
    this.dato = dato;
  }

  public void setNext(NodoS next) {
    this.next = next;
  }

  public String toString() {
    return this.dato.toString();
  }

  /* @formatter:off
   *
   * a)
   * - A.dato = 30
   * - B.next.dato = 90
   * - Lista.next.next.dato = 45
   * b)
   * - ¿Lista.next==A? V
   * - ¿A.next.dato==60? F
   * - ¿B.next==null? F
   * c)
   * - Lista = A.next;
   * - B = B.next;
   * - Lista = null;
   *
   * @formatter:on
   */
  public static void main(String[] args) {
    NodoS lista = new NodoS(25);
    NodoS A = new NodoS(30);
    NodoS B = new NodoS(80);
    lista.setNext(A);
    A.setNext(new NodoS(45, new NodoS(60, new NodoS(65, B))));
    B.setNext(new NodoS(90));
    NodoS aux = lista;
    System.out.println("Lista");
    while (aux != null) {
      System.out.println(aux);
      aux = aux.getNext();
    }
    System.out.println();
    System.out.println("A.nodoInfo = " + A.getDato());
    System.out.println("B.nextNodo.nodoInfo = " + B.getNext().getDato());
    System.out.println(
      "Lista.nextNodo.nextNodo.nodoInfo = " +
      lista.getNext().getNext().getDato()
    );
  }
}
