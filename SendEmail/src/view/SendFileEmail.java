package view;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
public class SendFileEmail {
    public static void main(String[] args) throws MessagingException {

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

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        message.setSubject(subject);
        // Phan 1 gom doan tin nhan
        BodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText(body);
        // phan 2 chua tap tin txt
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
        // Duong dan den file cua ban
        String filePath = "Data/Demo.txt";
        DataSource source1 = new FileDataSource(filePath);
        messageBodyPart2.setDataHandler(new DataHandler(source1));
        messageBodyPart2.setFileName(filePath);
        // phan 3 chua tap tin image
        MimeBodyPart messageBodyPart3 = new MimeBodyPart();
        // Duong dan den file cua ban
        String imagePath = "Image/Anh.JPG";
        DataSource source2 = new FileDataSource(imagePath);
        messageBodyPart3.setDataHandler(new DataHandler(source2));
        messageBodyPart3.setFileName(imagePath);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart1);
        multipart.addBodyPart(messageBodyPart2);
        multipart.addBodyPart(messageBodyPart3);
        message.setContent(multipart);
        Transport.send(message);
        System.out.println("Gui mail thanh cong");
    }
}