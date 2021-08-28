package tk.luisalbarracin.gestionfacturas.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tk.luisalbarracin.gestionfacturas.modelo.DetalleFactura;
import tk.luisalbarracin.gestionfacturas.modelo.Factura;
import tk.luisalbarracin.gestionfacturas.util.ConexionPostgreSQL;

public class DetalleFacturaDaoPostgreSQL implements DetalleFacturaDao {

	private ConexionPostgreSQL conexion;

	/**
	 * private Integer id; private String articulo; private Integer cantidad;
	 * private Factura factura;
	 **/

	private static final String INSERT_DETALLE_SQL = "INSERT INTO detalle_factura (id, articulo, cantidad, factura) VALUES (?, ?, ?, ?);";
	private static final String DELETE_DETALLE_SQL = "DELETE FROM detalle_factura WHERE id = ? AND factura;";
	private static final String UPDATE_DETALLE_SQL = "UPDATE detalle_factura SET articulo = ?, cantidad = ? WHERE id = ? AND factura = ?;";
	private static final String SELECT_DETALLE_BY_ID = "SELECT * FROM detalle_factura WHERE id = ? AND factura = ?;";
	private static final String SELECT_ALL_DETALLE_SQL = "SELECT id, articulo, cantidad, factura FROM detalle_factura;";
	private static final String SELECT_ALL_DETALLE_BY_FACTURA = "SELECT id, articulo, cantidad, factura FROM detalle_factura WHERE factura = ?;";

	public DetalleFacturaDaoPostgreSQL() {
		this.conexion = ConexionPostgreSQL.getConexion();
	}

	@Override
	public void insert(DetalleFactura detalle) throws SQLException {
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_DETALLE_SQL);
			preparedStatement.setInt(1, detalle.getId());
			preparedStatement.setString(2, detalle.getArticulo());
			preparedStatement.setInt(3, detalle.getCantidad());
			preparedStatement.setInt(4, detalle.getFactura().getNumeroFactura());
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public DetalleFactura select(Integer id, Integer factura) {
		DetalleFactura detalle = null;

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_DETALLE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, factura);

			ResultSet rs = conexion.query();

			while (rs.next()) {
				Integer idDetalle = rs.getInt("id");
				String articulo = rs.getString("articulo");
				Integer cantidad = rs.getInt("cantidad");
				Integer facturaId = rs.getInt("factura");

				detalle = new DetalleFactura();
				detalle.setArticulo(articulo);
				detalle.setId(idDetalle);
				detalle.setCantidad(cantidad);
				detalle.setFactura(new Factura(facturaId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return detalle;
	}

	@Override
	public List<DetalleFactura> selectAll() {
		List<DetalleFactura> detalleFactura = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_DETALLE_SQL);
			ResultSet rs = conexion.query();

			while (rs.next()) {
				Integer idDetalle = rs.getInt("id");
				String articulo = rs.getString("articulo");
				Integer cantidad = rs.getInt("cantidad");
				Integer facturaId = rs.getInt("factura");

				DetalleFactura detalle = new DetalleFactura();
				detalle.setArticulo(articulo);
				detalle.setId(idDetalle);
				detalle.setCantidad(cantidad);
				detalle.setFactura(new Factura(facturaId));
				
				detalleFactura.add(detalle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return detalleFactura;
	}

	@Override
	public void delete(Integer id, Integer facturaId) throws SQLException {
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_DETALLE_SQL);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, facturaId);

			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(DetalleFactura detalleFactura) throws SQLException {
		try {

			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_DETALLE_SQL);
			preparedStatement.setString(1, detalleFactura.getArticulo());
			preparedStatement.setInt(2, detalleFactura.getCantidad());
			preparedStatement.setInt(3, detalleFactura.getId());
			preparedStatement.setInt(4, detalleFactura.getFactura().getNumeroFactura());
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<DetalleFactura> selectAllFactura(Integer id) throws SQLException {
		List<DetalleFactura> detalleFactura = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_DETALLE_BY_FACTURA);
			preparedStatement.setInt(1, id);
			ResultSet rs = conexion.query();

			while (rs.next()) {
				Integer idDetalle = rs.getInt("id");
				String articulo = rs.getString("articulo");
				Integer cantidad = rs.getInt("cantidad");
				Integer facturaId = rs.getInt("factura");

				DetalleFactura detalle = new DetalleFactura();
				detalle.setArticulo(articulo);
				detalle.setId(idDetalle);
				detalle.setCantidad(cantidad);
				detalle.setFactura(new Factura(facturaId));
				
				detalleFactura.add(detalle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return detalleFactura;
	}

}
