package tk.luisalbarracin.gestionfacturas.dao;

import java.sql.SQLException;
import java.util.List;

import tk.luisalbarracin.gestionfacturas.modelo.DetalleFactura;

public interface DetalleFacturaDao {

	public void insert(DetalleFactura detalle) throws SQLException;
	
	public DetalleFactura select(Integer id, Integer factura);
	
	public List<DetalleFactura> selectAll();
	
	public void delete(Integer id, Integer factura) throws SQLException;
	
	public void update(DetalleFactura detalle) throws SQLException;
	
	public List<DetalleFactura> selectAllFactura(Integer factura) throws SQLException;
	
	
}
