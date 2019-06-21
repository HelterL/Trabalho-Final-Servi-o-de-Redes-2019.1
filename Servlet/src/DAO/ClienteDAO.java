package DAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import ConnectionJDBC.ConnectionFactory;
import pojo.Cliente;

public class ClienteDAO {
	private  Connection con;
	public ClienteDAO () {}
	
	public ArrayList<Cliente> getListaClientes()  {
		String sql = "SELECT * FROM cliente";
		ArrayList<Cliente> listaclientes= new ArrayList<Cliente>();
		try {
			this.con =  new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			con.close();
			if(rs.next()) {
				do{
					String nome = rs.getString("nome");
					String email = rs.getString("email");				
	
					listaclientes.add(new Cliente(nome,email));				
				}
				while(rs.next());
				
				return listaclientes;
			} else {
				String erro = "Cliente Nao encontrado";
				System.out.println(erro);
				return listaclientes;
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}

	public boolean AddCliente(Cliente cliente) {
		
	String sql = "INSERT INTO cliente(nome, senha, email,cpf,data_nasc,telefone,saldo) VALUES (?, ?, ?, ?,?,?,?)";
		
		try {
			this.con =  new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getSenha());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getCpf());
			stmt.setString(5, cliente.getData());
			stmt.setString(6, cliente.getTelefone());
			stmt.setDouble(7, cliente.getSaldo());
			
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if(qtdRowsAffected > 0) {
				System.out.println("Cliente Adicionado");
				return true;
			}
			System.out.println("Erro ao Adicionar");
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
	
	
	public Cliente loginUsuario(String email,String senha){
		String sql = "SELECT * FROM cliente where email = ? and senha = ?";
		try {
			this.con =  new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,senha);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
			do{
				
				String nome = rs.getString("nome");
				String senha1 = rs.getString("senha");
				String email1 = rs.getString("email");
				String cpf1 = rs.getString("cpf");
				String datanasc = rs.getString("data_nasc");
				String telefone = rs.getString("telefone");
				Double saldo1 = rs.getDouble("saldo");
				
				Cliente cliente = new Cliente(nome,senha1,email1,cpf1,datanasc,telefone,saldo1);
				
				return cliente;
			}
			while(rs.next());
			}
			else {
			System.out.println("Usuario n encontrado");
			return null;
			}
	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}


	

	public Cliente getCliente(String email) throws ClassNotFoundException, IOException {
		String sql = "SELECT * FROM cliente where email = ? ";
		this.con =  new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,email);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
			do{
				String nome = rs.getString("nome");
				String senha1= rs.getString("senha");
				String email1= rs.getString("email");
				String cpf1= rs.getString("cpf");
				String data_nasc1= rs.getString("data_nasc");
				String telefone1= rs.getString("telefone");
				Double saldo1= rs.getDouble("saldo");

				Cliente cliente = new Cliente(nome,senha1,email1,cpf1,data_nasc1,telefone1,saldo1);
				return cliente;
			}
			while(rs.next());
			}
			else {
			String erro = "Cliente Nao encontrado";
			System.out.println(erro);
			}
	
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public boolean atualizaClientesaldo(Cliente cliente){
		String sql = "UPDATE cliente SET nome = ?,senha = ?, email = ?, data_nasc = ?, telefone = ?, saldo = ? where email = ?";
		try {
			this.con =  new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, cliente.getNome());
					stmt.setString(2, cliente.getSenha());
					stmt.setString(3, cliente.getEmail());
					stmt.setString(4, cliente.getData());
					stmt.setString(5, cliente.getTelefone());
					stmt.setDouble(6, cliente.getSaldo());
					stmt.setString(7, cliente.getEmail());
					int qtdRowsAffected = stmt.executeUpdate();
					stmt.close();
					
					if(qtdRowsAffected > 0) {
						System.out.println("cliente Atualizado com Sucesso");
						return true;
					}
					else {
						System.out.println("CPF Inexistente");
					}
				}			
				catch (SQLException e) {
					System.out.println("Cpf com erro"+e);
				}
		return false;


	}
	
	public boolean deletarcliente(Cliente cliente){
		String sql = "Delete from cliente where cpf =?";	
		
		try {
			this.con =  new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, cliente.getCpf());
			
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if(qtdRowsAffected > 0) {
				System.out.println("Cliente Removido");
				return true;
			}
			System.out.println("Erro ao Remover, Cliente nao encontrado no Banco");
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


	public boolean atualizaCliente(Cliente cliente) {
		String sql = "UPDATE cliente SET nome = ?, senha = ?, email = ?, telefone = ? where email = ?";
		try {
			this.con =  new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, cliente.getNome());
					stmt.setString(2, cliente.getSenha());
					stmt.setString(3, cliente.getEmail());
					stmt.setString(4, cliente.getTelefone());
					stmt.setString(5, cliente.getEmail());
					int qtdRowsAffected = stmt.executeUpdate();
					stmt.close();
					
					if(qtdRowsAffected > 0) {
						System.out.println("cliente Atualizado com Sucesso");
						return true;
					}
					else {
						System.out.println("CPF Inexistente");
					}
				}			
				catch (SQLException e) {
					System.out.println("Cpf com erro"+e);
				}
		return false;


	}
	
	public boolean ExibirCliente(Cliente cliente)  {
		String sql = "SELECT email FROM cliente WHERE email ILIKE '?%';";
		try {
			this.con =  new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, cliente.getNome());
					stmt.setString(2, cliente.getSenha());
					stmt.setString(3, cliente.getEmail());
					stmt.setString(4, cliente.getTelefone());
					stmt.setString(5, cliente.getEmail());
					int qtdRowsAffected = stmt.executeUpdate();
					stmt.close();
					
					if(qtdRowsAffected > 0) {
						System.out.println("Exibir clientes");
						return true;
					}
					else {
						System.out.println("CPF Inexistente");
					}
				}			
				catch (SQLException e) {
					System.out.println("Cpf com erro"+e);
				}
		return false;


	}
	
}