/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.helper;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Derslived
 */
public class MailHelper {

    private String host, port, from, to, subject, body;

    public MailHelper() {
    }

    public String getHost() {
        return host;
    }

    public MailHelper setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPort() {
        return port;
    }

    public MailHelper setPort(String port) {
        this.port = port;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public MailHelper setFrom(String from) {
        this.from = from;
        return this;
    }

    public void getTo(String to) {
        this.to = to;
    }

    public MailHelper setTo(String to) {
        this.to = to;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public MailHelper setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getBody() {
        return body;
    }

    public MailHelper setBody(String body) {
        this.body = body;
        return this;
    }

    public void send() {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setContent(body, "text/html");
            Transport.send(message);
            System.out.println("Mail Sent");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
