package TP7;

/*
 * Uso de Listas. Sean 2 números binarios de gran tamaño, se desea realizar la
 * suma de los mismos también en binario. Para ello, se dispone de tres listas
 * A, B y C que almacenan los dos números y el Resultado respectivo. Se pide
 * implementar una clase que cargue los números binarios, los sume y muestre el
 * resultado en binario.
 */

public class E3 {

  static class Binario {

    private UList<Integer> num;

    public Binario(String numero) {
      num = new UList<Integer>();
      for (int i = 0; i < numero.length(); i++) {
        num.insert((int) numero.charAt(i), i);
      }
    }

    public String toString() {
      StringBuilder str = new StringBuilder("");
      for (int i = 0; i < num.length(); i++) {
        str.append(num.get(i));
      }
      return str.toString();
    }

    public Binario sumar(Binario b) {
      int a = Integer.valueOf(this.toString(), 2);
      int bb = Integer.valueOf(b.toString(), 2);
      int c = a + bb;
      return new Binario(Integer.toBinaryString(c));
    }
  }

  public static void main(String[] args) {
    Binario a = new Binario("1101");
    Binario b = new Binario("1010");
    Binario c = a.sumar(b);
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
  }
}
