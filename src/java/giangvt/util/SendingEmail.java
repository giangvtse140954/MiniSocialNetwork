/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.util;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author MY HP
 */
public class SendingEmail implements Serializable {

    private String email;
    private String code;

    public SendingEmail(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public void sendEmail() throws MessagingException, AddressException {
        String gmail = "vuthugiang26100@gmail.com";
        String pword = "rubikcube2000";
        Properties properties = new Properties();

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // dang nhap vao gmail
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(gmail, pword);
            }
        });

        // tao tao tin
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(gmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Please verify your e-mail from MiniSN");
        message.setText("Please copy this code and paste it into your form "
                + "to complete the e-mail verification process."
                + "Your verification code: " + code
                + "\n\nThe code can be used only once.\n\n");
        
        // gui mail di
        Transport.send(message);

    }
}
