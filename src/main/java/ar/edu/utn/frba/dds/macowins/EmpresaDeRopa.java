package ar.edu.utn.frba.dds.macowins;

import java.util.Date;
import java.util.List;

public class EmpresaDeRopa {
  private List<Venta> ventas;

  Double gananciaDelDia(Date dia){
    return ventas.stream().filter(venta -> venta.fecha(dia))
        .mapToDouble(venta -> venta.importe()).sum();
  }

  void registrarVenta(Venta unaVenta){
    ventas.add(unaVenta);
  }

  public EmpresaDeRopa(List<Venta> ventas) {
    this.ventas = ventas;
  }
}