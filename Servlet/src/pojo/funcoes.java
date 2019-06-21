package pojo;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class funcoes {

	
	/*public void pagar(Cobranca cobranca) throws ClassNotFoundException {
		CobraPagaDAO cobrapagadao = new CobraPagaDAO();
		//se der certo o pagamento mudar cobraca pra pago
		
		boolean pago = cobrapagadao.pagar(cobranca);
		cobranca.setBiterro(true);
		// atualizar informaçoes no banco de dados se a operação de pagamento der certo
	
	}
	*/
	public void EviarEmail( String destinatario, String msg, String assunto) {
		Properties props = new Properties();
		String senha = "helter123";
		String remetente = "nocashpayufc@gmail.com";
		
		props.put("mail.smtp.user", remetente); 
		props.put("mail.smtp.host", "smtp.gmail.com"); 
		props.put("mail.smtp.port", "25"); 
		props.put("mail.debug", "true"); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.EnableSSL.enable","true");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465"); 
		props.setProperty("mail.smtp.socketFactory.port", "465");

		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(remetente, senha);
			}
		});
		session.setDebug(true);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remetente));
			
			Address[] toUser = InternetAddress.parse(destinatario);
			
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(assunto);
			message.setText(msg);
			Transport.send(message);
		} catch (AddressException e) {
				e.printStackTrace();
		} catch (MessagingException e) {
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
	}
	
}
