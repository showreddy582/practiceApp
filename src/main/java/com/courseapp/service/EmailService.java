package com.courseapp.service;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Transactional(value=TxType.REQUIRED)
	public void sendemail(String email, String subject, String text) throws Exception{
		MimeMessage mail = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(email);
		helper.setFrom("courseapp.jnit@gmail.com");
		helper.setSubject(subject);
		helper.setText(text);
		mailSender.send(mail);
	}
}
