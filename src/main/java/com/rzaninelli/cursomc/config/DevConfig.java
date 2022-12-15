package com.rzaninelli.cursomc.config;

import com.rzaninelli.cursomc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev") // indica que todos os Beans criados a partir desta classe só estarão ativos quando o profile de teste estiver ativo
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if (!strategy.equals("create")) {
            return false;
        }

        dbService.instantiateTestDatabase();

        return true;
    }
}
