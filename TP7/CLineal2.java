package TP7;

import java.util.Comparator;

public interface CLineal2<Tipo> {
  public int search(Tipo dato, Comparator<Tipo> c);

  public Tipo get(int pos);

  public boolean del(int pos);

  public void clear();

  public boolean isEmpty();

  public int length();
}
