package ar.edu.utn.frba.dds.macowins;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Venta {

  private List<Prenda> prendas;
  private LocalDate fecha;
  private MetodoPago metodoDePago;

  public Venta(List<Prenda> prendas, LocalDate fecha, MetodoPago metodoDePago) {

    this.prendas = prendas;
    this.fecha = fecha;
    this.metodoDePago = metodoDePago;
  }

  public LocalDate getFecha() {
    LocalDate fecha = this.fecha;
    return fecha;
  }

  double importe(){
    return importeSinRecargo() + metodoDePago.recargo(this.importeSinRecargo());
  }

  double importeSinRecargo(){
    return prendas.stream().mapToDouble(prenda -> prenda.precio()).sum();
  }

  public boolean fecha(LocalDate dia) {
    return getFecha().equals(dia);
  }
}
