package TP9;

public class NodoBT {

  private Object dato;
  private NodoBT izq;
  private NodoBT der;

  public NodoBT(Object dato, NodoBT izq, NodoBT der) {
    this.izq = izq;
    this.der = der;
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

  public void setDato(Object dato) {
    this.dato = dato;
  }

  public void setIzq(NodoBT izq) {
    this.izq = izq;
  }

  public void setDer(NodoBT der) {
    this.der = der;
  }

  public String toString() {
    return this.dato.toString();
  }
}
