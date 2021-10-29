package br.ucsal.pooa.concesionaria.interfaces;

import java.util.List;

import br.ucsal.pooa.concesionaria.model.Carro;

public interface CarroDAO {

	void save(Carro carro);

	void delete(Carro carro);

	void update(Carro carro);

	List<Carro> selectAll();

	List<Carro> searchByName(String nome);
}
