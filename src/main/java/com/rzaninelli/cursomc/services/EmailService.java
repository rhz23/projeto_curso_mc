package com.rzaninelli.cursomc.services;

import com.rzaninelli.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;


public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage message);
}
