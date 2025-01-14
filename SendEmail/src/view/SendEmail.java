package view;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class SendEmail {

    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {

        final String fromEmail = "atvn15@gmail.com";
        // Mat khai email cua ban
        final String password = "qocs fghn xcnk kvfe";
        // dia chi email nguoi nhan
        final String toEmail = "tn9195981@gmail.com";

        final String subject = "Java Example Test";
        final String body = "Hello Admin";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);


        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(fromEmail, "Lap trinh web"));

        msg.setReplyTo(InternetAddress.parse(fromEmail, false));

        msg.setSubject(subject, "UTF-8");

        msg.setText(body, "UTF-8");

        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        Transport.send(msg);
        System.out.println("Gui mail thanh cong");
    }
}