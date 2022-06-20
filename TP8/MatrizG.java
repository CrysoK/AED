package TP8;

import Utils.Matriz;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
      input.next();
      filas = columnas = input.hasNextInt() ? input.nextInt() : 0;
      matriz = new Object[filas][columnas];

      for (int f = 0; f < filas; f++) {
        for (int c = 0; c < columnas; c++) {
          matriz[f][c] = input.hasNext() ? input.next() : null;
        }
      }

      input.close();
    } catch (FileNotFoundException e) {
      System.out.println("Archivo no encontrado");
      filas = 0;
      columnas = 0;
      matriz = new Object[filas][columnas];
    }
  }

  public MatrizG() {
    super();
  }

  public void writeToFile(String archivo, String tipo) {
    if (filas == 0 || columnas == 0) {
      return;
    }
    StringBuilder sb = new StringBuilder();
    sb.append(tipo + " ");
    sb.append(filas + "\n");
    int[] anchos = anchoCols();

    for (int f = 0; f < filas; f++) {
      for (int c = 0; c < columnas; c++) {
        String fmt = String.format(
          "%s%%%ds%s",
          c == 0 ? "" : " ",
          anchos[c],
          c < columnas - 1 ? "" : "%n"
        );
        sb.append(String.format(fmt, matriz[f][c]));
      }
    }

    // Elimina el salto de linea final
    sb.setLength(sb.length() - 1);

    String contenido = sb.toString();
    try {
      File file = new File(archivo);
      if (!file.exists()) file.createNewFile();
      PrintWriter output = new PrintWriter(file);
      output.print(contenido);
      output.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void set(double valor, int vertice, int destino) {
    matriz[vertice][destino] = valor;
  }

  public void set(int valor, int vertice, int destino) {
    matriz[vertice][destino] = valor;
  }

  public double getCosto(int vertice, int destino) {
    return Double.parseDouble(matriz[vertice][destino].toString());
  }

  public int getVertice(int vertice, int destino) {
    String e = matriz[vertice][destino].toString();
    return Integer.parseInt(e);
  }

  public static void main(String[] args) {
    String sep = File.separator;
    String ruta = "TP8" + sep + "Grafos" + sep;
    MatrizG m = new MatrizG(ruta + "GD4.txt");
    m.writeToFile(ruta + "GD4-Copia.txt", "D");
  }
}
// private void writeD(StringBuilder sb, int[] anchos) {
//   for (int f = 0; f < filas; f++) {
//     for (int c = 0; c < columnas; c++) {
//       String fmt = String.format(
//         "%s%%%ds%s",
//         c == 0 ? "" : " ",
//         anchos[c],
//         c < columnas - 1 ? "" : "%n"
//       );
//       sb.append(String.format(fmt, matriz[f][c]));
//     }
//   }
// }
// private void writeND(StringBuilder sb, int[] anchos) {
//   for (int f = 0; f < filas; f++) {
//     for (int c = 0; c <= f; c++) {
//       String fmt = String.format(
//         "%s%%%ds%s",
//         c == 0 ? "" : " ",
//         anchos[c],
//         c != f ? "" : "%n"
//       );
//       sb.append(String.format(fmt, matriz[f][c]));
//     }
//   }
// }
// private void readD(Scanner sc) {
//   for (int f = 0; f < filas; f++) {
//     for (int c = 0; c < columnas; c++) {
//       if (sc.hasNext()) {
//         matriz[f][c] = sc.next();
//       }
//     }
//   }
// }
// private void readND(Scanner sc) {
//   // mitad inferior (f >= c)
//   for (int f = 0; f < filas; f++) {
//     for (int c = 0; c <= f; c++) {
//       if (sc.hasNext()) {
//         matriz[f][c] = sc.next();
//         matriz[c][f] = matriz[f][c];
//       }
//     }
//   }
// mitad superior (f < c)
// for (int f = 0; f < filas; f++) {
//   for (int c = f + 1; c < columnas; c++) {
//     matriz[f][c] = matriz[c][f];
//   }
// }
// }
