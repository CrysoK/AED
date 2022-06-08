package TP6;

// EJERCICIO 3

// Nodo simplemente enlazado
public class NodoS<T> {

  private T dato;
  private NodoS<T> next;

  public NodoS(T dato) {
    this(dato, null);
  }

  public NodoS(T dato, NodoS<T> next) {
    this.dato = dato;
    this.next = next;
  }

  public T getDato() {
    return this.dato;
  }

  public NodoS<T> getNext() {
    return this.next;
  }

  public void setDato(T dato) {
    this.dato = dato;
  }

  public void setNext(NodoS<T> next) {
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
    NodoS<Integer> lista = new NodoS<Integer>(25);
    NodoS<Integer> A = new NodoS<Integer>(30);
    NodoS<Integer> B = new NodoS<Integer>(80);
    lista.setNext(A);
    A.setNext(
      new NodoS<Integer>(45, new NodoS<Integer>(60, new NodoS<Integer>(65, B)))
    );
    B.setNext(new NodoS<Integer>(90));
    NodoS<Integer> aux = lista;
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
