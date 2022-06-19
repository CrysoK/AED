package Utils;

public class Matriz {

  protected Object[][] matriz;
  protected int filas;
  protected int columnas;

  public Matriz(int filas, int columnas) {
    this.filas = filas;
    this.columnas = columnas;
    matriz = new Object[filas][columnas];
  }

  protected Matriz() {}

  public int getFilas() {
    return filas;
  }

  public int getColumnas() {
    return columnas;
  }

  public void set(Object valor, int fila, int columna) {
    matriz[fila][columna] = valor;
  }

  public Object get(int fila, int columna) {
    return matriz[fila][columna];
  }

  public String toString() {
    if (filas == 0 || columnas == 0) {
      return "Matriz vacia\n";
    }
    int[] anchos = anchoCols();
    StringBuilder sb = new StringBuilder();
    for (int f = 0; f < filas; f++) {
      for (int c = 0; c < columnas; c++) {
        String fmt = String.format(
          "%s%%%ds%s",
          c == 0 ? "| " : "  ",
          anchos[c],
          c < columnas - 1 ? "" : " |%n"
        );
        sb.append(String.format(fmt, matriz[f][c]));
      }
    }
    // Elimina el salto de linea final
    sb.setLength(sb.length() - 1);
    return sb.toString();
  }

  private int[] anchoCols() {
    int[] res = new int[columnas];
    for (int f = 0; f < filas; f++) {
      for (int c = 0; c < columnas; c++) {
        if (matriz[f][c] != null) {
          int ancho = matriz[f][c].toString().length();
          res[c] = res[c] < ancho ? ancho : res[c];
        }
      }
    }
    return res;
  }
}
