package servlets;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ClienteDAO;
import DAO.CobraPagaDAO;
import pojo.Cliente;
import pojo.Cobranca;
import pojo.funcoes;

/**
 * Servlet implementation class Login
 */
@WebServlet("/ServLetLogin")
public class ServLetLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ArrayList<Cobranca> listaCobranca = new ArrayList<Cobranca>();
	ArrayList<Cobranca> listaCobrancaPagas = new ArrayList<Cobranca>();
	ArrayList<Cobranca> listaCobrancaDoCliente = new ArrayList<Cobranca>();
	ArrayList<Cobranca> listaCobrancaDoClientePagas = new ArrayList<Cobranca>();
	ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
	
	
	Cliente cliente = new Cliente();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String destino = "/WEB-INF/MenuPrincipal.jsp";
		
		ClienteDAO clientdao = new ClienteDAO();
		CobraPagaDAO cobradao = new CobraPagaDAO();
		
		boolean sucesso = false;
		
		try {
			switch (acao) {
				//se autenticando no sistema
				case "Login":
					String email = request.getParameter("email");
					String senha = request.getParameter("senha");
					if (email!=null && senha!=null && !email.isEmpty() && !senha.isEmpty()) {
						cliente = clientdao.loginUsuario(email,senha);
						if (cliente != null) {
							try {
								HttpSession sessao = request.getSession();
								sessao.setAttribute("usuarioautenticado", email);
								listaCliente = clientdao.getListaClientes();
								listaCobrancaDoCliente= cobradao.getCobrancasDoCliente(cliente.email);
								listaCobranca = cobradao.getCobrancasParaCliente(cliente.getEmail());
								
								request.setAttribute("cliente", cliente);
								request.setAttribute("listaCliente", listaCliente);
								request.setAttribute("listaCobrancaDoCliente", listaCobrancaDoCliente);
								request.setAttribute("listaCobrancaParaCliente", listaCobranca);
								
							} catch (ClassNotFoundException e1) {
								request.setAttribute("messagem", "Algo de errado n esta certo...");
								destino = "/WEB-INF/erro.jsp";
								e1.printStackTrace();
							}
						
						}else {
							
						     response.sendRedirect("login.html");
						}
					}
					
					break;
				case "Cadastrar":
					cliente = new Cliente();
					cliente.setNome(request.getParameter("nome"));
					cliente.setCpf(request.getParameter("cpf"));
					cliente.setData(request.getParameter("data"));
					cliente.setEmail(request.getParameter("email"));
					cliente.setSenha(request.getParameter("senha"));
					cliente.setTelefone(request.getParameter("telefone"));
					
					sucesso = clientdao.AddCliente(cliente);

					if(sucesso) {
						destino= "login.html";
						request.setAttribute("messagem", "Usuario cadastrado.");

					}else {
						request.setAttribute("messagem", "Falha ao criar usuario");
						destino= "/WEB-INF/cadastro.html";
					}				
					break;
				case "Alterar Dados":
					
					cliente = new Cliente();
					cliente.setEmail(request.getParameter("email"));
					cliente.setNome(request.getParameter("nome"));
					cliente.setSenha(request.getParameter("senha"));
					cliente.setTelefone(request.getParameter("telefone"));
					//lembra de fazer uma triger para atualizar o email em todas as cobranças
					sucesso = clientdao.atualizaCliente(cliente);
					
					if(sucesso) {
						request.setAttribute("cliente", cliente);
						request.setAttribute("messagem", "Usuario atualizado");
						
					}else {
						request.setAttribute("messagem", "Atualização dos dados falhou");
						destino = "/WEB-INF/erro.jsp";
					}		
					break;
				case "Cobrar":
					Cobranca cobranca = new Cobranca();
					
					cobranca.setBiterro(false);
					cobranca.setClientecredor(cliente.getEmail());
					cobranca.setClienteDevedor(request.getParameter("emaildevedor"));
					cobranca.setDescricao(request.getParameter("descricao"));
					cobranca.setValorcobrado(Double.parseDouble(request.getParameter("valorcobrado")));
					
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
					Date date = new Date(); 
					String hj= dateFormat.format(date); 
					
					cobranca.setData_cobranca(hj);
					
					sucesso = new CobraPagaDAO().cobrancaAdd(cobranca);
					
					if (sucesso) {
						//String msg = cliente.getNome()+" esta "
						//		+ "cobrando você no valor de "+request.getParameter("valorcobrado")+""
						//				+ " veja a descrição "+request.getParameter("descricao");
						//new funcoes().EviarEmail(request.getParameter("email"), msg, "Cobrança noCashpay");
						//request.setAttribute("messagem", "Sua cobrança foi enviada");
						

					}else {
						request.setAttribute("messagem", "falha ao cobrar");
						destino = "/WEB-INF/erro.jsp";
					}
					break;
				case "Pagar":
					System.out.println(request.getParameter("data_cobranca"));
					String s = request.getParameter("valorcobrado");
					Double d = Double.parseDouble(s);
					cobranca = new Cobranca();
					cobranca.setClientecredor(request.getParameter("email_1"));
					cobranca.setClienteDevedor(request.getParameter("email_2"));
					cobranca.setBiterro(Boolean.parseBoolean((String) request.getAttribute("cobranca.biterro")));
					cobranca.setData_cobranca(request.getParameter("data_cobranca"));
					cobranca.setDescricao(request.getParameter("descricao"));
					cobranca.setValorcobrado(d);
					
					if(cliente.getSaldo() >= cobranca.getValorcobrado() ) {
						Cliente c2 = clientdao.getCliente(cobranca.getEmail_1());
						Double add=  c2.getSaldo()+cobranca.getValorcobrado();
						Double sub= cliente.getSaldo()-cobranca.getValorcobrado();
						cliente.setSaldo(sub);
						c2.setSaldo(add);
					
						cobradao.pagar(cobranca);
						clientdao.atualizaClientesaldo(cliente);
						clientdao.atualizaClientesaldo(c2);
						
						request.setAttribute("messagem", "Pagamento efetuado");
											
					}else {
						request.setAttribute("messagem", "Saldo insuficiente.");
						destino = "/WEB-INF/erro.jsp";
					}
					break;
				case "Depositar":
					String banco =request.getParameter("banco");
					String agencia =request.getParameter("agencia");
					String conta =request.getParameter("conta");
					String tipoconta =request.getParameter("tipoconta");
					request.setAttribute("messagem", "Deposito efetuado com sucesso");
					cliente.setSaldo(cliente.getSaldo()+Double.parseDouble(request.getParameter("valordeposito")));
					clientdao.atualizaClientesaldo(cliente);
					break;
				case "transferir":
					banco = request.getParameter("banco");
					agencia = request.getParameter("agencia");
					conta = request.getParameter("conta");
					String emaildestino = request.getParameter("emaildestino");
					Cliente c3 = clientdao.getCliente(emaildestino);
					c3.setSaldo(c3.getSaldo()+Double.parseDouble(request.getParameter("valortransferencia")));
					clientdao.atualizaClientesaldo(c3);
					break;
				
				case "Transferir":
					String emaildestino1 = request.getParameter("emaildestino");
					Cliente c4 = clientdao.getCliente(emaildestino1);
					c4.setSaldo(c4.getSaldo()+Double.parseDouble(request.getParameter("valortransferir")));
					clientdao.atualizaClientesaldo(c4);
					Double sub= cliente.getSaldo()-Double.parseDouble(request.getParameter("valortransferir"));
					cliente.setSaldo(sub);
					clientdao.atualizaClientesaldo(cliente);
					break;
				case "Menu":
				 destino = "/WEB-INF/MenuPrincipal.jsp";
					break;
				case "Cadastro":
					 destino = "/WEB-INF/cadastro.html";
						break;
				default:
					break;
			}
			
		} catch (Exception e) {
			System.out.println("erro");
			destino = "/WEB-INF/erro.jsp";
			e.printStackTrace();
		}
		
		try {
		
			listaCliente = clientdao.getListaClientes();
			listaCobrancaDoCliente= cobradao.getCobrancasDoCliente(cliente.getEmail());
			listaCobrancaDoClientePagas= cobradao.getCobrancasDoClientePagas(cliente.getEmail());
			listaCobranca = cobradao.getCobrancasParaCliente(cliente.getEmail());
			listaCobrancaPagas = cobradao.getCobrancasParaClientePagas(cliente.getEmail());
			
			request.setAttribute("cliente", cliente);
			request.setAttribute("listaCliente", listaCliente);
			request.setAttribute("listaCobrancaParaCliente", listaCobranca);
			request.setAttribute("listaCobrancaDoCliente", listaCobrancaDoCliente);
			request.setAttribute("listaCobrancaParaClientePagas", listaCobrancaPagas);
			request.setAttribute("listaCobrancaDoClientePagas", listaCobrancaDoClientePagas);
					
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			request.setAttribute("messagem", "Algo de errado n esta certo...");
			destino = "/WEB-INF/erro.jsp";
			e1.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
		
	}
	public ServLetLogin() {
		super();
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}