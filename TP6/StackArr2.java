package TP6;

// EJERCICIO 7

public class StackArr2<T> extends StackArr<T> {

  // Obtener tope sin eliminarlo
  public T tope() {
    if (isEmpty()) return null;
    T dato = this.pop();
    this.push(dato);
    return dato;
  }

  public StackArr2(int size) {
    super(size);
  }
}
