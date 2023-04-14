package Mailing;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class MailingService {

    final static private String user = "onepassprojectds@gmail.com";
    final static private String pass = "gjzpqtejtkvaingf";
    private String emailAddr;
    private String subject;
    private String messageBody;

    
    public MailingService(String emailAddr, String subject, String messageBody) {
        this.emailAddr = emailAddr;
        this.subject = subject;
        this.messageBody = messageBody;
    }


    public void sendMail() {
        // set properties for sending emails
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        // create a session for sending emails
        Session session = Session.getDefaultInstance(props,
        new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddr));
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);
            System.out.println("Message has been sent");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

