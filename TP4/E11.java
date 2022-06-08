package TP4;

import TP1.Div;
import java.util.Scanner;

public class E11 {

  /*
  Dados dos números N y B, tales que B<N, podemos hacer que N explote usando
  a B como bomba. Cuando N explota, se parte en dos números N1= (N div B) y
  N2=N-(N div B) produciendo una reacción en cadena que hace explotar a N1 y
  luego a N2 con la misma bomba. El proceso de explosión finaliza cuando todos
  los fragmentos de N original, son menores o iguales que la bomba B.

  Ejemplo: Si N=15, y la bomba B=3, el número N se parte inicialmente en dos: 15
  div 3, y 15-(15 div 3), es decir, 5 y 10. Como ambos son mayores que la bomba,
  vuelven a explotar, y asi siguiendo como muestra la figura (PDF).

  Implementar un procedimiento recursivo Explotar, que dado un numero N y un
  numero bomba B, imprima todos los fragmentos que quedan al explotar N usando
  B. Ej.: En el caso que muestra la figura, deberá imprimirse 1,1,3,3,2,1,1,3
  */
  public static void explotar(int n, int b) {
    if (b >= n) {
      System.out.println(n);
    } else {
      explotar(Div.cocienteNat(n, b), b);
      explotar(n - Div.cocienteNat(n, b), b);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a;
    int b;
    try {
      while (true) {
        System.out.print("N: ");
        a = sc.nextInt();
        System.out.print("B: ");
        b = sc.nextInt();
        E11.explotar(a, b);
      }
    } catch (Exception e) {
      sc.close();
    }
  }
}
