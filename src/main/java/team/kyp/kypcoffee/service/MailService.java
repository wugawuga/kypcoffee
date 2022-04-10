package team.kyp.kypcoffee.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team.kyp.kypcoffee.domain.Mail;

    @Service
    @AllArgsConstructor
    public class MailService {
        private JavaMailSender mailSender;
        private static final String TO_ADDRESS = "enjuk91@gmail.com";

        public void mailSend(Mail mail) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(TO_ADDRESS);
            message.setSubject(mail.getName()+"님이 발송한 이메일 입니다.");
            message.setText(mail.getMessage());

            mailSender.send(message);
        }
}
