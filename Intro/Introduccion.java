package Intro;

/**
 * # Introducción rápida a Java
 *
 * ## Unicode Para usar UTF-8 es necesario añadir -Dfile.encoding="UTF-8" a la JVM
 *
 * ## Compilación y ejecución Compilar: javac archivo.java Compilar todos los archivos en una
 * carpeta: javac *.java Ejecutar: java archivo
 *
 */

// CLASE

public class Introduccion {

  // PUNTO DE ENTRADA DE TOD0 EL PROGRAMA MAIN

  public static void main(String[] args) {
    // TIPOS DE DATOS PRIMITIVOS

    int entero = 1;
    System.out.println("int: " + entero);

    boolean booleano = true;
    System.out.println("boolean: " + booleano);

    char caracter = 'a';
    System.out.println("char: " + caracter);

    long entero_largo = 1000000;
    System.out.println("long: " + entero_largo);

    float real = 1.2f;
    System.out.println("float: " + real);

    double real_doble = 3323.3232;
    System.out.println("double: " + real_doble);

    // OTROS TIPOS DE DATOS

    String cadena = "HOLA";
    cadena = cadena.toLowerCase();
    System.out.println("String: " + cadena);

    // CLASE DEFINIDA EN OTRO ARCHIVO

    OtroArchivo otro_archivo = new OtroArchivo();
    System.out.println(otro_archivo.quienSoy());

    // CONTROL DE LA EJECUCIÓN

    boolean condicion = booleano;
    if (condicion) {
      System.out.println("La condición es verdadera");
    } else {
      System.out.println("La condición es falsa");
    }

    for (int i = 0; i < 4; i++) {
      System.out.println("Ciclo for (iteración: " + i + ")");
    }

    int contador = 0;
    while (contador <= 3) {
      System.out.println("Ciclo while (iteración: " + contador + ")");
      contador++;
    }

    try {
      int[] array_enteros = { 1, 2, 3 };
      System.out.println(array_enteros[10]);
      System.out.println("Ejecución exitosa");
    } catch (Exception error) {
      System.out.println("Hubo un error durante la ejecución (" + error + ")");
    }

    // USO DE FUNCIONES

    function("soy un argumento");
  }

  // Funciones
  public static void function(String parametro) {
    System.out.println(
      "Esta función fue llamada con el parámetro: \"" + parametro + "\""
    );
  }
}
