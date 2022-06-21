package TP8;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Tests {

  static String sep = File.separator;
  static String carpetaGrafos = "TP8" + sep + "Grafos" + sep;
  // El cambio de Locale sirve para usar el punto como separador decimal
  static Scanner input = new Scanner(System.in).useLocale(Locale.ENGLISH);

  public static void main(String[] args) {
    // "GND7.txt"
    GrafoND gND7 = new GrafoND(carpetaGrafos + "GND7.txt");
    // gND7.printMatriz();
    gND7.printCostos();
    System.out.println();
    // GrafoND.Prim prim = gND7.new Prim(0);
    GrafoND.Kruskal kruskal = gND7.new Kruskal();
  }

  public static void crearGD(String archivo) {
    GrafoD gD = new GrafoD(input);
    gD.toFile(carpetaGrafos + archivo);
  }

  public static void crearGND(String archivo) {
    GrafoND gND = new GrafoND(input);
    gND.toFile(carpetaGrafos + archivo);
  }

  public static void mostrarArr(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void mostrarArr(double[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }
}
