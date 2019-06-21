	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>


<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="stylemenu.css">
	<link rel="stylesheet" type="text/css" href="style.css"> 
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js" type="text/javascript"></script>
	<script src="http://digitalbush.com/files/jquery/maskedinput/rc3/jquery.maskedinput.js" type="text/javascript"></script>
	
	<title>noCASHpay</title>
	<script type="text/javascript">
		jQuery(function($){
		   $("#cpf").mask("999.999.999-99");
		   $("#cpfcredor").mask("999.999.999-99");
		   $("#cpfdevedor").mask("999.999.999-99");
		   $("#telefone").mask("(99) 99999-9999");
		   $("#agencia").mask("999-9");
		   $("#conta").mask("999-999-999-999-9");
		   
		  
		});
	</script>
	
</head>
<body>
<header>
	<div id="nometopo">
	<h2><label>noCashpay</label></h2>
	<div id="logout">
	<h3><a href="login.html">logout</a></h3>
	</div>
	</div>
</header>
	<input type="checkbox" id="chk">
	<label for="chk" class="menu-icon" id="icone"> &#x2632;</label>
	
	<!-- div que engloba tudo -->
	<div class="bg">
	<!-- DIV OPÇÃO CONFIGURAÇÃO -->
	<!--div evento clicar -->
	<div id="configuracao">
	
	<!-- div conteudo -->
	
		<fieldset id="configuser">Dados Pessoais</fieldset>
		<div id="dados">
			<form method="POST" action="ServLetLogin" enctype="application/x-www-form-urlencoded">
				
				<label for="nome" class="labelConfig">Nome</label><br>
				<input type="text" name="nome" value="${cliente.nome}" >
				
				<label for="senha" class="labelConfig">Senha:</label>
				<input type="password" name="senha" value="${cliente.senha}" >
				
				<label for="email" class="labelConfig">Email:</label>
				<input type="email" name="email" value="${cliente.email}">
				
				<label for="telefone" class="labelConfig">Telefone:</label>
				<input type="text" name="telefone" id="telefone" maxlength="12" value="${cliente.telefone}">
			
				<input type="submit" name="acao" value="Alterar Dados">
				<input type="reset" name="limparcampos" value="Limpar Campos">
			</form>
		</div>
	</div>
	<!-- TERMINA AKI DIV CONFIGURAÇÃO -->
	
	<!-- DIV COBRANÇAS -->
	<div id="cobrancas">
		<fieldset id="configcob">Cobrança</fieldset>
		<form method="POST" action="ServLetLogin" enctype="application/x-www-form-urlencoded">
			
			<p>
			 <label for="clientedevedor" class="labelConfig">ClienteDevedor:</label><br>
			<input type="email" name="emaildevedor" placeholder="E-mail" >
			</p>
			
			<p>
			 <label for="valorcobrado" class="labelConfig">Valor Cobrado:</label><br>
			<input type="text" name="valorcobrado" placeholder="Valor Cobrado" id="valorcobrado">
			</p>
			
			<p>
			 <label for="descricao" class="labelConfig">Descrição:</label><br>
			<textarea rows="2" cols="50" name="descricao" style="resize: none"></textarea>
			</p>
			
			
			<input type="submit" name="acao" value="Cobrar">
			<input type="reset" name="limparcampos" value="Limpar Campos">
			
		</form>
	</div>
	<!-- TERMINA AKI DIV COBRANCAS -->
	
	<!-- DIV DEPOSITOS  -->
	<div id="depositos">
		<fieldset id="configcob">Depósito</fieldset>
		<form method="POST" action="" enctype="application/x-www-form-urlencoded">
			<p>
			<label for="opcoes" class="labelConfig">Banco:</label><br>
			<select name="banco">
				  <option value="104-Caixa">Caixa</option>
				  <option value="237-Bradesco">Bradesco</option>
				  <option value="260-Nuconta">Banco do Brasil</option>
			</select>
		  
			</p>
			<p>
			<label for="agencia" class="labelConfig">Número da Agência:</label><br>
			<input type="text" name="agencia" placeholder="Agência" id="agencia">
			</p>
			
			<p>
			<label for="conta" class="labelConfig">Conta:</label><br>
			<input type="text" name="conta" placeholder="Conta" id="conta">
			</p>
			
			<p>
			<label for="opcoes" class="labelConfig">Tipo de Conta:</label><br>
			<select name="tipoconta">
				  <option value="001">Conta Corrente (pessoa fisica)</option>
				  <option value="013"> Poupanca (pessoa fisica)</option>
				  <option value="023">Conta Caixa Fácil</option>
			</select>
		  
			</p>
			
			<p>
			<label for="valordeposito" class="labelConfig">Valor Depósito:</label><br>
			<input type="number" name="valordeposito" placeholder="ValorDeposito">
			</p>
			
			<input type="submit" name="acao" value="Depositar">
			<input type="reset" name="limparcampos" value="Limpar Campos">
		</form>
	</div>
	<!-- TERMINA AKI DIV DEPOSITOS -->
	
	<!-- DIV PAGAMENTOS -->
	<div id="pagamentos">
	<fieldset id="configcob">Histórico</fieldset>
	<div id="historico">
			<h3>Cobranças</h3>
		<table id="tableConteudohistorico">
				<thead>
						<tr>
					<th>Data</th>
					<th>Descrição</th>
					<th>Usuário</th>
					<th>Valor</th>
						</tr>
				</thead>
				<tbody>
				<c:forEach var="Cobrancaspagas" items="${listaCobrancaDoClientePagas}">
	
				<tr>
						<th>${Cobrancaspagas.data_cobranca}</th>
						<th>${Cobrancaspagas.descricao}</th>
						<th>${Cobrancaspagas.email_2}</th>
						<th>${Cobrancaspagas.valorcobrado}</th>
						<th><i class="fa fa-check-square"></i></th>
				</tr>
				</c:forEach>
	
				</tbody>
		</table>
		</div>
	
		<div id="historico1">
		<h3>Pagamentos</h3>
		<table id="tableConteudohistorico1">
				<thead>
						<tr>
					<th>Data</th>
					<th>Descrição</th>
					<th>Usuário</th>
					<th>Valor</th>
						</tr>
				</thead>
				<tbody>
				<c:forEach var="cobrancaparaclientepagas" items="${listaCobrancaParaClientePagas}">
	
				<tr>
						<th>${cobrancaparaclientepagas.data_cobranca}</th>
						<th>${cobrancaparaclientepagas.descricao}</th>
						<th>${cobrancaparaclientepagas.email_1}</th>
						<th>${cobrancaparaclientepagas.valorcobrado}</th>
						<th><i class="fa fa-check-square"></i></th>
				</tr>
				</c:forEach>
				
				</tbody>
		</table>
		</div>
		
		
	</div>
	
	<div id="transferencias">
		<fieldset id="configcob">Transferências</fieldset>
		<form method="POST" action="ServLetLogin" enctype="application/x-www-form-urlencoded">
			
			<p>
			<label for="opcoes" class="labelConfig">Banco:</label><br>
			<select name="banco">
				  <option value="104-Caixa">Caixa</option>
				  <option value="237-Bradesco">Bradesco</option>
				  <option value="260-Nuconta">Banco do Brasil</option>
			</select>
			</p>
			
			<p>
			<label for="agencia" class="labelConfig">Número da Agência:</label><br>
			<input type="text" name="agencia" placeholder="Agência" id="agencia">
			</p>
			
			<p>
			<label for="conta" class="labelConfig">Conta:</label><br>
			<input type="text" name="conta" placeholder="Conta" id="conta">
			</p>			
			
			<p>
			<label for="email" class="labelConfig">Email Destino:</label><br>
			<input type="email" name="emaildestino" placeholder="E-mail" >
			</p>
			
			<p>
			<label for="valordeposito" class="labelConfig">Valor Transferência:</label><br>
			<input type="number" name="valortransferencia" placeholder="ValorTransferência">
			</p>
			<input type="submit" name="acao" value="transferir">
			<input type="reset" name="limparcampos" value="Limpar Campos">
			
		</form>
	</div>
	
<!-- DIV HISTORICO TERMINA AKI -->
<div id="transferenciasnocash">
		<fieldset id="configcob">Transferências noCash</fieldset>
		<form method="POST" action="ServLetLogin" enctype="application/x-www-form-urlencoded">
			<p>
			<label for="email" class="labelConfig">Email Destino:</label><br>
			<input type="email" name="emaildestino" placeholder="E-mail" >
			</p>
			
			<p>
			<label for="valordeposito" class="labelConfig">Valor Transferência:</label><br>
			<input type="number" name="valortransferir" placeholder="ValorTransferência">
			</p>
			<input type="submit" name="acao" value="Transferir">
			<input type="reset" name="limparcampos" value="Limpar Campos">
			
		</form>
	</div>
	
	</div>
	
	<nav class="menu" id="principal">	
		<ul>
			<li><a href="ServLetLogin?acao=Menu">Home</a></li>
			<li><a href="#pagamentos">Histórico</a></li>
			<li><a href="#depositos">Depósitos</a></li>
			<li><a href="#transferencias">Transferência</a></li>
			<li><a href="#transferenciasnocash">Transferência nocash</a></li>
			<li><a href="#cobrancas">Cobranças</a></li>
			<li><a href="#configuracao">Perfil</a></li>
		</ul>
		</nav>
		
		<!-- COBRANÇAS E PAGAMENTOS DA PAGINA INICIAL -->
<!-- ##################################################################################################### -->
		<div id="usuario">
		<h1>Usuário</h1>
		<label>Email: ${cliente.email}</label><br>
		<label>Nome: ${cliente.nome}</label><br>
		<label>Saldo Disponível: ${cliente.saldo} </label>
		</div>
		<div id="historicoCobrancas">
		<h1>Cobranças</h1>
		<table id="tableConteudo">
				<thead>
						<tr>
					<th>Data</th>
					<th>Descrição</th>
					<th>Usuário</th>
					<th>Valor</th>
						</tr>
				</thead>
				<tbody>
				<c:forEach var="Cobranca" items="${listaCobrancaDoCliente}">
	
				<tr>
						<th>${Cobranca.data_cobranca}</th>
						<th>${Cobranca.descricao}</th>
						<th>${Cobranca.email_2}</th>
						<th>${Cobranca.valorcobrado}</th>
						<th><i class="fa fa-exclamation-triangle"></i></th>						
				</tr>
				</c:forEach>
	
				</tbody>
		</table>
		</div>
		
		<div id="historicopagamentos">
		<h1>Pagamentos</h1>
		<table id="tableConteudo">
				<thead>

						<tr>
					<th>Data</th>
					<th>Descrição</th>
					<th>Usuário</th>
					<th>Valor</th>
						</tr>
				</thead>
				<tbody>
				<c:forEach var="cobrancaparacliente" items="${listaCobrancaParaCliente}">
	
				<tr>
						<th>${cobrancaparacliente.data_cobranca}</th>
						<th>${cobrancaparacliente.descricao}</th>
						<th>${cobrancaparacliente.email_1}</th>
						<th>${cobrancaparacliente.valorcobrado}</th>
						<th><i class="fa fa-exclamation-triangle"></i></th>
						<th><a href="ServLetLogin?acao=Pagar&email_1=${cobrancaparacliente.email_1}&email_2=${cobrancaparacliente.email_2}&descricao=${cobrancaparacliente.descricao}&valorcobrado=${cobrancaparacliente.valorcobrado}&data_cobranca=${cobrancaparacliente.data_cobranca}">Pagar</a>
						
				</tr>
				</c:forEach>
				
				</tbody>
		</table>
		</div>
		
		
</body>
</html>