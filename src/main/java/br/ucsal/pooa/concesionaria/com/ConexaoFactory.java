package br.ucsal.pooa.concesionaria.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoFactory {
	// java.sql = Connection, Statement, Result
	// DriveManager
	public static Connection getConexao() {

		// EntityManagerFactory emf = EntityManagerConfig.getEMF();
		// EntityManager em = emf.createEntityManager();

		// EntityManagerConfig.getEMF();

		String url = "jdbc:postgresql://localhost:5432/agencia";
		String user = "postgres";
		String password = "admin";

		try {

			return DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static void close(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(Connection connection, Statement stmt) {
		close(connection);
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(Connection connection, Statement stmt, ResultSet rs) {
		close(connection, stmt);
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
