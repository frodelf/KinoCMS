package progect.avadaMedia.KinoCMS.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

@Service
public class EmailUtil {
    public static void sendEmail(String fromEmail, String toEmail, String subject, MultipartFile message) {
        // Встановлюємо параметри сесії
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.enabledProtocols", "TLSv1.2");

        // Створюємо об'єкт сесії з аутентифікацією
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, "wrkzbilrlroaxctw");
                    }
                });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(fromEmail));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            mimeMessage.setSubject(subject);

            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            ByteArrayDataSource source = new ByteArrayDataSource(message.getBytes(), "text/html");
            messageBodyPart.setDataHandler(new DataHandler(source));
            multipart.addBodyPart(messageBodyPart);

            mimeMessage.setContent(multipart);

            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
