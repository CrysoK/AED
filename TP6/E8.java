package TP6;

public class E8 {

  /*
   * Dada una pila de enteros y dos valores enteros, reemplazar el primero por el segundo (si se
   * encuentra en la pila)
   */

  /**
   * @Precondición 'pila' debe existir y, si contiene elementos, estos deben ser enteros.
   * @Postcondición: 'pila' ahora tiene a 'b' en el lugar de 'a'.
   * @param pila Pila de enteros
   * @param a Elemento a reemplazar
   * @param b Elemento por el que reemplazar
   */
  public static void reemplazar(Stack pila, int a, int b) {
    Stack aux = new StackLL();
    int temp;
    // Búsqueda de a
    do {
      temp = (int) pila.pop();
      if (temp == a) pila.push(b); else aux.push(temp);
    } while (!pila.isEmpty() && temp != a);
    // Una vez reemplazado, se restaura el resto de la pila original
    while (!aux.isEmpty()) {
      pila.push((int) aux.pop());
    }
  }

  public static void main(String[] args) {
    Stack pila = new StackArr(10);

    pila.push(1);
    pila.push(2);
    pila.push(3);
    pila.push(4);
    pila.push(5);
    pila.push(6);
    pila.push(7);
    pila.push(8);
    pila.push(9);
    pila.push(10);

    reemplazar(pila, 4, -1);

    while (!pila.isEmpty()) System.out.println(pila.pop());
  }
}
