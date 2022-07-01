package TP7.Tests;

import TP6.ColaLL;
import TP7.UList;
import java.util.concurrent.ThreadLocalRandom;

public class Test5 {

  public static void main(String[] args) {
    int cntPCs = 15;
    int horaApertura = 10;
    int horaCierre = 20;
    boolean abierto = true;

    UList pcs = new UList();
    UList usaron = new UList();
    ColaLL espera = new ColaLL();
    int hora = horaApertura;
    int minuto = 0;

    while (true) {
      System.out.println("Hora: " + hora + ":" + minuto);

      if (abierto && llegoAlumno()) {
        espera.push(new User(hora, minuto));
        System.out.println("Llego un alumno");
      }

      for (int i = 0; i < pcs.length(); i++) {
        System.out.println(pcs.length() + " PCs ocupadas");
        User user = (User) pcs.get(i);
        if (user.haTerminado(hora, minuto)) {
          usaron.insert(user);
          pcs.del(i);
          System.out.println("Una PC se desocupó");
        }
      }
      while (!espera.isEmpty() && pcs.length() < cntPCs) {
        User user = (User) espera.pop();
        user.usar(hora, minuto);
        pcs.insert(user);
        System.out.println("Una PC se ocupó");
      }

      // Incrementar tiempo
      minuto += 1;
      if (minuto == 60) {
        minuto = 0;
        hora += 1;
        if (abierto && hora == horaCierre) {
          abierto = false;
        }
        if (hora == 24) {
          hora = 0;
        }
      }

      if (!abierto && espera.isEmpty() && pcs.length() <= 0) break;
    }

    for (int i = 0; i < usaron.length(); i++) {
      User u = (User) usaron.get(i);
      System.out.println("Usuario Nº " + i + ": " + u);
    }
  }

  static boolean llegoAlumno() {
    int n = aleatorioEntre(0, 99);
    return n < 50 ? true : false;
  }

  static int aleatorioEntre(int desde_inclusive, int hasta_inclusive) {
    return ThreadLocalRandom
      .current()
      .nextInt(desde_inclusive, hasta_inclusive + 1);
  }

  static class User {

    private int hLlegada;
    private int mLlegada;
    private int hInicio = -1;
    private int mInicio = -1;
    private int hFin = -1;
    private int mFin = -1;
    private int mDeUso;

    public User(int hora_llegada, int min_llegada) {
      this.hLlegada = hora_llegada;
      this.mLlegada = min_llegada;
      this.mDeUso = aleatorioEntre(20, 60);
    }

    public void usar(int hora_inicio, int min_inicio) {
      this.hInicio = hora_inicio;
      this.mInicio = min_inicio;
      calcHoraFin();
    }

    public boolean haTerminado(int hora_fin, int min_fin) {
      if (hora_fin > this.hFin) return true;
      if (hora_fin == this.hFin && min_fin >= this.mFin) return true;
      return false;
    }

    private void calcHoraFin() {
      int m = mInicio + mDeUso;
      int h = hInicio;
      while (m >= 60) {
        m = m - 60;
        h += 1;
        if (h == 24) h = 0;
      }
      this.hFin = h;
      this.mFin = m;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Llegó a las ");
      sb.append(String.format("%02d:%02d", hLlegada, mLlegada));
      sb.append(" y usó la PC por ");
      sb.append(mDeUso);
      sb.append(" minutos (");
      sb.append("desde las ");
      sb.append(String.format("%02d:%02d", hInicio, mInicio));
      sb.append(" hasta las ");
      sb.append(String.format("%02d:%02d", hFin, mFin));
      sb.append(")");
      return sb.toString();
    }
  }
}
