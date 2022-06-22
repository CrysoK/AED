package TP6;

public class E9 {

  /* Evaluar si los paréntesis están balanceados */

  public static boolean balanceado(String s) {
    Pila pila = new PilaArr(s.length());
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ')' && pila.pop() == null) return false;
      if (s.charAt(i) == '(') pila.push('(');
    }
    if (pila.isEmpty()) return true;
    return false;
  }

  public static void main(String[] args) {
    System.out.println(balanceado("abc(defg(ijk))(l(mn)op)qr"));
    System.out.println(balanceado("abc(def))ghij(kl)m"));
  }
}
