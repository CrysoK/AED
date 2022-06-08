package TP6;

// EJERCICIO 10

public class QueueV2<T> extends QueueLL<T> {

  private int cardinal;

  // Esta clase sabe en todo momento cuántos elementos contiene.
  public QueueV2() {
    super();
    this.cardinal = 0;
  }

  // Si push es exitoso, se incrementa el cardinal.
  public boolean push(T dato) {
    if (super.push(dato)) {
      this.cardinal++;
      return true;
    }
    return false;
  }

  // Si pop es exitoso, se decrementa el cardinal.
  public T pop() {
    T dato = super.pop();
    if (dato != null) this.cardinal--;
    return dato;
  }

  /**
   * a)
   *
   * @Precondición: La cola existe.
   * @return Cantidad de elementos de la cola
   */
  public int cardinal() {
    return this.cardinal;
  }

  /**
   * b)
   *
   * @Precondición: La cola existe.
   * @Postcondición: Cola en orden inverso.
   */
  public void reverse() {
    Stack<T> pila = new StackLL<T>();
    while (!this.isEmpty()) pila.push(this.pop());
    while (!pila.isEmpty()) this.push(pila.pop());
  }

  /**
   * c)
   *
   * @Precondición: Ambas colas existen.
   * @Postcondición: Cola tiene los elementos de {@code q} añadidos al final
   *                 manteniendo el orden.
   */
  public void concat(QueueV2<T> q) {
    while (!q.isEmpty()) this.push(q.pop());
  }

  public static void main(String[] args) {
    QueueV2<Integer> q1 = new QueueV2<Integer>();
    q1.push(1);
    q1.push(2);
    q1.push(3);
    q1.push(4);
    q1.push(5);
    q1.push(6);
    q1.push(7);
    q1.push(8);
    q1.push(9);
    q1.push(10);
    QueueV2<Integer> q2 = new QueueV2<Integer>();
    q2.push(11);
    q2.push(12);
    q2.push(13);
    q2.push(14);
    q2.push(15);
    q2.push(16);
    q2.push(17);
    q2.push(18);
    q2.push(19);
    q2.push(20);

    // a)
    System.out.println(q1.cardinal());
    // b)
    q1.concat(q2);
    // a)
    System.out.println(q1.cardinal());
    // c)
    q1.reverse();

    while (!q1.isEmpty()) System.out.println(q1.pop());
  }
}
