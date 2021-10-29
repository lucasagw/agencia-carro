package br.ucsal.pooa.concesionaria.interfaces;

import java.util.List;

import br.ucsal.pooa.concesionaria.model.Comprador;

public interface CompradorDAO {

	void save(Comprador comprador);

	void delete(Comprador comprador);

	void update(Comprador comprador);

	List<Comprador> selectAll();

	List<Comprador> searchByName(String nome);
}
