package ar.edu.utn.frba.dds.macowins;

import java.util.Date;
import java.util.List;

public class Venta {

  private List<Prenda> prendas;
  private Date fecha;
  private MetodoPago metodoDePago;

  public Venta(List<Prenda> prendas, Date fecha, MetodoPago metodoDePago) {

    this.prendas = prendas;
    this.fecha = new Date(fecha.getTime());
    this.metodoDePago = metodoDePago;
  }

  public Date getFecha() {
    Date fecha = this.fecha;
    return fecha;
  }

  double importe(){
    return importeSinRecargo() + metodoDePago.recargo(this.importeSinRecargo());
  }

  double importeSinRecargo(){
    return prendas.stream().mapToDouble(prenda -> prenda.precio()).sum();
  }

  public boolean fecha(Date dia) {
    return getFecha().equals(dia);
  }
}
