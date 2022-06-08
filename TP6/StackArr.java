package TP6;

// EJERCICIO 2

public class StackArr<T> extends Stack<T> {

  private T[] stack;
  private int top;

  @SuppressWarnings("unchecked")
  public StackArr(int size) {
    this.stack = (T[]) new Object[size];
    this.top = -1;
  }

  public boolean push(T dato) {
    if (!isFull()) {
      this.top += 1;
      this.stack[top] = dato;
      return true;
    } else {
      return false;
    }
  }

  public T pop() {
    if (!isEmpty()) {
      T dato = stack[top];
      this.top -= 1;
      return dato;
    } else {
      return null;
    }
  }

  public void clear() {
    this.top = -1;
  }

  public boolean isEmpty() {
    return this.top == -1;
  }

  private boolean isFull() {
    return this.top == this.stack.length - 1;
  }
}
