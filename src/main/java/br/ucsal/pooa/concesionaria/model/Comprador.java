package br.ucsal.pooa.concesionaria.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "comprador", schema = "public", uniqueConstraints = { @UniqueConstraint(name = "conprador_cpf", columnNames = { "comp_cpf"}) })
//@SequenceGenerator(name = "seq_comp", sequenceName = "sq_comp")
public class Comprador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "comp_id")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comp")	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "comp_cpf")
	private String cpf;

	@Column(name = "comp_nome")
	private String nome;

	public Comprador() {
	}

	public Comprador(Long id, String cpf, String nome) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;

	}

	public Comprador(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Comprador{" + "id=" + id + ", cpf='" + cpf + '\'' + ", nome='" + nome + '\'' + '}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comprador other = (Comprador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
