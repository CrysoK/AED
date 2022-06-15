package TP6;

// EJERCICIO 2

public class StackArr extends Stack {

  private Object[] stack;
  private int top;

  public StackArr(int size) {
    this.stack = new Object[size];
    this.top = -1;
  }

  public boolean push(Object dato) {
    if (!isFull()) {
      this.top += 1;
      this.stack[top] = dato;
      return true;
    } else {
      return false;
    }
  }

  public Object pop() {
    if (!isEmpty()) {
      Object dato = stack[top];
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
