package DAO;



import java.sql.PreparedStatement;


import java.sql.ResultSet;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionJDBC.ConnectionFactory;
import pojo.Cobranca;

public class CobraPagaDAO {
//para cobrar adicionar no banco de dados = OK 
//criar um tipo cobranca e passar um tipo cobranca para a DAO de cobranca que vai atualizar a cobranca e retornar a cobranca = OK
//logo apos sera enviado um email 
//para enviar o email atributos do descricao mensagem, destinatario, valorcobrado
	private  Connection con;
	public CobraPagaDAO () {}
	Cobranca cobranca = new Cobranca();
	
	public boolean cobrancaAdd(Cobranca cobranca) throws ClassNotFoundException {
	String sql = "INSERT INTO cobranca(emailcredor, emaildevedor,descricao,valorcobrado,data_cob,biterro) VALUES (?, ?, ?, ?,?,?)";
	
	this.con =  new ConnectionFactory().getConnection();
	
	try{
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, cobranca.getEmail_1());
		stmt.setString(2, cobranca.getEmail_2());
		stmt.setString(3, cobranca.getDescricao());
		stmt.setDouble(4, cobranca.getValorcobrado());
		stmt.setString(5, cobranca.getData_cobranca());
		stmt.setBoolean(6, cobranca.isBiterro());
		
		
		int qtdRowsAffected = stmt.executeUpdate();
		stmt.close();
		if(qtdRowsAffected > 0) {
			System.out.println("Cobranca adicionada no banco");
			return true;
		}
		System.out.println("Erro ao Adicionar cobranca");
		return false;
	}catch(SQLException e){
		System.out.println("Cliente Ja Adicionado no Banco "+e); 
	}finally{
		try{
			this.con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	return false;

	}
public boolean pagar (Cobranca cobranca) throws ClassNotFoundException {
	String sql = "UPDATE cobranca SET biterro = ? where emailcredor = ? and emaildevedor = ? and data_cob = ?";
	this.con =  new ConnectionFactory().getConnection();

	try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setBoolean(1, true);
		stmt.setString(2, cobranca.getEmail_1());
		stmt.setString(3, cobranca.getEmail_2());
		stmt.setString(4, cobranca.getData_cobranca());
		int qtdRowsAffected = stmt.executeUpdate();
		stmt.close();
		
		if(qtdRowsAffected > 0) {
			System.out.println("Cobranca Atualizado com Sucesso");
			return true;
		}
		else {
			System.out.println("Cobranca Inexistente");
			return false;
		}
	}			
	catch (SQLException e) {
		System.out.println("Cobranca com erro"+e);
	}
	return false;
	
}

public ArrayList <Cobranca> getCobrancasDoCliente(String clientecredor) throws ClassNotFoundException, IOException {
	String sql = "SELECT * FROM cobranca where emailcredor = ? and biterro = ?";
	this.con =  new ConnectionFactory().getConnection();
	ArrayList <Cobranca> cobranca1 = new ArrayList<Cobranca>();
	try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, clientecredor);
		stmt.setBoolean(2, false);

		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
		do{
			String descricao = rs.getString("descricao");
			String clientecredor1 = rs.getString("emailcredor");
			String clientedevedor = rs.getString("emaildevedor");
			double valorcobrado = rs.getDouble("valorcobrado");
			String data_cob = rs.getString("data_cob");
			boolean biterro = rs.getBoolean("biterro");
			
			
			;
			cobranca1.add(new Cobranca(clientecredor1, clientedevedor,descricao, valorcobrado, data_cob, biterro));
			
		}
		
		while(rs.next());
		return cobranca1;
		}
		
		else {
			System.out.println("CPF nao Encontrado");
		}

		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	
	
}
public ArrayList <Cobranca> getCobrancasDoClientePagas(String clientecredor) throws ClassNotFoundException, IOException {
	String sql = "SELECT * FROM cobranca where emailcredor = ? and biterro = ?";
	this.con =  new ConnectionFactory().getConnection();
	ArrayList <Cobranca> cobranca1 = new ArrayList<Cobranca>();
	try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, clientecredor);
		stmt.setBoolean(2, true);

		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
		do{
			String descricao = rs.getString("descricao");
			String clientecredor1 = rs.getString("emailcredor");
			String clientedevedor = rs.getString("emaildevedor");
			double valorcobrado = rs.getDouble("valorcobrado");
			String data_cob = rs.getString("data_cob");
			boolean biterro = rs.getBoolean("biterro");
			
			
			;
			cobranca1.add(new Cobranca(clientecredor1, clientedevedor,descricao, valorcobrado, data_cob, biterro));
			
		}
		
		while(rs.next());
		return cobranca1;
		}
		
		else {
			System.out.println("CPF nao Encontrado");
		}

		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	
	
}
public ArrayList <Cobranca> getCobrancasParaCliente(String emaildevedor) throws ClassNotFoundException, IOException {
	String sql = "SELECT * FROM cobranca where emaildevedor = ? and biterro = false";
	this.con =  new ConnectionFactory().getConnection();
	ArrayList <Cobranca> cobranca1 = new ArrayList<Cobranca>();
	try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, emaildevedor);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
		do{
			String descricao = rs.getString("descricao");
			String clientecredor1 = rs.getString("emailcredor");
			String clientedevedor = rs.getString("emaildevedor");
			double valorcobrado = rs.getDouble("valorcobrado");
			String data_cob = rs.getString("data_cob");
			boolean biterro = rs.getBoolean("biterro");
			cobranca1.add(new Cobranca(clientecredor1, clientedevedor,descricao, valorcobrado, data_cob, biterro));
			
		}
		
		while(rs.next());
		return cobranca1;
		}
		
		else {
			System.out.println("CPF nao Encontrado");
		}

		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	
	
}

public ArrayList <Cobranca> getCobrancasParaClientePagas(String emaildevedor) throws ClassNotFoundException, IOException {
	String sql = "SELECT * FROM cobranca where emaildevedor = ? and biterro = ?";
	this.con =  new ConnectionFactory().getConnection();
	ArrayList <Cobranca> cobranca1 = new ArrayList<Cobranca>();
	try {
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, emaildevedor);
		stmt.setBoolean(2, true);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
		do{
			String descricao = rs.getString("descricao");
			String clientecredor1 = rs.getString("emailcredor");
			String clientedevedor = rs.getString("emaildevedor");
			double valorcobrado = rs.getDouble("valorcobrado");
			String data_cob = rs.getString("data_cob");
			boolean biterro = rs.getBoolean("biterro");
			cobranca1.add(new Cobranca(clientecredor1, clientedevedor,descricao, valorcobrado, data_cob, biterro));
			
		}
		
		while(rs.next());
		return cobranca1;
		}
		
		else {
			System.out.println("CPF nao Encontrado");
		}

		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	
	
}
public boolean deletarcob(Cobranca cobranca) throws ClassNotFoundException {
	String sql = "Delete from cobranca where clientecredor=? and clientedevedor=? and data_cob=?";	
	
	this.con =  new ConnectionFactory().getConnection();
	
	try{
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, cobranca.getEmail_1());
		stmt.setString(2, cobranca.getEmail_2());
		stmt.setString(3, cobranca.getData_cobranca());
		
		int qtdRowsAffected = stmt.executeUpdate();
		stmt.close();
		if(qtdRowsAffected > 0) {
			System.out.println("Cobranca Removida");
			return true;
		}
		System.out.println("Erro ao Remover, Cobranca nao encontrado no Banco");
		return false;
	}catch(SQLException e){
		System.out.println(e); 
	}finally{
		try{
			this.con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	return false;
}



}


