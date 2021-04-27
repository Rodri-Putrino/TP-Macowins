package ar.edu.utn.frba.dds.macowins;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrendaTest {

  @Test
  public void elTipoDeUnaCamisaNuevaEsCAMISA() {
    assertEquals(camisaNueva(200).getTipo().toString(), "CAMISA");
  }

  @Test
  public void elTipoDeUnSacoEnLiquidacionEsSACO() {
    assertEquals(sacoEnLiquidacion(200).getTipo().toString(), "SACO");
  }

  @Test
  public void elPrecioDeUnaCamisaNuevaEsSuPrecioBase() {
    assertEquals(camisaNueva(4000).precio(), 4000, 0);
    assertEquals(camisaNueva(5000).precio(), 5000, 0);
  }

  @Test
  public void elPrecioDeUnSacoEnLiquidacionEsSuLaMitadDeSuPrecioBase() {
    assertEquals(sacoEnLiquidacion(3000).precio(), 1500, 0);
    assertEquals(sacoEnLiquidacion(8000).precio(), 4000, 0);
  }

  @Test
  public void elPrecioDeUnPantalonEnPromocionEsSuPrecioBaseMenosSuDecuento() {
    assertEquals(pantalonEnPromocion(1500, 200).precio(), 1300, 0);
    assertEquals(pantalonEnPromocion(1600, 100).precio(), 1500, 0);
  }

  @Test
  public void elImporteDeUnaVentaEnEfectivoEsLaSumaDeLosImportesDeSusPrendas() {
    assertEquals(tresCamisasNuevasPagadasEnEfectivo(1500).importe(), 4500);
    assertEquals(tresCamisasNuevasPagadasEnEfectivo(3000).importe(), 9000);
  }

  @Test
  public void elImporteDeUnaVentaConTarjetaEsLaSumaDeLosImportesDeSusPrendasMasUnRecargo() {
    assertEquals(tresCamisasNuevasPagadasConTarjeta(1500, 6, 5).importe(), 4575);
  }

  private Prenda pantalonEnPromocion(int precioBase, int descuento) {
    Prenda pantalonEnPromocion = new Prenda(precioBase, new Promocion(descuento), TipoPrenda.PANTALON);
    return pantalonEnPromocion;
  }

  private Prenda camisaNueva(double precioBase) {
    Prenda camisaNueva = new Prenda(precioBase, new Nueva(), TipoPrenda.CAMISA);
    return camisaNueva;
  }

  private Prenda sacoEnLiquidacion(double precioBase) {
    Prenda sacoEnLiquidacion = new Prenda(precioBase, new Liquidacion(), TipoPrenda.SACO);
    return sacoEnLiquidacion;
  }

  private Venta tresCamisasNuevasPagadasEnEfectivo(double precioBase) {
    Prenda primeraCamisa = new Prenda(precioBase, new Nueva(), TipoPrenda.CAMISA);
    Prenda segundaCamisa = new Prenda(precioBase, new Nueva(), TipoPrenda.CAMISA);
    Prenda terceraCamisa = new Prenda(precioBase, new Nueva(), TipoPrenda.CAMISA);
    List<Prenda> prendas = new ArrayList<>();
    prendas.add(primeraCamisa);
    prendas.add(segundaCamisa);
    prendas.add(terceraCamisa);
    Venta tresCamisasNuevas = new Venta(prendas, new Date(), new Efectivo());
    return tresCamisasNuevas;
  }

  private Venta tresCamisasNuevasPagadasConTarjeta(double precioBase, Integer cantidadDeCuotas, double coeficiente) {
    Prenda primeraCamisa = new Prenda(precioBase, new Nueva(), TipoPrenda.CAMISA);
    Prenda segundaCamisa = new Prenda(precioBase, new Nueva(), TipoPrenda.CAMISA);
    Prenda terceraCamisa = new Prenda(precioBase, new Nueva(), TipoPrenda.CAMISA);
    List<Prenda> prendas = new ArrayList<>();
    prendas.add(primeraCamisa);
    prendas.add(segundaCamisa);
    prendas.add(terceraCamisa);
    Venta tresCamisasNuevas = new Venta(prendas, new Date(), new Tarjeta(cantidadDeCuotas, coeficiente));
    return tresCamisasNuevas;
  }
}
