package TP9;

public class Tests {

  public static void main(String[] args) {
    AVLTree arbol = new AVLTree((a, b) -> ((int) a) - ((int) b));

    arbol.insertar(100);
    arbol.insertar(30);
    arbol.insertar(20);
    arbol.insertar(10);
    arbol.insertar(5);
    arbol.insertar(24);
    arbol.insertar(70);
    arbol.insertar(80);
    arbol.insertar(90);
    arbol.insertar(110);
    arbol.insertar(75);

    arbol.printEnOrden();
    arbol.printPreOrden();
  }
}
