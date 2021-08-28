package tk.luisalbarracin.gestionfacturas.modelo;

public class DetalleFactura {

	private Integer id;
	private String articulo;
	private Integer cantidad;
	private Factura factura;

	public DetalleFactura() {
	}

	public DetalleFactura(Integer id, String articulo, Integer cantidad, Factura factura) {
		this.id = id;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.factura = factura;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

}
