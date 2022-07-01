package TP7.Tests;

import TP7.Set;
import java.util.Comparator;

public class Test4 {

  public static void main(String[] args) {
    Comparator<Object> cInts = new Comparator<Object>() {
      public int compare(Object a, Object b) {
        return ((Integer) a) - ((Integer) b);
      }
    };

    Set a = new Set(cInts, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 3, 4, 15);
    Set b = new Set(cInts, 2, 4, 6, 8, 10, 16);

    System.out.println("A = " + a);
    System.out.println("B = " + b);

    System.out.println("B ⊂ A: " + a.esSubconjunto(b));
    System.out.println("A ⊂ B: " + b.esSubconjunto(a));

    System.out.println("15 ∈ A: " + a.esElemento(15));

    System.out.println("A ∪ B: " + a.union(b, cInts));
    System.out.println("A ∩ B: " + a.interseccion(b, cInts));
    System.out.println("A - B: " + a.diferencia(b, cInts));
    System.out.println("B - A: " + b.diferencia(a, cInts));
  }
}
