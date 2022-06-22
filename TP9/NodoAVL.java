package TP9;

public class NodoAVL {

  private Object dato;
  private NodoAVL izq;
  private NodoAVL der;
  private int altura;

  public NodoAVL(Object dato) {
    this(dato, null, null);
  }

  public NodoAVL(Object dato, NodoAVL izq, NodoAVL der) {
    this.dato = dato;
    this.izq = izq;
    this.der = der;
    this.altura = 0;
  }

  /**
   * @return El dato del nodo.
   */
  public Object getDato() {
    return dato;
  }

  /**
   * @param dato El nuevo dato del nodo.
   */
  public void setDato(Object dato) {
    this.dato = dato;
  }

  /**
   * @return El hijo izquierdo del nodo.
   */
  public NodoAVL getIzq() {
    return izq;
  }

  /**
   * @param nodo El nuevo hijo izquierdo del nodo.
   */
  public void setIzq(NodoAVL nodo) {
    this.izq = nodo;
  }

  /**
   * @return El hijo derecho del nodo.
   */
  public NodoAVL getDer() {
    return der;
  }

  /**
   * @param nodo El nuevo hijo derecho del nodo.
   */
  public void setDer(NodoAVL nodo) {
    this.der = nodo;
  }

  /**
   *
   * @param nodo El nuevo hijo.
   * @param lado El hijo a modificar. -1 para izquierdo, 1 para derecho.
   * @return true si se pudo modificar, false en caso contrario.
   */
  public boolean setHijo(NodoAVL nodo, int lado) {
    if (lado < -1 || lado == 0 || lado > 1) return false;
    if (lado == -1) this.izq = nodo; else this.der = nodo;
    return true;
  }

  /**
   * @param lado El hijo a obtener. -1 para izquierdo, 1 para derecho.
   * @return El hijo del lado indicado.
   */
  public NodoAVL getHijo(int lado) {
    if (lado < -1 || lado == 0 || lado > 1) return null;
    return lado == -1 ? this.izq : this.der;
  }

  /**
   * @return La altura del nodo.
   */
  public int getAltura() {
    return altura;
  }

  /**
   * @param altura La nueva altura del nodo.
   */
  public void setAltura(int altura) {
    this.altura = altura;
  }
}
