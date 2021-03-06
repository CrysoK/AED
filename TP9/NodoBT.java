package TP9;

public class NodoBT {

  protected Object dato;
  protected NodoBT izq;
  protected NodoBT der;

  public NodoBT(Object dato, NodoBT izq, NodoBT der) {
    this.dato = dato;
    this.izq = izq;
    this.der = der;
  }

  public NodoBT() {
    this(null, null, null);
  }

  public NodoBT(Object dato) {
    this(dato, null, null);
  }

  public Object getDato() {
    return this.dato;
  }

  public NodoBT getIzq() {
    return this.izq;
  }

  public NodoBT getDer() {
    return this.der;
  }

  public NodoBT getHijo(int direccion) {
    if (direccion < -1 || direccion == 0 || direccion > 1) return null;
    return direccion == -1 ? this.izq : this.der;
  }

  public void setDato(Object dato) {
    this.dato = dato;
  }

  public void setIzq(NodoBT izq) {
    this.izq = izq;
  }

  public void setDer(NodoBT der) {
    this.der = der;
  }

  public boolean setHijo(int lado, NodoBT hijo) {
    if (lado < -1 || lado == 0 || lado > 1) return false;
    if (lado == -1) this.izq = hijo; else this.der = hijo;
    return true;
  }

  public String toString() {
    return this.dato.toString();
  }
}
