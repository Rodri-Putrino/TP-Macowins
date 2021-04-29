package ar.edu.utn.frba.dds.macowins;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
    assertEquals(tresCamisasNuevasPagadasConTarjeta(2000, 12, 2).importe(), 6084);
  }

  @Test
  public void laGananciaDeUnDiaEsLaSumaDeLosImportesDeLasVentasDeEseDia(){
    assertEquals(macowins().gananciaDelDia(LocalDate.now()), 10584);
  }

  private Prenda pantalonEnPromocion(double precioBase, int descuento) {
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
    Venta tresCamisasNuevas = new Venta(prendas, LocalDate.now(), new Efectivo());
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
    Venta tresCamisasNuevas = new Venta(prendas, LocalDate.now(), new Tarjeta(cantidadDeCuotas, coeficiente));
    return tresCamisasNuevas;
  }

  private Venta ventaPagadaEnEfectivo(double precioBaseCamisa, double precioBasePantalon, int descuentoPantalon, LocalDate fecha){
    Prenda camisaNueva = camisaNueva(precioBaseCamisa);
    Prenda pantalonEnPromocion = pantalonEnPromocion(precioBasePantalon, descuentoPantalon);
    List<Prenda> prendas = new ArrayList<>();
    prendas.add(camisaNueva);
    prendas.add(pantalonEnPromocion);
    Venta ventaPagadaEnEfectivo = new Venta(prendas, fecha, new Efectivo());
    return ventaPagadaEnEfectivo;
  }

  private EmpresaDeRopa macowins(){
    List<Venta> ventas = new ArrayList<>();
    ventas.add(tresCamisasNuevasPagadasEnEfectivo(1500));
    ventas.add(tresCamisasNuevasPagadasConTarjeta(2000, 12,2));
    ventas.add(ventaPagadaEnEfectivo(1500, 1000,200, LocalDate.of(2021,4,28)));
    EmpresaDeRopa macowins = new EmpresaDeRopa(ventas);
    return macowins;
  }
}
