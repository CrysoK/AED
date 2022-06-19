package TP8;

import java.io.File;

public class Tests {

  public static void main(String[] args) {
    String sep = File.separator;
    String ruta = "TP8" + sep + "Grafos" + sep;

    String[] mensajes = {
      "Grafo no dirigido de orden 12",
      "Grafo dirigido de orden 12",
      "Grafo dirigido de orden 6",
    };
    String[] archivos = { "GND12.txt", "GD12.txt", "GD6.txt" };

    for (int i = 0; i < mensajes.length; i++) {
      if (i <= 1) {
        // System.out.println(mensajes[i]);
        // GrafoND gND = new GrafoND(ruta + archivos[i]);
        // gND.printMatriz();
        // System.out.println();
        // gND.printCostos();
        // System.out.println();
        // gND.printBusqEnProfundidad();
        // System.out.println();
        // gND.printBusqEnProfundidad2();
        // System.out.println();
        // gND.printBusqEnAmplitud();
        // System.out.println();
      } else {
        System.out.println(mensajes[i]);
        GrafoD gD = new GrafoD(ruta + archivos[i]);
        gD.printMatriz();
        System.out.println();
        gD.printCostos();
        System.out.println();
        // gD.printBusqEnProfundidad();
        // System.out.println();
        // gD.printBusqEnProfundidad2();
        // System.out.println();
        // gD.printBusqEnAmplitud();
        // System.out.println();

        GrafoD.Dijkstra resDijkstra = gD.new Dijkstra(0);
        resDijkstra.print(gD.getOrden() - 1);
        System.out.println();
        // GrafoD.Floyd resFloyd = gD.new Floyd();
        // resFloyd.print();
        // System.out.println();
      }
    }
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
