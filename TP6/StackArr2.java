package TP6;

// EJERCICIO 7

public class StackArr2 extends StackArr {

  // Obtener tope sin eliminarlo
  public Object tope() {
    if (isEmpty()) return null;
    Object dato = this.pop();
    this.push(dato);
    return dato;
  }

  public StackArr2(int size) {
    super(size);
  }
}
