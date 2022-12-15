package com.rzaninelli.cursomc.config;

import com.rzaninelli.cursomc.services.DBService;
import com.rzaninelli.cursomc.services.EmailService;
import com.rzaninelli.cursomc.services.MockMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test") // indica que todos os Beans criados a partir desta classe só estarão ativos quando o profile de teste estiver ativo
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        dbService.instantiateTestDatabase();

        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockMailService();
    }
}
