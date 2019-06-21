package servlets;


import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ClienteDAO;
import pojo.Cliente;


@WebServlet(urlPatterns="/adicionaUsuario")
public class adicionaUsuario extends HttpServlet{
		
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse aux) throws IOException {

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        PrintWriter writer = res.getWriter();
		

        try {
        	HttpSession session = req.getSession();
        	String nome = req.getParameter("nome");
    	    String senha = req.getParameter("senha");
    	    String email = req.getParameter("email");
    	    String cpf = req.getParameter("cpf");
    	    String data = req.getParameter("data");
    	    String telefone = req.getParameter("telefone");
    	    Cliente cliente = new Cliente(nome,senha,email,cpf,data,telefone);
    	    ClienteDAO clientedao = new ClienteDAO();
    	    boolean cad = clientedao.AddCliente(cliente);
               
    	    if(cad==true) {
    	    	
    	    writer.println("<html><head><title>Teste</title></head>");
    	    writer.println("<body><script type='text/javascript'>alert('Usuario Cadastrado com Sucesso');location='login.html'; </script></body>");
    	    writer.println("</html>");
            writer.close();
            Cobranca(req,res);
            
    	    }
    	    else {
    	    	 	writer.println("<html><head><title>Teste</title></head>");
    	    	    writer.println("<body><script type='text/javascript'>alert('Usuario já Cadastrado');location='cadastro.html'; </script></body>");
    	    	    writer.println("</html>");
    	  
    	            writer.close();
    	    }
    	    	
        } catch (Exception e) {
            req.setAttribute("msg", "Erro: " + e.getMessage());
            req.getRequestDispatcher("Cadastro.html").forward(req, res);
            
        }
	
	
    }    
    
    public void Cobranca(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter writer = res.getWriter();
        writer.print("teste");
    }
 }
