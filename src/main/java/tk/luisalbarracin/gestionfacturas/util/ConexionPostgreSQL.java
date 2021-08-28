package tk.luisalbarracin.gestionfacturas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionPostgreSQL {
	private Connection con = null;
	private static ConexionPostgreSQL db;
	private PreparedStatement preparedStatement;

	private static final String URL = "jdbc:postgresql://localhost:5432/";
	private static final String DBNAME = "gestion_factura";
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "password";

	private ConexionPostgreSQL() {

		try {
			Class.forName(DRIVER);
			con = (Connection) DriverManager.getConnection(URL + DBNAME, USERNAME, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cerrarConexion() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ConexionPostgreSQL getConexion() {
		if (db == null) {
			db = new ConexionPostgreSQL();
		}

		return db;
	}

	public ResultSet query() throws SQLException {
		ResultSet res = preparedStatement.executeQuery();
		return res;
	}

	public int execute() throws SQLException {
		int result = preparedStatement.executeUpdate();
		return result;
	}

	public Connection getCon() {
		return this.con;
	}

	public PreparedStatement setPreparedStatement(String sql) throws SQLException {
		this.preparedStatement = con.prepareStatement(sql);
		return preparedStatement;
	}

	public static void main(String[] args) {
		try {
			ConexionPostgreSQL.getConexion().setPreparedStatement("SELECT * FROM cyclist");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
