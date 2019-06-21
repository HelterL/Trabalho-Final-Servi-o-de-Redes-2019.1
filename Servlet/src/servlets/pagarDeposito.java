package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class pagarDeposito
 */
@WebServlet("/pagarDeposito")
public class pagarDeposito extends HttpServlet {
       
   	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

		try {
			String banco = req.getParameter("banco");
			String agencia = req.getParameter("agencia");
			String conta = req.getParameter("conta");
			String tipoconta = req.getParameter("tipoconta");
			String valordeposito = req.getParameter("valordeposito");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

}
