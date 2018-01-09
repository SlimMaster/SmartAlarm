package com.spring.test.RestService;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SimpleEmailSender {
	@Autowired
    private JavaMailSender sender;
	
	public void sendEmail(String newUserMail, String token) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        
        helper.setTo(newUserMail);
        helper.setText("<html>"
        		+ "<body>"
        		+ "<p>Bienvenu Slim et Merci de votre confiance à utiliser Eagle eye</p>"
        		+ "<Veuillez terminer la dernière étape en confirmant votre compte /--tebourski.slim@gmail.com--/"
        		+ "par un simple click sur le boutton ci-dessous :"
        		+ "<button>"
        		+ "<a href='http://localhost:8080/users/ConfirmRegistration/"+token+"' >Commencez à utiliser Eagle Eye</a>"
        		+ "</button>"
//        		+ "<form action='http://localhost:8080/users/ConfirmRegistration' method='post'>"
//        		+"<button type='submit' name='email' value='"+newUserMail+"'>Complete this registration</button>"
//        		+"</form>"
        		//+ "<a href='http://localhost:8080/users/ConfirmRegistration'>https://mail.google.com/mail/u/0/#inbox/15d371f840a7038f</a>"
        		+ "</body>"
        		+ "</html>"
        		,true);
        
        sender.send(message);
    }
}
