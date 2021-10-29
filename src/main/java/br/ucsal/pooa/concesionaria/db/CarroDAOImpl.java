package br.ucsal.pooa.concesionaria.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.pooa.concesionaria.com.ConexaoFactory;
import br.ucsal.pooa.concesionaria.interfaces.CarroDAO;
import br.ucsal.pooa.concesionaria.model.Carro;
import br.ucsal.pooa.concesionaria.model.Comprador;

public class CarroDAOImpl implements CarroDAO {

	@Override
	public void save(Carro carro) {
		
		String sql = "INSERT INTO PUBLIC.CARRO (car_nome, car_placa, car_comp_id) VALUES (?, ?, ?)";
	
		try (Connection conn = ConexaoFactory.getConexao(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, carro.getNome().trim().toUpperCase());
			ps.setString(2, carro.getPlaca().trim().toUpperCase());
			ps.setLong(3, carro.getComprador().getId());

			ps.executeUpdate();
			System.out.println("Registro inserido com sucesso");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Carro carro) {
		
		if (carro == null || carro.getId() == null) {
			System.out.println("Não foi possível excluir o registro");
			return;

		}

		String sql = "DELETE FROM PUBLIC.CARRO WHERE car_id = ?";
		try (Connection conn = ConexaoFactory.getConexao(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setLong(1, carro.getId());
			ps.executeUpdate();
			System.out.println("Registro excluído com sucesso");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Carro carro) {
		if (carro == null || carro.getId() == null) {
			System.out.println("Não foi possível atualizar o registro");
			return;

		}

		String sql = "UPDATE PUBLIC.CARRO SET car_placa = ?, car_nome = ? WHERE car_id = ? ";
		try (Connection conn = ConexaoFactory.getConexao(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, carro.getPlaca().trim().toUpperCase());
			ps.setString(2, carro.getNome().trim().toUpperCase());
			ps.setLong(3, carro.getId());
			ps.executeUpdate();
			System.out.println("Registro atualizado com sucesso");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Carro> selectAll() {

		String sql = "SELECT car_id, car_nome, car_placa, car_comp_id FROM PUBLIC.CARRO ";

		List<Carro> carroList = new ArrayList<>();

		try (Connection conn = ConexaoFactory.getConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Comprador c = CompradorDAOImpl.searchById(rs.getLong("car_comp_id"));
				carroList.add(new Carro(rs.getLong("car_id"), rs.getString("car_nome"), rs.getString("car_placa"), c));

			}

			return carroList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Carro> searchByName(String nome) {

		String sql = "SELECT car_id, car_nome, car_placa, car_comp_id FROM PUBLIC.CARRO where upper(trim(car_nome)) LIKE ?";

		List<Carro> carroList = new ArrayList<>();

		try (Connection conn = ConexaoFactory.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, "%" + nome.trim().toUpperCase() + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Comprador c = CompradorDAOImpl.searchById(rs.getLong("car_comp_id"));
				carroList.add(new Carro(rs.getLong("car_id"), rs.getString("car_nome"), rs.getString("car_placa"), c));

			}

			ConexaoFactory.close(conn, ps, rs);
			return carroList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
