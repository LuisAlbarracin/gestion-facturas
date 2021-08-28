package tk.luisalbarracin.gestionfacturas.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tk.luisalbarracin.gestionfacturas.modelo.Factura;
import tk.luisalbarracin.gestionfacturas.util.ConexionPostgreSQL;

public class FacturaDaoPostgreSQL implements FacturaDao {

	private ConexionPostgreSQL conexion;

	private static final String INSERT_FACTURA_SQL = "INSERT INTO factura (numero_factura, nombre_cliente, fecha) VALUES (?, ? , ?);";
	private static final String DELETE_FACTURA_SQL = "DELETE FROM factura WHERE numero_factura = ?;";
	private static final String UPDATE_FACTURA_SQL = "UPDATE factura SET nombre_cliente = ?, fecha = ? WHERE numero_factura = ?;";
	private static final String SELECT_FACTURA_BY_ID = "SELECT * FROM factura WHERE numero_factura = ?;";
	private static final String SELECT_ALL_FACTURA_SQL = "SELECT numero_factura, nombre_cliente, fecha FROM factura;";
	private static final String SELECT_ALL_FACTURA_BY_CLIENTE = "SELECT numero_factura, nombre_cliente, fecha FROM factura WHERE nombre_cliente = ?;";

	public FacturaDaoPostgreSQL() {
		this.conexion = ConexionPostgreSQL.getConexion();
	}

	@Override
	public void insert(Factura factura) throws SQLException {
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_FACTURA_SQL);
			preparedStatement.setInt(1, factura.getNumeroFactura());
			preparedStatement.setString(2, factura.getNombreCliente());
			preparedStatement.setDate(3, (Date) factura.getFecha());
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Factura select(Integer id) {
		Factura factura = null;

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_FACTURA_BY_ID);
			preparedStatement.setInt(1, id);

			ResultSet rs = conexion.query();

			while (rs.next()) {
				Integer numeroFactura = rs.getInt("numero_factura");
				String nombreCliente = rs.getString("nombre_cliente");
				Date fecha = rs.getDate("fecha");

				factura = new Factura(numeroFactura, nombreCliente, fecha);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return factura;

	}

	@Override
	public List<Factura> selectAll() {
		List<Factura> facturas = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_FACTURA_SQL);
			ResultSet rs = conexion.query();

			while (rs.next()) {
				int numeroFactura = rs.getInt("numero_factura");
				String nombreCliente = rs.getString("nombre_cliente");
				Date fecha = rs.getDate("fecha");

				facturas.add(new Factura(numeroFactura, nombreCliente, fecha));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return facturas;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_FACTURA_SQL);
			preparedStatement.setInt(1, id);

			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Factura factura) throws SQLException {
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_FACTURA_SQL);
			preparedStatement.setString(1, factura.getNombreCliente());
			preparedStatement.setDate(2, (Date) factura.getFecha());
			preparedStatement.setInt(3, factura.getNumeroFactura());
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Factura> selectAllCliente(String cliente) throws SQLException {

		List<Factura> facturasCliente = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_FACTURA_BY_CLIENTE);
			preparedStatement.setString(1, cliente);
			ResultSet rs = conexion.query();

			while (rs.next()) {
				Integer numeroFactura = rs.getInt("numero_factura");
				String nombreCliente = rs.getString("nombre_cliente");
				Date fecha = rs.getDate("fecha");

				facturasCliente.add(new Factura(numeroFactura, nombreCliente, fecha));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return facturasCliente;

	}

}
