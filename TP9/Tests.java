package TP9;

public class Tests {

  public static void main(String[] args) {
    BSTree arbol = new BSTree((a, b) -> ((int) a) - ((int) b));

    arbol.insertar(7);
    arbol.insertar(5);
    arbol.insertar(20);
    arbol.insertar(3);
    arbol.insertar(6);
    arbol.insertar(14);
    arbol.insertar(35);
    arbol.insertar(1);
    arbol.insertar(3);
    arbol.insertar(10);
    arbol.insertar(27);
    arbol.insertar(7);

    arbol.printEnOrden();
    System.out.println();
    arbol.printPreOrden();
    System.out.println();
    arbol.printPostOrden();
    System.out.println();
  }
}
