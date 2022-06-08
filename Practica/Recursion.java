package Practica;

public class Recursion {

  // 'resultado' debe tener el tamaño de 'vector' - 1
  public static void vectorSinPosX(
    int X,
    char[] original,
    char[] nuevo,
    int i
  ) {
    // Variable auxiliar para simplificar el código
    int ult = original.length - 1;
    // El vector se terminó de copiar
    if (i > ult) return;
    // Todavía no se alcanzó el índice a eliminar, se copia sin cambios
    if (i < X) nuevo[i] = original[i];
    // Desde la posición a eliminar, se copia lo que esté un lugar a la derecha
    if (i >= X && i + 1 <= ult) nuevo[i] = original[i + 1];
    // Se repite el proceso con la siguiente posición
    vectorSinPosX(X, original, nuevo, i + 1);
  }

  private static int posicionCaracter(char[] vector, char buscado, int pos) {
    if (pos > vector.length - 1) return -1;
    if (vector[pos] == buscado) return pos;
    return posicionCaracter(vector, buscado, pos + 1);
  }

  private static char[] buscarYEliminarTodoCharX(char X, char[] vec, int i) {
    int ult = vec.length - 1;
    if (i > ult) return vec;
    // Se busca la posición del carácter
    int pos = posicionCaracter(vec, X, i);
    if (pos == -1) return vec;
    // Si la eliminación es exitosa, el resultado tendrá un elemento menos
    char[] resultado = new char[vec.length - 1];
    vectorSinPosX(pos, vec, resultado, 0);
    return buscarYEliminarTodoCharX(X, resultado, pos);
  }

  public static char[] eliminarTodoCharX(char[] vector, char X) {
    return buscarYEliminarTodoCharX(X, vector, 0);
  }

  public static boolean contieneCaracter(char[] vector, char buscado) {
    int pos = posicionCaracter(vector, buscado, 0);
    if (pos != -1) return true;
    return false;
  }

  public static void main(String[] args) {
    String s = "eeheoeleeae";
    char[] vector = s.toCharArray();

    System.out.println(contieneCaracter(vector, 'a'));
    char[] nuevo = eliminarTodoCharX(vector, 'e');
    System.out.println(nuevo);
  }
}
