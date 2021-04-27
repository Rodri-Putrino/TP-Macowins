package ar.edu.utn.frba.dds.macowins;

public interface EstadoPrenda {

  double precioFinal(double precioPropio);
}

class Nueva implements EstadoPrenda{

  public double precioFinal(double precioPropio){
    return precioPropio;
  }
}

class Promocion implements EstadoPrenda{

  Integer descuento;

  public Promocion(Integer descuento) {
    this.descuento = descuento;
  }

  public double precioFinal(double precioPropio){
    return precioPropio - descuento;
  }
}

class Liquidacion implements EstadoPrenda{
  public double precioFinal(double precioPropio){
    return precioPropio / 2;
  }
}
