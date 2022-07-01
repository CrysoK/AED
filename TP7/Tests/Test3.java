package TP7.Tests;

import TP7.OList;

public class Test3 {

  public static void main(String[] args) {
    OList lista = new OList((a, b) ->
      ((Integer) a).intValue() - ((Integer) b).intValue()
    );

    lista.insert(100);
    lista.insert(90);
    lista.insert(80);
    lista.insert(70);

    System.out.println(lista);
    System.out.println();

    lista.insert(55);
    lista.insert(77);
    System.out.println(lista);

    System.out.println();
    System.out.println("90 en que posicion esta? " + lista.search(90));
    System.out.println("-90 en que posicion esta? " + lista.search(-90));
  }
}
