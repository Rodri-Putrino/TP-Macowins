package ar.edu.utn.frba.dds.macowins;

public interface MetodoPago {
  double recargo(double sum);
}

class Efectivo implements MetodoPago{

  public double recargo(double precio) {
    return 0;
  }
}

class Tarjeta implements MetodoPago{

  private Integer cantidadDeCuotas;
  private double coeficiente;

  public double recargo(double precio){
    return cantidadDeCuotas * coeficiente + 0.01 * precio;
  }

  public Tarjeta(Integer cantidadDeCuotas, double coeficiente) {
    this.cantidadDeCuotas = cantidadDeCuotas;
    this.coeficiente = coeficiente;
  }
}