package br.ucsal.pooa.concesionaria.com;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerConfig {

	public static String DATABASE_NAME = "agencia";

	public static EntityManagerFactory getEMF() {
		return Persistence.createEntityManagerFactory(DATABASE_NAME);
	}
}
