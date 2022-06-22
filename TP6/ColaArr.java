package TP6;

// EJERCICIO 4

public class ColaArr extends Cola {

  private Object[] datos;
  private int first;
  private int last;

  public ColaArr(int size) {
    this.datos = new Object[size + 1];
    clear();
  }

  public boolean push(Object dato) {
    if (isFull()) return false;
    if (this.last != this.datos.length - 1) this.last += 1; else this.last = 0;
    this.datos[this.last] = dato;
    return true;
  }

  public Object pop() {
    if (isEmpty()) return null;
    Object dato = this.datos[this.first];
    if (this.first != this.datos.length - 1) this.first += 1; else this.first =
      0;
    return dato;
  }

  public void clear() {
    this.first = this.last = this.datos.length - 1;
  }

  public boolean isEmpty() {
    return this.first == this.last;
  }

  public boolean isFull() {
    return this.first == this.last + 1;
  }
}
