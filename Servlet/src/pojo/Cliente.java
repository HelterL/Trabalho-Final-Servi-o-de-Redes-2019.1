package pojo;

public class Cliente {
	String nome;
	Double saldo = 0.00;
	String senha;
	public String email;
	String cpf;
	String data;
	String telefone;
	
	
	public Cliente (String nome, String senha, String email, String cpf, String data, String telefone,Double saldo) {
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
		this.data = data;
		this.telefone = telefone;
		this.saldo = saldo;
	}
	public Cliente (String nome, String senha, String email, String cpf, String data, String telefone) {
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
		this.data = data;
		this.telefone = telefone;
	
	}
	public Cliente (String nome, String senha, String email, String data, String telefone) {
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.data = data;
		this.telefone = telefone;
	}
	public Cliente (String cpf,String senha) {
		this.cpf = cpf;
		this.senha=senha;
	}
	
	public Cliente (String nome,String email, String cpf ) {
	this.nome = nome;
	this.email = email;
	this.cpf = cpf;
}
	public Cliente () {
		
	}
	
	
	public String getData() {
		return data;
		
	}
	
	public void setData(String data) {
		this.data = data;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}


	public String getTelefone() {
		return telefone;
	}
	public Double getSaldo() {
		return saldo;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}