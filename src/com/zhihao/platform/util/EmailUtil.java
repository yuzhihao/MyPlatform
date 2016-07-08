package com.zhihao.platform.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	
	public static final String MyServerMailAddress= "wingsofyuzhihao@163.com";
	public static final String MyServerMailPassword = "wingsofdream";
	public static final String MyServerMailHost = "smtp.163.com";
	public static final String MyPersonalMailAddress = "928435030@qq.com";
	
	
	public static void sendEmail(String from , String psw, String to ,String subject,String content) {
		Mail mail = new Mail(subject, content, from , to, psw);
		Properties prop = new Properties();
		prop.setProperty("mail.host", mail.host);
		prop.setProperty("mail.transport.protocol", mail.protocol);
		prop.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(prop);
		session.setDebug(true);
		Transport ts;
		try {
			ts = session.getTransport();
			ts.connect(mail.host, mail.mailFrom, mail.password);
			Message message = createSimpleMail(session,mail);
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static MimeMessage createSimpleMail(Session session,Mail mail){
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(mail.mailFrom));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.mailTo));
			message.setSubject(mail.subject);
			message.setContent(mail.content, "text/html;charset=UTF-8");
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
		return message;
	}
	
	public static class Mail{
		//发送邮件人
		String mailFrom ="";
		//发送者密码
		String password;
		//邮件收件人
		String mailTo = "";
		//邮件主题
		String subject = "";
		//邮件内容
		String content = "";
		//邮件发送者host
		String host = "";
		//邮件协议
		String protocol = "smtp";
		
		public Mail(String subject,String content,String mailFrom ,String mailTo, String mailFromPsw){
			this.subject = subject;
			this.content = content;
			this.mailFrom = mailFrom;
			this.mailTo = mailTo;
			host = MyServerMailHost;
			this.password = mailFromPsw;		
		}	
	}
}
