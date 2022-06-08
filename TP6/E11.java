package TP6;

public class E11 {

  /*
   * Escribir un método que para una pila de enteros, genere una cola con los
   * elementos capicúa de la pila y mantenga en la pila los elementos no capicúa
   * en el mismo orden.
   */

  public static Queue<Integer> separarCapicuas(Stack<Integer> pila) {
    // Pila para mantener el orden original de los capicúas
    Stack<Integer> auxCapicuas = new StackLL<Integer>();
    Queue<Integer> capicuas = new QueueLL<Integer>();
    Queue<Integer> noCapicuas = new QueueLL<Integer>();
    // Separa los números
    while (!pila.isEmpty()) {
      int temp = (int) pila.pop();
      if (esCapicua(temp)) {
        auxCapicuas.push(temp);
      } else {
        noCapicuas.push(temp);
      }
    }
    // Restaura el orden original
    while (!noCapicuas.isEmpty()) {
      pila.push(noCapicuas.pop());
    }
    while (!auxCapicuas.isEmpty()) {
      capicuas.push(auxCapicuas.pop());
    }
    return capicuas;
  }

  public static boolean esCapicua(int n) {
    int temp = n;
    int alReves = 0;
    while (temp != 0) {
      alReves = alReves * 10 + temp % 10;
      temp /= 10;
    }
    return n == alReves;
  }

  public static void main(String[] args) {
    Stack<Integer> pila = new StackArr<Integer>(10);

    pila.push(101);
    pila.push(43);
    pila.push(3);
    pila.push(545);
    pila.push(665);
    pila.push(557);
    pila.push(243);
    pila.push(444);
    pila.push(923);
    pila.push(404);

    Stack<Integer> pila2 = new StackArr<Integer>(10);

    System.out.println("Pila original:");
    while (!pila.isEmpty()) {
      Integer temp = pila.pop();
      System.out.println(temp);
      pila2.push(temp);
    }

    Queue<Integer> capicuas = separarCapicuas(pila2);

    System.out.println("Capicuas (cola):");
    while (!capicuas.isEmpty()) {
      System.out.println(capicuas.pop());
    }
    System.out.println("No capicuas (pila):");
    while (!pila2.isEmpty()) {
      System.out.println(pila2.pop());
    }
  }
}
