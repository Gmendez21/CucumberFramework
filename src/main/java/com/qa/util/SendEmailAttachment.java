package com.qa.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.sun.mail.smtp.SMTPTransport;

import com.qa.factory.DriverFactory;

public class SendEmailAttachment {
	
	public DriverFactory driverfactory;
	public static Properties propEmail;
	
	private static final String SMTP_SERVER = "smtp.gmail.com";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	
    private static final String EMAIL_SUBJECT = "Automation Test Report";
    private static final String EMAIL_TEXT = " Hello this is a test";
    
    
    public static void main(String[] args) throws FileNotFoundException {
    	
    	Properties prop = System.getProperties();
    	prop.put("mail.smtp.auth", "true");
    	
    	Session session = Session.getInstance(prop, null);
    	Message msg = new MimeMessage(session);
    	
    	try {
    		DriverFactory driverfactory = new DriverFactory();
    		propEmail = driverfactory.init_prop();
    		msg.setFrom(new InternetAddress(propEmail.getProperty("emailFrom")));
    		
    		msg.setRecipients(Message.RecipientType.TO,
    				InternetAddress.parse(propEmail.getProperty("emailTo")));
    				
    		msg.setSubject(EMAIL_SUBJECT);		
    		
    		//text
    		MimeBodyPart p1 = new MimeBodyPart();
    		p1.setText(EMAIL_TEXT);
    		
    		Path parentFolder = Paths.get(propEmail.getProperty("reportPath")).toAbsolutePath();
    		System.out.println(">>>>>>>>"+parentFolder.toString());
    		Optional<File> mostRecentFile = Arrays.stream(new File(parentFolder.toString()).listFiles())
    				.filter(f -> f.isFile())
    		.max(
    				(f1,f2) -> Long.compare(f1.lastModified(), f2.lastModified()));
    		
    		File mostRecent = mostRecentFile.get();
    		
    		//file
    		MimeBodyPart p2 = new MimeBodyPart();
    		FileDataSource fds = new FileDataSource(mostRecent);
    		
    		p2.setDataHandler(new DataHandler(fds));
    		p2.setFileName(fds.getName());
    		
    		Multipart mp = new MimeMultipart();
    		mp.addBodyPart(p1);
    		mp.addBodyPart(p2);
    		
    		msg.setContent(mp);
    		
    		SMTPTransport t = (SMTPTransport) session.getTransport("smpt");
    		
    		//connect
    		t.connect(SMTP_SERVER, USERNAME, PASSWORD);
    		
    		//send
    		t.sendMessage(msg, msg.getAllRecipients());
    		
    		System.out.println("Response: " +t.getLastServerResponse());
    		
    		t.close();
    		
    	}catch (MessagingException e) {
    		e.printStackTrace();
    	}
    }
    
    

}
