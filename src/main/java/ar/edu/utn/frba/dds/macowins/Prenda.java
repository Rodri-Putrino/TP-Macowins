package ar.edu.utn.frba.dds.macowins;

public class Prenda {
  private double precioPropio;
  private EstadoPrenda estado;
  private TipoPrenda tipo;

  public Prenda(double precioPropio, EstadoPrenda estado, TipoPrenda tipo) {
    this.precioPropio = precioPropio;
    this.estado = estado;
    this.tipo = tipo;
  }

  public TipoPrenda getTipo() {
    return tipo;
  }

  double precio() {
    return estado.precioFinal(precioPropio);
  }
}