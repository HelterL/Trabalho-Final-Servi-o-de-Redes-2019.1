package pojo;

public class Cobranca {
	Cliente clientecredor;
	String descricao, email_2, email_1,data_cobranca;
	double valorcobrado;
	boolean biterro = false;
	
	public Cobranca(String email_1, String email_2, String descricao, Double valorcobrado) {
		//adicionar para pegar a data atual 
		this.email_2 =  email_2;
		this.email_1 =  email_1;
		this.descricao = descricao;
		this.valorcobrado = valorcobrado;
		this.data_cobranca = data_cobranca;
		this.biterro = false;
		
	}
	
	
	public Cobranca() {	}

	public Cobranca(String email_1, String email_2, String descricao, double valorcobrado,
			String data_cob, boolean biterro) {
		this.email_2 =  email_2;
		this.email_1 =  email_1;
		this.descricao = descricao;
		this.valorcobrado = valorcobrado;
		this.data_cobranca = data_cob;
		this.biterro = biterro;
	}


	


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	

	public void setClientecredor(String clientecredor) {
		this.email_1 = clientecredor;
	}
	public void setClienteDevedor(String clientedevedor) {
		this.email_2 = clientedevedor;
	}

	

	public double getValorcobrado() {
		return valorcobrado;
	}

	public void setValorcobrado(double valorcobrado) {
		this.valorcobrado = valorcobrado;
	}

	public String getData_cobranca() {
		return data_cobranca;
	}

	public void setData_cobranca(String data_cobranca) {
		this.data_cobranca = data_cobranca;
	}

	public boolean isBiterro() {
		return biterro;
	}
	

	public void setBiterro(boolean biterro) {
		this.biterro = biterro;
	}


	public String getEmail_1() {
		// TODO Auto-generated method stub
		return email_1;
	}


	public String getEmail_2() {
		// TODO Auto-generated method stub
		return email_2;
	}
}