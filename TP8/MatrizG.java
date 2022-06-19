package TP8;

import Utils.Matriz;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatrizG extends Matriz {

  // protected double infinito = Double.POSITIVE_INFINITY;

  public MatrizG(int orden) {
    super(orden, orden);
  }

  public MatrizG(String archivo) {
    System.out.println("Ruta: " + archivo);
    try {
      Scanner input = new Scanner(new File(archivo));
      boolean simetrica = input.next().equals("ND");
      filas = columnas = input.hasNextInt() ? input.nextInt() : 0;
      matriz = new Object[filas][columnas];
      if (simetrica) readND(input); else readD(input);
      input.close();
    } catch (FileNotFoundException e) {
      System.out.println("Archivo no encontrado");
      filas = 0;
      columnas = 0;
      matriz = new Object[filas][columnas];
    }
  }

  private void readD(Scanner input) {
    for (int f = 0; f < filas; f++) {
      for (int c = 0; c < columnas; c++) {
        if (input.hasNext()) {
          matriz[f][c] = input.next();
        } else {
          matriz[f][c] = "-";
        }
        // System.out.printf("[%d, %d] = \"%s\"\n", f, c, matriz[f][c]);
      }
    }
  }

  private void readND(Scanner sc) {
    // mitad inferior (f >= c)
    for (int f = 0; f < filas; f++) {
      for (int c = 0; c <= f; c++) {
        if (sc.hasNext()) {
          matriz[f][c] = sc.next();
        } else {
          matriz[f][c] = "-";
        }
      }
    }
    // mitad superior (f < c)
    for (int f = 0; f < filas; f++) {
      for (int c = f + 1; c < columnas; c++) {
        matriz[f][c] = matriz[c][f];
      }
    }
  }

  public void set(double valor, int fila, int columna) {
    matriz[fila][columna] = valor;
  }

  public void set(int valor, int fila, int columna) {
    matriz[fila][columna] = valor;
  }

  public double getDouble(int fila, int columna) {
    return Double.parseDouble(matriz[fila][columna].toString());
  }

  public int getInt(int fila, int columna) {
    String e = matriz[fila][columna].toString();
    return Integer.parseInt(e);
  }

  public boolean conectado(int i, int j) {
    if (
      i < 0 || i >= this.getFilas() || j < 0 || j >= this.getColumnas()
    ) return false;
    if (this.get(i, j) == null) return false;
    return true;
  }

  public static void main(String[] args) {
    String sep = File.separator;
    String ruta = "TP8" + sep + "Grafos" + sep;
    MatrizG m = new MatrizG(ruta + "GND12.txt");
    // MatrizG m = new MatrizG(5);
    System.out.println(m);
  }
}
