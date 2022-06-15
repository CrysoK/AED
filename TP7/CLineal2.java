package TP7;

import java.util.Comparator;

public interface CLineal2 {
  public int search(Object dato, Comparator<Object> c);

  public Object get(int pos);

  public boolean del(int pos);

  public void clear();

  public boolean isEmpty();

  public int length();
}
