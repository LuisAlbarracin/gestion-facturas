package tk.luisalbarracin.gestionfacturas.modelo;

import java.util.Date;

public class Factura {

	private Integer numeroFactura;
	private String nombreCliente;
	private Date fecha;

	public Factura() {
	}

	public Factura(Integer numeroFactura, String nombreCliente, Date fecha) {
		this.numeroFactura = numeroFactura;
		this.nombreCliente = nombreCliente;
		this.fecha = fecha;
	}

	public Factura(Integer numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Integer getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(Integer numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
