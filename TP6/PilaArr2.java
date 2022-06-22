package TP6;

// EJERCICIO 7

public class PilaArr2 extends PilaArr {

  // Obtener tope sin eliminarlo
  public Object tope() {
    if (isEmpty()) return null;
    Object dato = this.pop();
    this.push(dato);
    return dato;
  }

  public PilaArr2(int size) {
    super(size);
  }
}
