package TP6;

import java.util.concurrent.ThreadLocalRandom;

public class Tests {

  static class Cliente {

    private int tiempo;

    public Cliente(int tiempo) {
      this.tiempo = tiempo;
    }

    public int getTiempo() {
      return tiempo;
    }
  }

  static class Cajero {

    private int atendidos = 0;

    public Cajero() {}

    public void atender(Cliente cliente) {
      atendidos += 1;
    }

    public int getAtendidos() {
      return atendidos;
    }
  }

  /**
   * Ejercicio de parcial
   *
   * Usando una cola realice la simulación de una caja rápida de Supermercado.
   * Se tienen 3 cajeros que deben atender una única fila (COLA) de clientes que
   * tienen un tiempo variable de atención (entre 20 y 60 segundos). Debe meter
   * en la fila a los clientes con sus respectivos tiempos de atención. Luego,
   * debe sacar clientes (de la misma) a medida que se desocupen los cajeros
   * hasta brindar atención a todos. Escriba un método (en clase clsSimulación)
   * que simule la atención de clientes que esperan en la cola. Informe cuántos
   * clientes atendió cada cajero y el tiempo promedio de atención de todos los
   * clientes (tiempo total entre número de clientes).
   *
   * - No realice la carga de datos.
   * - Escriba una clase clsCajero encargada de atender clientes. Para la
   *   simulación, debe usar tres objetos instanciados de la clase.
   * - Puede usar la clase clsColaLinkedList.
   */
  public static void simulacion() {
    int cntClientes = ThreadLocalRandom.current().nextInt(1, 20 + 1);
    int cntCajeros = 3;
    int sumaTiempos = 0;
    ColaLL cola = new ColaLL();
    Cajero[] cajeros = new Cajero[cntCajeros];
    for (int i = 0; i < cntCajeros; i++) {
      cajeros[i] = new Cajero();
    }
    for (int i = 0; i < cntClientes; i++) {
      int tiempo = ThreadLocalRandom.current().nextInt(20, 60 + 1);
      sumaTiempos += tiempo;
      cola.push(new Cliente(tiempo));
    }
    int cajero = 0;
    while (!cola.isEmpty()) {
      cajeros[cajero].atender((Cliente) cola.pop());
      cajero = (cajero + 1) % cntCajeros;
    }
    System.out.println(
      "Tiempo promedio de espera: " + (double) sumaTiempos / cntClientes
    );
    for (int i = 0; i < cntCajeros; i++) {
      System.out.println(
        "Cajero " +
        (i + 1) +
        " atendió " +
        cajeros[i].getAtendidos() +
        " clientes"
      );
    }
  }

  public static void main(String[] args) {
    simulacion();
  }
}
