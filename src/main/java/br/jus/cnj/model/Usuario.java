package br.jus.cnj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Integer codigo;
	
	private String nome;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private boolean enabled;
	
	@Transient
	private String regranome;
	
	/**
	 * Unidirecional relationship with Regra (Usuario see Regra)
	 */
	@ManyToOne
	@JoinColumn(name="regraId")
	private Regra regra;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Regra getRegra() {
		return regra;
	}

	public void setRegra(Regra regra) {
		this.regra = regra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getRegranome() {
		return regranome;
	}

	public void setRegranome(String regranome) {
		this.regranome = regranome;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
