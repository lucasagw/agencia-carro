package br.ucsal.pooa.concesionaria.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.pooa.concesionaria.com.ConexaoFactory;
import br.ucsal.pooa.concesionaria.interfaces.CompradorDAO;
import br.ucsal.pooa.concesionaria.model.Comprador;



public class CompradorDAOImpl implements CompradorDAO {

	
	
	@Override
	public void save(Comprador comprador) {
		
		String sql = "INSERT INTO PUBLIC.COMPRADOR (comp_cpf, comp_nome) VALUES (?, ?)";
		
		try (Connection conn = ConexaoFactory.getConexao(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, comprador.getCpf());
			ps.setString(2, comprador.getNome().trim().toUpperCase());
			ps.executeUpdate();
			System.out.println("Registro inserido com sucesso");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Comprador comprador) {
		if (comprador == null || comprador.getId() == null) {
			System.out.println("Não foi possível excluir o registro");
			return;

		}

		String sql = "DELETE FROM PUBLIC.COMPRADOR WHERE comp_id = ?";
		try (Connection conn = ConexaoFactory.getConexao(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setLong(1, comprador.getId());
			ps.executeUpdate();
			System.out.println("Registro excluído com sucesso");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
//	public void excluirTodos() {
//		String sql = "delete from PUBLIC.COMPRADOR";
//
//		try (PreparedStatement statement = ConexaoFactory.getConexao().prepareStatement(sql)) {
//			statement.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public void update(Comprador comprador) {
		if (comprador == null || comprador.getId() == null) {
			System.out.println("Não foi possível atualizar o registro");
			return;

		}

		String sql = "UPDATE PUBLIC.COMPRADOR SET comp_cpf = ?, comp_nome = ? WHERE comp_id = ? ";
		try (Connection conn = ConexaoFactory.getConexao(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, comprador.getCpf());
			ps.setString(2, comprador.getNome().trim().toUpperCase());
			ps.setLong(3, comprador.getId());
			ps.executeUpdate();
			System.out.println("Registro atualizado com sucesso");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Comprador> selectAll() {

		String sql = "SELECT comp_id, comp_nome, comp_cpf FROM PUBLIC.COMPRADOR";

		List<Comprador> compradorList = new ArrayList<>();

		try (Connection conn = ConexaoFactory.getConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				compradorList.add(new Comprador(rs.getLong("comp_id"), rs.getString("comp_cpf"), rs.getString("comp_nome")));

			}

			return compradorList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Comprador> searchByName(String nome) {

		String sql = "SELECT comp_id, comp_nome, comp_cpf FROM PUBLIC.COMPRADOR where upper(trim(comp_nome)) LIKE ?";

		List<Comprador> compradorList = new ArrayList<>();

		try (Connection conn = ConexaoFactory.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, "%" + nome.trim().toUpperCase() + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				compradorList.add(new Comprador(rs.getLong("comp_id"), rs.getString("comp_cpf"), rs.getString("comp_nome")));

			}

			ConexaoFactory.close(conn, ps, rs);
			return compradorList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Comprador searchById(Long id) {

		String sql = "SELECT comp_id, comp_nome, comp_cpf FROM PUBLIC.COMPRADOR where comp_id = ?";

		Comprador comprador = null;

		try (Connection conn = ConexaoFactory.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				comprador = new Comprador(rs.getLong("comp_id"), rs.getString("comp_cpf"), rs.getString("comp_nome"));

			}

			ConexaoFactory.close(conn, ps, rs);
			return comprador;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
