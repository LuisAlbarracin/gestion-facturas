package tk.luisalbarracin.gestionfacturas.dao;

import java.sql.SQLException;
import java.util.List;

import tk.luisalbarracin.gestionfacturas.modelo.Factura;

public interface FacturaDao {

	public void insert(Factura factura) throws SQLException;
	
	public Factura select(Integer id);
	
	public List<Factura> selectAll();
	
	public void delete(Integer id) throws SQLException;
	
	public void update(Factura factura) throws SQLException;
	
	public List<Factura> selectAllCliente(String cliente) throws SQLException;
	
}
