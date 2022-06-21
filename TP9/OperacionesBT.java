package TP9;

public interface OperacionesBT {
  public void insertar(Object dato);

  public boolean contiene(Object dato);

  public void eliminar(Object dato);

  public boolean isEmpty();

  public void clear();

  public NodoBT getNodo(Object dato);

  public void printEnOrden();

  public void printPreOrden();

  public void printPostOrden();

  public int cntHojas();

  public int getAltura();
}
