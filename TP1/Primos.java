package TP1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Primos {

  // EJERCICIO 12 //////////////////////////////////////////////////////////////

  /*
   * Implemente un algoritmo que permita determinar si un número es o no primo.
   */
  public static boolean esPrimo(int a) {
    a = a < 0 ? -a : a;
    if (a <= 1) return false; else {
      if (a % 2 == 0) {
        if (a == 2) return true; else return false;
      } else {
        boolean ban = true;
        int d = 3;
        while (d * d <= a && ban) {
          if (a % d == 0) ban = false; else d = d + 2;
        }
        return ban;
      }
    }
  }

  // EJERCICIO 19 //////////////////////////////////////////////////////////////

  /*
   * Implementar la Criba de Eratóstenes para devolver los números primos entre
   * 2 y n.
   */
  public static int[] criba(int n) {
    if (n < 2) return new int[1];
    // array de números hasta n
    int[] nums = nums2HastaN(n);
    // indice para nums
    int iNums = 0;
    // se inicia con los múltiplos de 2
    int primo = nums[iNums];
    while (primo * primo <= n) {
      int iMult = iNums + primo;
      while (iMult <= nums.length - 1) {
        nums[iMult] = 0;
        iMult += primo;
      }
      do {
        iNums++;
      } while (nums[iNums] == 0);
      primo = nums[iNums];
    }
    return nums;
  }

  public static int[] elimCeros(int[] vec) {
    // Lista
    List<Integer> ret = new ArrayList<Integer>();

    for (int i = 0; i <= vec.length - 1; i++) {
      if (vec[i] != 0) ret.add(vec[i]);
    }
    return ret.stream().mapToInt(i -> i).toArray();
  }

  private static int[] nums2HastaN(int n) {
    int[] ret = new int[n - 1];
    for (int i = 0; i < n - 1; i++) {
      ret[i] = i + 2;
    }
    return ret;
  }

  // EJERCICIO 20 //////////////////////////////////////////////////////////////

  /*
   * Realice una nueva versión de la Criba de Eratóstenes que permita obtener
   * los números primos entre m y n (m<n).
   */

  // Una forma de optimizarlo cuando el intervalo es muy grande podría ser
  // reutilizar la intersección de 'primos' con el intervalo [m, n]. Esto
  // evitaría repetir el proceso para ese segmento. Falta solucionar el caso del
  // 1 y del -1, que son inalcanzables por el algoritmo y por lo tanto no pueden
  // ser "tachados" sin un caso especial. Esto sucede cuando
  // - [m <= -1, 1 <= n]
  // - [1, n]
  // - [m, 1]
  // - [m, -1]
  // - [-1, n]
  public static int[] cribaEntre(int m, int n) {
    if (m >= n) return new int[1];
    // array de divisores primos hasta la raíz cuadrada de n
    int[] primos = elimCeros(criba((int) Math.round(Math.sqrt(n))));
    int iP = 0;
    // array de números desde m hasta n
    int[] nums = numsEntre(m, n);
    // indice para nums
    int iN = 0;
    // última posición de nums
    int uN = nums.length - 1;
    boolean casoEspecial = false;
    // Este ciclo recorre 'primos'
    while (iP <= primos.length - 1) {
      int primo = primos[iP];
      int iM = iN;
      // caso especial: 1 y -1
      if (1 == Math.abs(nums[iM])) {
        nums[iM] = 0;
        iM++;
      }
      // se busca el primer múltiplo de 'primo' en el intervalo
      while (
        iM <= uN &&
        (
          nums[iM] ==
          0 || // si es 0, ya fue "tachado"
          primo ==
          Math.abs(nums[iM]) || // si es primo, no se tacha
          Div.restoEnt(nums[iM], primo) != 0 // si no es múltiplo, no se
          // tacha
        )
      ) {
        iM += 1;
      }
      // este ciclo solo se ejecuta si el múltiplo fue encontrado
      while (iM <= uN) {
        // Si el múltiplo del primo no es su propio opuesto, se tacha
        if (primo != Math.abs(nums[iM])) nums[iM] = 0; else if (
          primo == 2
        ) casoEspecial = true;

        if (casoEspecial) {}

        // else if(iM - 1 >= 0 && Math.abs(nums[iM - 1]) == 1 )
        // desde el primer múltiplo, se puede conseguir el próximo sumando
        // 'primo'
        iM += primo;
      }
      // se avanza al siguiente número no "tachado"
      do {
        iN++;
      } while (iN <= uN && nums[iN] == 0);
      iP++;
    }
    return nums;
  }

  private static int[] numsEntre(int m, int n) {
    int[] ret = new int[n - m + 1];
    for (int i = 0; i <= ret.length - 1; i++) {
      ret[i] = m;
      m++;
    }
    return ret;
  }

  // PRUEBAS ///////////////////////////////////////////////////////////////////

  public static void main(String[] args) {
    test12();
    test19();
    test20();
  }

  private static void test12() {
    Scanner sc = new Scanner(System.in);
    int a;
    try {
      while (true) {
        System.out.print("Número a probar: ");
        a = sc.nextInt();
        if (Primos.esPrimo(a)) {
          System.out.println("Es primo");
        } else {
          System.out.println("No es primo");
        }
      }
    } catch (Exception e) {
      sc.close();
    }
  }

  private static void test19() {
    int[] c = criba(98);
    for (int i = 0; i <= c.length - 1; i++) {
      if (c[i] != 0) System.out.println(c[i]);
    }
  }

  private static void test20() {
    // TODO No funciona
    int[] c = cribaEntre(-10, 10);
    for (int i = 0; i <= c.length - 1; i++) {
      if (c[i] != 0) System.out.println(c[i]);
    }
  }
}
